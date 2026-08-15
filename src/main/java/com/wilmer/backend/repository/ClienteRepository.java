package com.wilmer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.backend.entity.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}