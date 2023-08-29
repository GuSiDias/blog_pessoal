package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.blogpessoal.model.postagem;

public interface PostagemRepository extends JpaRepository<postagem,Long> {
}
