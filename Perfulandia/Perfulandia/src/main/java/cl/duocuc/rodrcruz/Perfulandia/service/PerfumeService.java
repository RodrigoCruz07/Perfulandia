package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.repository.PerfumeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

//ver inventario de perfumes
public class PerfumeService {
    @Autowired //para inyectar la clase repository
    private PerfumeRepository perfumeRepository;
    public List <Perfume> getPerfume(){

        return perfumeRepository.getListPerfumes();
    }

    //Buscar perfume por Id
    public Perfume getPerfumeById(int elementId){
        List <Perfume> perfumes = getPerfume();
        if (elementId >= 0 && elementId < perfumes.size()){
            return perfumes.get(elementId -1 );
        }
        return null;
    }

    //Buscar perfume por nombre
    public Perfume getPerfumeByName(String name) {
        List<Perfume> perfumes = getPerfume();
        if (!perfumes.isEmpty()) {
            for (Perfume perfume : perfumes) {
                if (perfume.getName() != null && perfume.getName().equals(name)){
                    return perfume;
                }
            }
        }
        return null;
    }

    //agregar perfume
    public Perfume addPerfume(Perfume perfume){
        List <Perfume> perfumes = perfumeRepository.getListPerfumes();
        int nextId = perfumes.size() + 1;
        Perfume perfume1 = new Perfume(nextId,perfume.getName(), perfume.getQuantity(),perfume.getBrand());
        return perfume;
    }

}
