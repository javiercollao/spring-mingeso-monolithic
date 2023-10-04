package spring.topeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.CuotaEntity;
import spring.topeducation.entities.EstudianteEntity;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {
    public List<CuotaEntity> findAllByEstudiante(EstudianteEntity estudiante);

}
