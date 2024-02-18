package com.example.VeryTec.repository;

import com.example.VeryTec.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Set<Usuario> findAll();
    Set<Usuario> findByRol(int rol);
    Optional<Usuario> findByDni(String dni);
    Set<Usuario> findByTelefono(int telefono);
    Set<Usuario> findByNombre(String nombre);
    Set<Usuario> findByApellidos(String apellidos);
    Set<Usuario> findByEmail(String email);
    Set<Usuario> findByDireccion(String direccion);
    Set<Usuario> findByNumidentificacion(String numidentificacion);
    @Query(value = "SELECT dni,password,rol,descripcion,nombre,apellidos,telefono,email,direccion,numidentificacion FROM usuario usuario WHERE usuario.nombre = :nombre and usuario.apellidos = :apellidos",nativeQuery = true)
    Set<Usuario> findByNombreAndApellidos(@Param("nombre") String nombre,@Param("apellidos") String apellidos);
    @Query(value = "SELECT dni,password,rol,descripcion,nombre,apellidos,telefono,email,direccion,numidentificacion FROM usuario usuario WHERE usuario.nombre = :nombre and usuario.telefono = :telefono",nativeQuery = true)
    Set<Usuario> findByNombreAndTelefono(@Param("nombre") String nombre,@Param("telefono") int telefono);
    @Query(value = "SELECT dni,password,rol,descripcion,nombre,apellidos,telefono,email,direccion,numidentificacion FROM usuario usuario WHERE usuario.nombre = :nombre and usuario.rol = :rol",nativeQuery = true)
    Set<Usuario> findByNombreAndRol(@Param("nombre") String nombre,@Param("rol") int rol);
    @Query(value = "SELECT dni,password,rol,descripcion,nombre,apellidos,telefono,email,direccion,numidentificacion FROM Usuario usuario WHERE Usuario.dni = :dni and Usuario.password = :password",nativeQuery = true)
    Set<Usuario> findByDniAndPassword(@Param("dni") String dni,@Param("password") String password);
}

