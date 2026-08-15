package com.wilmer.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.wilmer.backend.entity.Articulo;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    @Query("SELECT a FROM Articulo a WHERE " +
            "LOWER(a.codigo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
            "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
            "LOWER(a.categoria) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Articulo> buscar(@Param("texto") String texto);
}