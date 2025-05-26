package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfumeService {
    private int nextId = 11;

    @Autowired //para inyectar la clase repository
    private PerfumeRepository perfumeRepository;

    //Obtener todos los perfumes
    public List<Perfume> findAll() {
        return new ArrayList<>(perfumeRepository.getPerfumes());
    }

    public List<Perfume> findByName(String name) {
        return perfumeRepository.getPerfumes().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .toList();
    }
    public Optional<Perfume> findById(int id) {
        return perfumeRepository.getPerfumes().stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
    public Perfume save(Perfume perfume) {
        if(perfume.getId()==0){
            perfume.setId(nextId++);
            perfumeRepository.getPerfumes().add(perfume);

        }else{
            List<Perfume> perfumes = perfumeRepository.getPerfumes();
            for(int i=0; i<perfumes.size(); i++){
                if(perfumes.get(i).getId() == perfume.getId()){
                    perfumes.set(i, perfume);
                    break;
                }
            }

        }
        return perfume;
    }
    public boolean deleteById(int id) {
        return perfumeRepository.getPerfumes().removeIf(p -> p.getId() == id);

    }
    public Optional <Perfume> updateQuantity( int id, int newQuantity) {
        Optional<Perfume> optionalPerfume = findById(id);
        if(optionalPerfume.isPresent()) {
            Perfume perfume = optionalPerfume.get();
            perfume.setQuantity(newQuantity);
            save(perfume);
            return Optional.of(perfume);
        }
        return Optional.empty();
    }

}
