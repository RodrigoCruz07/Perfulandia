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
    private int nextId = 11;

    public PerfumeRepository() {
        listPerfumes.add(new Perfume(1,"Sauvage",2,"Dior"));
        listPerfumes.add(new Perfume(2,"One Million",6,"Paco Rabanne"));
        listPerfumes.add(new Perfume(3,"Humor",12,"Natura"));
        listPerfumes.add(new Perfume(4,"212 VIP Black",8,"Carolina Herrera"));
        listPerfumes.add(new Perfume(5,"Blue Label",10,"Givenchy"));
        listPerfumes.add(new Perfume(6,"Classic",15,"Swiss Army"));
        listPerfumes.add(new Perfume(7,"Polo Red",11,"Ralph Lauren"));
        listPerfumes.add(new Perfume(8,"Acqua Di Gio",17,"Giorgio Armani"));
        listPerfumes.add(new Perfume(9,"Eros",15,"Versace"));
        listPerfumes.add(new Perfume(10,"Invictus",19,"Paco Rabanne"));


    }

    public List <Perfume> findAll() {
        return new ArrayList <>(listPerfumes); //Devuelve copia para evitar modificaciones externas
    }

    public Optional<Perfume> findById(int id) {
        return listPerfumes.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Perfume> findByName(String name) {
        return listPerfumes.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .toList();
    }

    public Perfume save(Perfume perfume) {
        if (perfume.getId() == 0) {
            // Nuevo perfume - Asignar ID
            perfume.setId(nextId++);
            listPerfumes.add(perfume);

        } else {
            //Actualizacion - buscar y reemplazar
            for (int i = 0; i < listPerfumes.size(); i++) {
                if (listPerfumes.get(i).getId() == perfume.getId()) {
                    listPerfumes.set(i, perfume);
                    break;
                }
            }
        }
        return perfume;
    }

    public boolean deleteById(int id) {
        return listPerfumes.removeIf(p -> p.getId() == id);
    }

}
