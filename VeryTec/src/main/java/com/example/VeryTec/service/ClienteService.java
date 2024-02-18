package com.example.VeryTec.service;

import com.example.VeryTec.domain.Cliente;

import java.util.Optional;
import java.util.Set;

public interface ClienteService {

    Set<Cliente> findAll();
    Set<Cliente> findByNombre(String nombre);
    Set<Cliente> findByApellidos(String apellidos);
    Optional<Cliente> findByDni(String dni);
    Set<Cliente> findByTelefono(int telefono);
    Set<Cliente> findByEmail(String email);
    Set<Cliente> findByHistorial(String historial);
    Set<Cliente> findByDinero(float dinero);

    Set<Cliente> findByNombreAndApellidos(String nombre, String apellidos);
    Set<Cliente> findByNombreAndTelefono(String nombre, int telefono);

    Cliente addCliente(Cliente cliente);
    Cliente modifyCliente(String dni, Cliente newCliente);
    void deleteCliente(String dni);
}
