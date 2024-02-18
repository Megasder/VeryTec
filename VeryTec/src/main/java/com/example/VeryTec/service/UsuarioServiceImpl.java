package com.example.VeryTec.service;

import com.example.VeryTec.domain.Usuario;
import com.example.VeryTec.exception.UsuarioNotFoundException;
import com.example.VeryTec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Set<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Set<Usuario> findByRol(int rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public Set<Usuario> findByNombreAndApellidos(String nombre, String apellidos) {
        return usuarioRepository.findByNombreAndApellidos(nombre, apellidos);
    }

    @Override
    public Set<Usuario> findByNombreAndTelefono(String nombre, int telefono){
        return usuarioRepository.findByNombreAndTelefono(nombre, telefono);
    }

    @Override
    public Set<Usuario> findByNombreAndRol(String nombre, int rol){
        return usuarioRepository.findByNombreAndRol(nombre, rol);
    }

    @Override
    public Set<Usuario> findByDniAndPassword(String dni, String password) {
        return usuarioRepository.findByDniAndPassword(dni, password);
    }

    @Override
    public Set<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public Set<Usuario> findByApellidos(String apellidos) {
        return usuarioRepository.findByApellidos(apellidos);
    }

    @Override
    public Optional<Usuario> findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    @Override
    public Set<Usuario> findByTelefono(int telefono) {
        return usuarioRepository.findByTelefono(telefono);
    }

    @Override
    public Set<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Set<Usuario> findByDireccion(String direccion) {
        return usuarioRepository.findByDireccion(direccion);
    }

    @Override
    public Set<Usuario> findByNumidentificacion(String numidentificacion) {
        return usuarioRepository.findByNumidentificacion(numidentificacion);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modifyUsuario(String dni, Usuario newUsuario) {
        Usuario usuario = usuarioRepository.findByDni(dni)
                .orElseThrow(() -> new UsuarioNotFoundException(dni));
        newUsuario.setDni(usuario.getDni());
        return usuarioRepository.save(newUsuario);
    }

    @Override
    public void deleteUsuario(String dni) {
        Usuario usuario = usuarioRepository.findByDni(dni)
                .orElseThrow(() -> new UsuarioNotFoundException(dni));

        if (usuario.getRol() != 1){
            usuarioRepository.deleteById(dni);
        }else{
            System.err.println("No se puede eliminar superadmin");
        }
    }
}
