package com.example.VeryTec.service;

import com.example.VeryTec.domain.Usuario;

import java.util.Optional;
import java.util.Set;

public interface UsuarioService {

    Set<Usuario> findAll();
    Set<Usuario> findByRol(int rol);
    Set<Usuario> findByNombre(String nombre);
    Set<Usuario> findByApellidos(String apellidos);
    Optional<Usuario> findByDni(String dni);
    Set<Usuario> findByTelefono(int telefono);
    Set<Usuario> findByEmail(String email);
    Set<Usuario> findByDireccion(String direccion);
    Set<Usuario> findByNumidentificacion(String numidentificacion);

    Set<Usuario> findByNombreAndApellidos(String nombre, String apellidos);
    Set<Usuario> findByNombreAndTelefono(String nombre, int telefono);
    Set<Usuario> findByNombreAndRol(String nombre, int rol);
    Set<Usuario> findByDniAndPassword(String dni, String password);

    Usuario addUsuario(Usuario usuario);
    Usuario modifyUsuario(String dni, Usuario newUsuario);
    void deleteUsuario(String dni);
}
