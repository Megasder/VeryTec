package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

    Set<Cliente> findAll();
    Set<Cliente> findByTelefono(int telefono);
    Set<Cliente> findByNombre(String nombre);
    Set<Cliente> findByApellidos(String apellidos);
    Optional<Cliente> findByDni(String dni);
    Set<Cliente> findByEmail(String email);
    Set<Cliente> findByHistorial(String historial);
    Set<Cliente> findByDinero(float dinero);
    @Query(value = "SELECT dni,nombre,apellidos,telefono,email,password,historial,dinero FROM cliente cliente WHERE cliente.nombre = :nombre and cliente.apellidos = :apellidos",nativeQuery = true)
    Set<Cliente> findByNombreAndApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);
    @Query(value = "SELECT dni,nombre,apellidos,telefono,email,password,historial,dinero FROM cliente cliente WHERE cliente.nombre = :nombre and cliente.telefono = :telefono",nativeQuery = true)
    Set<Cliente> findByNombreAndTelefono(@Param("nombre") String nombre, @Param("telefono") int telefono);

}
