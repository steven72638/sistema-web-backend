package com.wilmer.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull; // <-- Importación
import org.springframework.stereotype.Service;

import com.wilmer.backend.entity.Articulo;
import com.wilmer.backend.repository.ArticuloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    public List<Articulo> buscar(String texto) {
        return articuloRepository.buscar(texto);
    }

    public Optional<Articulo> buscarPorId(@NonNull Long id) { // <-- @NonNull
        return articuloRepository.findById(id);
    }

    public Articulo guardar(@NonNull Articulo articulo) { // <-- @NonNull
        return articuloRepository.save(articulo);
    }

    public void eliminar(@NonNull Long id) { // <-- @NonNull
        articuloRepository.deleteById(id);
    }
}