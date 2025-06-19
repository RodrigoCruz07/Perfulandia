package cl.duocuc.rodrcruz.Perfulandia.repository.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerJpaRepository extends JpaRepository<SellerDB, Integer> {
}
