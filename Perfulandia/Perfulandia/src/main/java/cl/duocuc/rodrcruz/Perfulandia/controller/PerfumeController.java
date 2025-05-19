package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/perfume")

public class PerfumeController {
    @Autowired
    private PerfumeService perfumeService;
    @GetMapping
    public ResponseEntity<List<Perfume>> findAll() {
        List<Perfume> perfumes=perfumeService.findAll();
        if (perfumes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perfumes);
    }


}
