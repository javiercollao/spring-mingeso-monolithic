package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.CuotaEntity;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {
}