package com.wilmer.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.wilmer.backend.entity.Proveedor;
import com.wilmer.backend.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(@NonNull Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor guardar(@NonNull Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(@NonNull Long id) {
        proveedorRepository.deleteById(id);
    }
}