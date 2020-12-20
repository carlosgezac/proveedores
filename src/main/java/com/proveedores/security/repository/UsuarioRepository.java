package com.proveedores.security.repository;

import com.proveedores.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUserName(String userName);

    public boolean existsByUserName(String userName);

    public boolean existsByEmail(String email);

}
