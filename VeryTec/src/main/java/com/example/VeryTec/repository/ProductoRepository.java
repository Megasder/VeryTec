package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductoRepository  extends CrudRepository<Producto, Long> {

    Set<Producto> findAll();
    Set<Producto> findByNombre(String nombre);
    Set<Producto> findByCategoria(String categoria);
    Set<Producto> findByPrecio(float precio);
    Set<Producto> findByDescuento(int descuento);
    Set<Producto> findByCantidad(int cantidad);

    @Query(value = "SELECT id_producto,nombre,categoria,precio,iva,descuento,cantidad,empresa_cif FROM Producto producto WHERE producto.nombre = :nombre and producto.precio <= :precio",nativeQuery = true)
    Set<Producto> findByNombreAndPrecio(@Param("nombre") String nombre,@Param("precio") float precio);
    @Query(value = "SELECT id_producto,nombre,categoria,precio,iva,descuento,cantidad,empresa_cif FROM Producto producto WHERE producto.nombre = :nombre and producto.categoria = :categoria",nativeQuery = true)
    Set<Producto> findByNombreAndCategoria(@Param("nombre") String nombre,@Param("categoria") String categoria);
}