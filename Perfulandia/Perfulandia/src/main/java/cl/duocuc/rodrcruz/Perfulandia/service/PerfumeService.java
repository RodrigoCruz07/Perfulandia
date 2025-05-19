package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.repository.PerfumeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<Perfume> findAll() {
        return perfumeRepository.findAll();
    }
    public Perfume findById(Long id) {
        return perfumeRepository.findById(id).get();
    }
    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }
    public void delete(Long id) {
        perfumeRepository.deleteById(id);

    }

}
