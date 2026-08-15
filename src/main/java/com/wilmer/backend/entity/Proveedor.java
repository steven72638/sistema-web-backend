package com.wilmer.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String foto;

    @Column(length = 500)
    private String descripcion;


    @ElementCollection
    @CollectionTable(name = "proveedor_productos", joinColumns = @JoinColumn(name = "proveedor_id"))
    @Column(name = "producto")
    @OrderColumn(name = "orden")
    private List<String> productosTop;

    @Column(name = "red_social")
    private String redSocial;
}