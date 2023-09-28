package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.ColegioEntity;

@Repository
public interface ColegioRepository extends JpaRepository<ColegioEntity, Long> {
}
