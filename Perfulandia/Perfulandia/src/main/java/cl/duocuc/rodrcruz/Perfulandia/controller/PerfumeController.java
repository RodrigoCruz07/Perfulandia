package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/perfume")

public class PerfumeController {
    @Autowired
    private PerfumeService perfumeService;
    @GetMapping("/{elementId}")
    public ResponseEntity<Perfume> getPerfume(@PathVariable int elementId) {
        Perfume var1 =  perfumeService.getPerfumeById(elementId);
        return  ResponseEntity.ok(var1);

    }





}
