package com.example.VeryTec.service;

import com.example.VeryTec.domain.Producto;
import com.example.VeryTec.exception.ProductoNotFoundException;
import com.example.VeryTec.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Set<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findByIdProducto(long idProducto) {
        return productoRepository.findById(idProducto);
    }

    @Override
    public Set<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public Set<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public Set<Producto> findByPrecio(float precio) {
        return productoRepository.findByPrecio(precio);
    }

    @Override
    public Set<Producto> findByDescuento(int descuento) {
        return productoRepository.findByDescuento(descuento);
    }

    @Override
    public Set<Producto> findByCantidad(int cantidad) {
        return productoRepository.findByCantidad(cantidad);
    }

    @Override
    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto modifyProducto(long idProducto, Producto newProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ProductoNotFoundException(idProducto));
        newProducto.setIdProducto(producto.getIdProducto());
        return productoRepository.save(newProducto);
    }

    @Override
    public void deleteProducto(long idProducto) {
        productoRepository.findById(idProducto)
                .orElseThrow(() -> new ProductoNotFoundException(idProducto));
        productoRepository.deleteById(idProducto);
    }

    @Override
    public Set<Producto> findByNombreAndPrecio(String nombre, float precio) {

        return productoRepository.findByNombreAndPrecio(nombre, precio);
    }

    @Override
    public Set<Producto> findByNombreAndCategoria(String nombre, String categoria) {

        return productoRepository.findByNombreAndCategoria(nombre, categoria);
    }

}
