package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.MetodoPagoEntity;
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Long> {
}
