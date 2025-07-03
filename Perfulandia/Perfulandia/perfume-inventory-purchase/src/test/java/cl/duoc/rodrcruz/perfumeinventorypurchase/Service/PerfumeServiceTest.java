package cl.duoc.rodrcruz.perfumeinventorypurchase.Service;

import cl.duoc.rodrcruz.perfumeinventorypurchase.model.Perfume;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PerfumeDB;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PerfumeJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*; //
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PerfumeServiceTest {
    @Mock //repositorio
    private PerfumeJpaRepository perfumeJpaRepository;
    @InjectMocks // Inyecta el mock del en el servicio
    private PerfumeDB testPerfumeDB;
    private Perfume testPerfume;
    private cl.duoc.rodrcruz.perfumeinventorypurchase.Service.PerfumeService perfumeService;

    @Test
    void getPerfume() {
        PerfumeDB foundPerfumeDB = new PerfumeDB();
        foundPerfumeDB.setId(1);
        foundPerfumeDB.setName("kako");
        foundPerfumeDB.setBrand("prueba");
        foundPerfumeDB.setSize(100);
        //inicializamos una entidad
        when(perfumeJpaRepository.findById(1)).thenReturn(Optional.of(foundPerfumeDB)); //simulamos el comportamiento
        PerfumeDB result = perfumeService.getPerfume(1);

        assertNotNull(result); //verifica que no sea nulo
        assertEquals(foundPerfumeDB.getName(), result.getName());
        assertEquals(foundPerfumeDB.getBrand(), result.getBrand());
        //Verifica que el metodo llama id 1
        verify(perfumeJpaRepository, times(1)).findById(1);


    }

    @Test
    void registerPerfume() {
        // Simula que el perfume no se encuentra
        when(perfumeJpaRepository.findByNameAndBrand(testPerfume.getName(), testPerfume.getBrand()))
                .thenReturn(Optional.empty());
        // Simula que el repositorio guarda el nuevo perfume y lo devuelve con un ID
        when(perfumeJpaRepository.save(any(PerfumeDB.class))).thenReturn(testPerfumeDB);
        PerfumeDB result = perfumeService.registerPerfume(testPerfume);

        // Verifica el resultado
        assertNotNull(result);
        assertEquals(testPerfumeDB.getName(), result.getName());
        assertEquals(testPerfumeDB.getBrand(), result.getBrand());
        assertEquals(testPerfumeDB.getSize(), result.getSize());


    }

    @Test
    void updatePerfume() {
        // Objeto de perfume con las actualizaciones deseadas
        Perfume updatedPerfume = Perfume.builder()
                .name("Updated Aventus")
                .brand("Updated Creed")
                .size(50)
                .build();
        // Creamos una copia del testPerfumeDB para manipularla y simular la actualización
        PerfumeDB originalPerfume = new PerfumeDB();
        originalPerfume.setId(testPerfumeDB.getId());
        originalPerfume.setName(testPerfumeDB.getName());
        originalPerfume.setBrand(testPerfumeDB.getBrand());
        originalPerfume.setSize(testPerfumeDB.getSize());
        // Simula que el perfume se encuentra
        when(perfumeJpaRepository.findById(testPerfumeDB.getId())).thenReturn(Optional.of(originalPerfume));
        // Importante: mockear para  que save devuelve la misma instancia que fue modificada.
        when(perfumeJpaRepository.save(any(PerfumeDB.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PerfumeDB result = perfumeService.updatePerfume(testPerfumeDB.getId(), updatedPerfume);
        // Verifica que el perfume se actualizó correctamente
        assertNotNull(result);
        assertEquals(updatedPerfume.getName(), result.getName());
        assertEquals(updatedPerfume.getBrand(), result.getBrand());
        assertEquals(updatedPerfume.getSize(), result.getSize());


    }

    @Test
    void deletePerfume() {
        PerfumeDB foundPerfumeDB = new PerfumeDB();
        foundPerfumeDB.setId(5);
        foundPerfumeDB.setName("kako");
        foundPerfumeDB.setBrand("prueba");
        foundPerfumeDB.setSize(100);
        //inicializamos una entidad
        // Simula que el perfume se encuentra
        when(perfumeJpaRepository.findById(5)).thenReturn(Optional.of(testPerfumeDB));

        boolean result = perfumeService.deletePerfume(5);
        // Verifica que la eliminación fue exitosa
        assertTrue(result);


    }

    @Test
    void getAllPerfumes() {
        PerfumeDB perfume1 = new PerfumeDB();
        perfume1.setId(1); perfume1.setName("kako"); perfume1.setBrand("ñam1"); perfume1.setSize(55);
        PerfumeDB perfume2 = new PerfumeDB();
        perfume2.setId(2); perfume2.setName("Rodrigo"); perfume2.setBrand("ñam2"); perfume2.setSize(30);
        PerfumeDB perfume3 = new PerfumeDB();
        perfume2.setId(3); perfume2.setName("Miguel"); perfume2.setBrand("ñam3"); perfume2.setSize(10);

        List<PerfumeDB> allPerfumes = Arrays.asList(perfume1, perfume2);

        // Simula que el repositorio devuelve una lista de perfumes
        when(perfumeJpaRepository.findAll()).thenReturn(allPerfumes);


        List<PerfumeDB> result = perfumeService.getAllPerfumes();

        // Verifica el resultado
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Perfume 1", result.get(0).getName());
        assertEquals("Perfume 2", result.get(1).getName());
    }
}