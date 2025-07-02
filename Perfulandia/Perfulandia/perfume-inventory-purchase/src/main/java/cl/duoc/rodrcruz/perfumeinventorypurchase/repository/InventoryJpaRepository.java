package cl.duoc.rodrcruz.perfumeinventorypurchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryJpaRepository extends JpaRepository<InventoryDB, Integer> {
}
