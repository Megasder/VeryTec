package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Cliente;
import com.example.VeryTec.domain.Compra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public interface CompraRepository extends CrudRepository<Compra, Long> {
    Set<Compra> findAll();
    Set<Compra> findByFecha(Date fecha);
    Set<Compra> findByHora(Time hora);
    Set<Compra> findByCantidad(int cantidad);

    @Query(value = "SELECT id_compra,dni,id_producto,fecha,hora,cantidad FROM compra compra WHERE compra.fecha = :fecha and compra.cantidad = :cantidad",nativeQuery = true)
    Set<Compra> findByFechaAndCantidad(@Param("fecha") Date fecha, @Param("cantidad") int cantidad);

    @Query(value = "SELECT id_compra,dni,id_producto,fecha,hora,cantidad FROM compra compra WHERE compra.fecha = :fecha and compra.hora = :hora",nativeQuery = true)
    Set<Compra> findByFechaAndHora(@Param("fecha") Date fecha, @Param("hora") Time hora);

    Set<Compra> findByCliente(Cliente cliente);

}
