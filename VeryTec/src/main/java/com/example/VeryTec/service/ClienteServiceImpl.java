package com.example.VeryTec.service;

import com.example.VeryTec.domain.Cliente;
import com.example.VeryTec.exception.ClienteNotFoundException;
import com.example.VeryTec.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Set<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Set<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Set<Cliente> findByApellidos(String apellidos) {
        return clienteRepository.findByApellidos(apellidos);
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Set<Cliente> findByTelefono(int telefono) {
        return clienteRepository.findByTelefono(telefono);
    }

    @Override
    public Set<Cliente> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public Set<Cliente> findByHistorial(String historial) {
        return clienteRepository.findByHistorial(historial);
    }

    @Override
    public Set<Cliente> findByDinero(float dinero) {
        return clienteRepository.findByDinero(dinero);
    }

    @Override
    public Set<Cliente> findByNombreAndApellidos(String nombre, String apellidos) {
        return clienteRepository.findByNombreAndApellidos(nombre, apellidos);
    }

    @Override
    public Set<Cliente> findByNombreAndTelefono(String nombre, int telefono) {
        return clienteRepository.findByNombreAndTelefono(nombre, telefono);
    }

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modifyCliente(String dni, Cliente newCliente) {
        Cliente cliente = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ClienteNotFoundException(dni));
        newCliente.setDni(cliente.getDni());
        return clienteRepository.save(newCliente);
    }

    @Override
    public void deleteCliente(String dni) {
        clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ClienteNotFoundException(dni));
        clienteRepository.deleteById(dni);
    }
}
