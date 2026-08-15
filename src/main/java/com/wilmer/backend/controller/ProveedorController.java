package com.wilmer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.wilmer.backend.entity.Proveedor;
import com.wilmer.backend.service.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> verUno(@PathVariable @NonNull Long id) {
        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@Valid @RequestBody @NonNull Proveedor proveedor) {
        Proveedor nuevo = proveedorService.guardar(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> editar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Proveedor proveedor) {
        return proveedorService.buscarPorId(id)
                .map(existente -> {
                    existente.setNombre(proveedor.getNombre());
                    existente.setFoto(proveedor.getFoto());
                    existente.setDescripcion(proveedor.getDescripcion());
                    existente.setProductosTop(proveedor.getProductosTop());
                    existente.setRedSocial(proveedor.getRedSocial());
                    return ResponseEntity.ok(proveedorService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}