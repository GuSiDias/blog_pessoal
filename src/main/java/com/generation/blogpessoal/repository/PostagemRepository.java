package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.blogpessoal.model.postagem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostagemRepository extends JpaRepository<postagem,Long> {
    public List<postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
