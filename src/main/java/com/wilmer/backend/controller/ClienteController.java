package com.wilmer.backend.controller;

import com.wilmer.backend.entity.Cliente;
import com.wilmer.backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> verUno(@PathVariable @NonNull Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody @NonNull Cliente cliente) {
        Cliente nuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable @NonNull Long id, @Valid @RequestBody @NonNull Cliente cliente) {
        return clienteService.buscarPorId(id)
                .map(existente -> {
                    existente.setNombre(cliente.getNombre());
                    existente.setApellido(cliente.getApellido());
                    existente.setEmail(cliente.getEmail());
                    return ResponseEntity.ok(clienteService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}