package com.proveedores.security.service;

import com.proveedores.security.entity.Rol;
import com.proveedores.security.entity.RolTipo;
import com.proveedores.security.entity.UsuarioTipo;
import com.proveedores.security.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> findByNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    public List<Rol> findByRolTipo(RolTipo rolTipo) {
        return rolRepository.findByRolTipo(rolTipo);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }
}
