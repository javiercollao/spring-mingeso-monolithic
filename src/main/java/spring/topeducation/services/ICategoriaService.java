package spring.topeducation.services;

import spring.topeducation.entities.CategoriaEntity;

import java.util.List;

public interface ICategoriaService {
    public List<CategoriaEntity> listarCategorias();
    public CategoriaEntity obtenerCategoriaPorId(Long id);
}
