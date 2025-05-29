package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.controller.Request.PerfumeRequest;
import cl.duocuc.rodrcruz.Perfulandia.controller.Response.PerfumeResponse;
import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.service.PerfumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api/v1/perfumes")
public class PerfumeController {
    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

        //Listar Perfumes
        @GetMapping
        public ResponseEntity <List<PerfumeResponse>> getAllPerfumes() {
            List<Perfume> perfumes = perfumeService.findAll();
            List<PerfumeResponse> response = perfumes.stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);}

        //Buscar perfume por ID
        @GetMapping("/{id}")
        public ResponseEntity<PerfumeResponse> getPerfumeById(@PathVariable int id) {
        Optional<Perfume> perfume = perfumeService.findById(id);
        return perfume.map(value -> new ResponseEntity<>(convertToResponse(value),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        //buscar perfume por nombre
        @GetMapping("/search")
        public ResponseEntity<List<PerfumeResponse>> getPerfumeByName(@RequestParam String name) {
        List<Perfume> perfumes = perfumeService.findByName(name);
        if (perfumes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PerfumeResponse> response = perfumes.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //Crear un perfume
        @PostMapping
        public ResponseEntity<PerfumeResponse> createPerfume (
                @RequestBody PerfumeRequest request) {
        Perfume perfume = convertToEntity(request);
        Perfume savedPerfume = perfumeService.save(perfume);
        return new ResponseEntity <>(convertToResponse(savedPerfume), HttpStatus.CREATED);
        }

        //Actualizar perfume
        @PutMapping("/{id}")
        public ResponseEntity <PerfumeResponse> updatePerfume (
                @PathVariable int id, @RequestBody PerfumeRequest request) {
        Perfume perfume = convertToEntity(request);
        perfume.setId(id);
        Perfume updatedPerfume = perfumeService.save(perfume);
        return new ResponseEntity <>(convertToResponse(updatedPerfume), HttpStatus.OK);
        }

        //Eliminar perfume
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePerfume (@PathVariable int id) {
        boolean deleted = perfumeService.deleteById(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    //Metodos auxiliares para conversion
    private PerfumeResponse convertToResponse(Perfume perfume) {
        PerfumeResponse response = new PerfumeResponse();
        response.setId(perfume.getId());
        response.setName(perfume.getName());
        response.setQuantity(perfume.getQuantity());
        response.setPrice(perfume.getPrice());
        response.setBrand(perfume.getBrand());
        return response;

    }

    private Perfume convertToEntity(PerfumeRequest request){
        Perfume perfume = new Perfume();
        perfume.setName(request.getName());
        perfume.setQuantity(request.getQuantity());
        perfume.setBrand(request.getBrand());
        return perfume;
    }
    @PatchMapping("/{idperfum}/quantity")
    public ResponseEntity<PerfumeResponse> updatequantity (@PathVariable int idperfum, @RequestParam int quantity) {
        Optional<Perfume> updated = perfumeService.updateQuantity(idperfum, quantity);
        if(updated.isPresent()) {
            return new ResponseEntity<>(convertToResponse(updated.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}






