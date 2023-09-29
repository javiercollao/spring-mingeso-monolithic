package spring.topeducation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.topeducation.entities.CategoriaEntity;
import spring.topeducation.repository.CategoriaRepository;
import spring.topeducation.services.ICategoriaService;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public CategoriaEntity crearCategoria(CategoriaEntity categoria) {
        return null;
    }

    @Override
    public CategoriaEntity obtenerCategoriaPorId(Long id) {
        return null;
    }

    @Override
    public CategoriaEntity actualizarCategoria(CategoriaEntity categoria, Long id) {
        return null;
    }
}
