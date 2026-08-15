package com.wilmer.backend.controller;

import com.wilmer.backend.entity.Articulo;
import com.wilmer.backend.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listar();
    }

    @GetMapping("/buscar")
    public List<Articulo> buscar(@RequestParam String texto) {
        return articuloService.buscar(texto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> verUno(@PathVariable @NonNull Long id) {
        return articuloService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Articulo> crear(@Valid @RequestBody @NonNull Articulo articulo) {
        Articulo nuevo = articuloService.guardar(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> editar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Articulo articulo) {
        return articuloService.buscarPorId(id)
                .map(existente -> {
                    existente.setCodigo(articulo.getCodigo());
                    existente.setNombre(articulo.getNombre());
                    existente.setCategoria(articulo.getCategoria());
                    existente.setPrecio(articulo.getPrecio());
                    existente.setStock(articulo.getStock());
                    return ResponseEntity.ok(articuloService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        articuloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}