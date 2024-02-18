package com.example.VeryTec.service;

import com.example.VeryTec.domain.Cliente;
import com.example.VeryTec.domain.Compra;
import com.example.VeryTec.exception.CompraNotFoundException;
import com.example.VeryTec.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public Set<Compra> findAll() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> findByIdCompra(long idCompra) {
        return compraRepository.findById(idCompra);
    }

    @Override
    public Set<Compra> findByFecha(Date fecha) {
        return compraRepository.findByFecha(fecha);
    }

    @Override
    public Set<Compra> findByHora(Time hora) {
        return compraRepository.findByHora(hora);
    }

    @Override
    public Set<Compra> findByCantidad(int cantidad) {
        return compraRepository.findByCantidad(cantidad);
    }

    @Override
    public Set<Compra> findByFechaAndHora(Date fecha, Time hora) {
        return compraRepository.findByFechaAndHora(fecha, hora);
    }

    @Override
    public Set<Compra> findByCantidadAndFecha(int cantidad, Date fecha) {
        return compraRepository.findByFechaAndCantidad(fecha, cantidad);
    }

    @Override
    public Compra addCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra modifyCompra(long idCompra, Compra newCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new CompraNotFoundException(idCompra));
        newCompra.setId_compra(compra.getId_compra());
        return compraRepository.save(newCompra);
    }

    @Override
    public void deleteCompra(long idCompra) {
        compraRepository.findById(idCompra)
                .orElseThrow(() -> new CompraNotFoundException(idCompra));
        compraRepository.deleteById(idCompra);
    }

    @Override
    public Set<Compra> findByDniCliente(String dniCliente) {
        Cliente cliente = new Cliente();
        cliente.setDni(dniCliente);
        return compraRepository.findByCliente(cliente);
    }


}
