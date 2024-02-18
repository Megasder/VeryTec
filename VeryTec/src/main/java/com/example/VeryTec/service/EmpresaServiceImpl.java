package com.example.VeryTec.service;

import com.example.VeryTec.domain.Empresa;
import com.example.VeryTec.exception.EmpresaNotFoundException;
import com.example.VeryTec.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Set<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Set<Empresa> findByNombre(String nombre) {
        return empresaRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Empresa> findByCif(String cif) {
        return empresaRepository.findByCif(cif);
    }

    @Override
    public Set<Empresa> findByWeb(String web) {
        return empresaRepository.findByWeb(web);
    }

    @Override
    public Set<Empresa> findByTelefono(int telefono) {
        return empresaRepository.findByTelefono(telefono);
    }

    @Override
    public Set<Empresa> findByEmail(String email) {
        return empresaRepository.findByEmail(email);
    }

    @Override
    public Set<Empresa> findByDireccion(String direccion) {
        return empresaRepository.findByDireccion(direccion);
    }

    @Override
    public Set<Empresa> findByActividad(String actividad) {
        return empresaRepository.findByActividad(actividad);
    }

    @Override
    public Empresa addEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Set<Empresa> findByNombreAndTelefono(String nombre, int telefono) {

        return empresaRepository.findByNombreAndTelefono(nombre, telefono);
    }

    @Override
    public Set<Empresa> findByNombreAndEmail(String nombre, String email) {

        return empresaRepository.findByNombreAndEmail(nombre, email);
    }

    @Override
    public Set<Empresa> findByNombreAndDireccion(String nombre, String direccion) {

        return empresaRepository.findByNombreAndDireccion(nombre, direccion);
    }

    @Override
    public Empresa modifyEmpresa(String cif, Empresa newEmpresa) {
        Empresa empresa = empresaRepository.findByCif(cif)
                .orElseThrow(() -> new EmpresaNotFoundException(cif));
        newEmpresa.setCif(empresa.getCif());
        return empresaRepository.save(newEmpresa);
    }

    @Override
    public void deleteEmpresa(String cif) {
        empresaRepository.findByCif(cif)
                .orElseThrow(() -> new EmpresaNotFoundException(cif));
        empresaRepository.deleteById(cif);
    }
}
