package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<usuario,Long> {

    public Optional<usuario> findByUsuario(String usuario);
}
