package com.example.VeryTec.service;

import com.example.VeryTec.domain.Producto;

import java.util.Optional;
import java.util.Set;

public interface ProductoService {

    Set<Producto> findAll();
    Optional<Producto> findByIdProducto(long idProducto);
    Set<Producto> findByNombre(String nombre);
    Set<Producto> findByCategoria(String categoria);
    Set<Producto> findByPrecio(float precio);
    Set<Producto> findByDescuento(int descuento);
    Set<Producto> findByCantidad(int cantidad);
    Set<Producto> findByNombreAndPrecio(String nombre, float precio);
    Set<Producto> findByNombreAndCategoria(String nombre, String categoria);

    Producto addProducto(Producto producto);
    Producto modifyProducto(long idProducto, Producto newProducto);
    void deleteProducto(long idProducto);
}

