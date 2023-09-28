package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.PuntajeEntity;
@Repository
public interface PuntajeRepository extends JpaRepository<PuntajeEntity, Long> {
}
