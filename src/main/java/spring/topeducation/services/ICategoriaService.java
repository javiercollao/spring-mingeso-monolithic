package spring.topeducation.services;

import spring.topeducation.entities.CategoriaEntity;

import java.util.List;

public interface ICategoriaService {
    public CategoriaEntity crearCategoria(CategoriaEntity categoria);
    public CategoriaEntity obtenerCategoriaPorId(Long id);
    public CategoriaEntity actualizarCategoria(CategoriaEntity categoria, Long id);
}
