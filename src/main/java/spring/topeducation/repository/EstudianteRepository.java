package spring.topeducation.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.topeducation.entities.EstudianteEntity;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    public Optional<EstudianteEntity> findByRut(String rut);
}
