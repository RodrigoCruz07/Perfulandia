package cl.duocuc.rodrcruz.Perfulandia.repository;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PerfumeRepository {
    private final List<Perfume> listPerfumes = new ArrayList<>();


    public PerfumeRepository() {
        listPerfumes.add(new Perfume(1, "Sauvage", 10, 85.99, "Dior"));
        listPerfumes.add(new Perfume(2, "One Million", 15, 78.50, "Paco Rabanne"));
        listPerfumes.add(new Perfume(3, "Humor", 20, 39.99, "Natura"));
        listPerfumes.add(new Perfume(4, "212 VIP Black", 12, 90.00, "Carolina Herrera"));
        listPerfumes.add(new Perfume(5, "Blue Label", 8, 65.75, "Givenchy"));
        listPerfumes.add(new Perfume(6, "Classic", 14, 59.00, "Swiss Army"));
        listPerfumes.add(new Perfume(7, "Polo Red", 10, 72.30, "Ralph Lauren"));
        listPerfumes.add(new Perfume(8, "Acqua Di Gio", 7, 99.99, "Giorgio Armani"));
        listPerfumes.add(new Perfume(9, "Eros", 11, 88.40, "Versace"));
        listPerfumes.add(new Perfume(10, "Invictus", 18, 84.20, "Paco Rabanne"));


    }
    public List<Perfume> getPerfumes() {
        return listPerfumes;
    }
}

