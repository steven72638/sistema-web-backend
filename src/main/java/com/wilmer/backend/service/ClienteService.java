package com.wilmer.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.wilmer.backend.entity.Cliente;
import com.wilmer.backend.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(@NonNull Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardar(@NonNull Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminar(@NonNull Long id) {
        clienteRepository.deleteById(id);
    }
}