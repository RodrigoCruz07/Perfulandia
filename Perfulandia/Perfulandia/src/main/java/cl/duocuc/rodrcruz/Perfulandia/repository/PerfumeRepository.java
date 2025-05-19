package cl.duocuc.rodrcruz.Perfulandia.repository;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
}
