package com.proveedores.security.repository;

import com.proveedores.security.entity.Rol;
import com.proveedores.security.entity.RolTipo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    public Optional<Rol> findByNombre(String nombre);

    public List<Rol> findByRolTipo(RolTipo rolTipo);
}
