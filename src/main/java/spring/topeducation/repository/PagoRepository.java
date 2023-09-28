package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.PagoEntity;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {
}
