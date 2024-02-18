package com.example.VeryTec.service;

import com.example.VeryTec.domain.Compra;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface CompraService {
    Set<Compra> findAll();
    Optional<Compra> findByIdCompra(long idCompra);
    Set<Compra> findByFecha(Date fecha);
    Set<Compra> findByHora(Time hora);
    Set<Compra> findByCantidad(int cantidad);
    Set<Compra> findByFechaAndHora(Date fecha , Time hora);
    Set<Compra> findByCantidadAndFecha(int cantidad, Date fecha);

    Compra addCompra(Compra compra);
    Compra modifyCompra(long idCompra, Compra newCompra);
    void deleteCompra(long idCompra);

    Set<Compra> findByDniCliente(String dniCliente);
}
