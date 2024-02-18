package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface EmpresaRepository  extends CrudRepository<Empresa, String> {

    Set<Empresa> findAll();
    Set<Empresa> findByNombre(String nombre);
    Optional<Empresa> findByCif(String cif);
    Set<Empresa> findByWeb(String web);
    Set<Empresa> findByTelefono(int telefono);
    Set<Empresa> findByEmail(String email);
    Set<Empresa> findByDireccion(String direccion);
    Set<Empresa> findByActividad(String actividad);
    @Query(value = "SELECT cif,nombre,web,telefono,email,direccion,actividad FROM empresa empresa WHERE empresa.nombre = :nombre and empresa.telefono = :telefono",nativeQuery = true)
    Set<Empresa> findByNombreAndTelefono(@Param("nombre") String nombre,@Param("telefono") int telefono);
    @Query(value = "SELECT cif,nombre,web,telefono,email,direccion,actividad FROM empresa empresa WHERE empresa.nombre = :nombre and empresa.email = :email",nativeQuery = true)
    Set<Empresa> findByNombreAndEmail(@Param("nombre") String nombre,@Param("email") String email);
    @Query(value = "SELECT cif,nombre,web,telefono,email,direccion,actividad FROM empresa empresa WHERE empresa.nombre = :nombre and empresa.direccion = :direccion",nativeQuery = true)
    Set<Empresa> findByNombreAndDireccion(@Param("nombre") String nombre,@Param("direccion") String direccion);
}
