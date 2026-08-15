package com.wilmer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilmer.backend.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}