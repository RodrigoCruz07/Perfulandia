package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {

    @Autowired //para inyectar la clase repository
    private PerfumeRepository perfumeRepository;

    //Obtener todos los perfumes
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    //buscar perfume por ID
    public Optional<Perfume> getPerfumeById(int id) {
        return perfumeRepository.findById(id);
    }

    //Buscar Perfume por nombre
    public List <Perfume> getPerfumeByName(String name) {
        return perfumeRepository.findByName(name);
    }

    //agregar o actualizar perfume
    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    //eliminar perfume por ID
    public boolean deletePerfume(int id) {
        return perfumeRepository.deleteById(id);
    }

    //Actualizar cantidad de un perfume

    public Optional <Perfume> updateQuantity( int id, int newQuantity) {
        Optional<Perfume> optionalPerfume = perfumeRepository.findById(id);
        if(optionalPerfume.isPresent()) {
            Perfume perfume = optionalPerfume.get();
            perfume.setQuantity(newQuantity);
            perfumeRepository.save(perfume);
            return Optional.of(perfume);
        }
        return Optional.empty();
    }


}
