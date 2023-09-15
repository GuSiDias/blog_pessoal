package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o atributo nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O atributo nome é obrigatorio")
    @Email(message = "O atributo usuario tem que receber um e-mail válido")
    private String usuario;

    @NotBlank(message = "O atributo senha é obrigatorio")
    @Size(min = 8, message = "A senha tem que ser no mínimo 8 caracteres")
    private String senha;

    @Size(max = 4000,message = "O link da foto inserida não pode passar dos 4000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<postagem> postagem;

    public usuario(Long id, String nome, String usuario, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
        this.postagem = postagem;
    }

    public usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<com.generation.blogpessoal.model.postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<com.generation.blogpessoal.model.postagem> postagem) {
        this.postagem = postagem;
    }
}
