package com.proyecto.v.repository;

import com.proyecto.v.entity.Usuario;
import com.proyecto.v.entity.Usuario.UsuarioId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioId> {
    Optional<Usuario> findByLogin(String login);
    boolean existsByLoginAndApikey(String login, String apikey);
}