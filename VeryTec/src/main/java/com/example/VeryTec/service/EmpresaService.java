package com.example.VeryTec.service;

import com.example.VeryTec.domain.Empresa;

import java.util.Optional;
import java.util.Set;

public interface EmpresaService {

    Set<Empresa> findAll();
    Set<Empresa> findByNombre(String nombre);
    Optional<Empresa> findByCif(String cif);
    Set<Empresa> findByWeb(String web);
    Set<Empresa> findByTelefono(int telefono);
    Set<Empresa> findByEmail(String email);
    Set<Empresa> findByDireccion(String direccion);
    Set<Empresa> findByActividad(String actividad);

    Set<Empresa> findByNombreAndTelefono(String nombre, int telefono);
    Set<Empresa> findByNombreAndEmail(String nombre, String email);
    Set<Empresa> findByNombreAndDireccion(String nombre, String direccion);

    Empresa addEmpresa(Empresa empresa);
    Empresa modifyEmpresa(String cif, Empresa newEmpresa);
    void deleteEmpresa(String cif);
}

