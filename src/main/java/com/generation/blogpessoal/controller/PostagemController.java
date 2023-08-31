package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.generation.blogpessoal.model.postagem;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List <postagem>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<postagem> getById(@PathVariable Long id){
        return postagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<postagem> post(@Valid @RequestBody postagem postagem){
        if (temaRepository.existsById(postagem.getTema().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));

        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
    }

    @PutMapping
    public ResponseEntity<postagem> put(@Valid @RequestBody postagem postagem){
        if (postagemRepository.existsById(postagem.getId())){
            if (temaRepository.existsById(postagem.getTema().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem));

            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<postagem> postagem = postagemRepository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        postagemRepository.deleteById(id);
    }

}
