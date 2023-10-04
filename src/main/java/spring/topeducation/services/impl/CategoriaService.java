package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.CategoriaEntity;
import spring.topeducation.repository.CategoriaRepository;
import spring.topeducation.services.ICategoriaService;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaEntity> listarCategorias() {
        return (List<CategoriaEntity>) categoriaRepository.findAll();
    }

    @Override
    public CategoriaEntity obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
