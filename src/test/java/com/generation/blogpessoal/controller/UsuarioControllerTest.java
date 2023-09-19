package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){
        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new usuario(0L, "root", "root@root.com", "", null));
    }

    @Test
    @DisplayName("Vou cadastrar um usuario")
    public void deveCriarUmUsuario(){

        HttpEntity<usuario> corpoRequsicao = new HttpEntity<usuario>(new usuario(0L, "Matheus Bergamo", "matheus_bergamo@enois.com.br","12345678", null));

        ResponseEntity<usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequsicao, usuario.class);

        assertEquals(HttpStatus.CREATED,corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Verificar a duplicidade do usuário")
    public void naoDuplicar(){

        usuarioService.cadastrarUsuario(new usuario(0L, "Mauricio Freire","mauricio_freire@gmail.com","123456789", null));

        HttpEntity<usuario> corpoRequisicao = new HttpEntity<usuario>(new usuario(0L, "Mauricio Freire","mauricio_freire@gmail.com","123456789", null));
        ResponseEntity<usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST,corpoResposta.getStatusCode());
    }


}
