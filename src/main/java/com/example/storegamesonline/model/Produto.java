package com.example.storegamesonline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do jogo não pode ser vazio")
    @Size(min = 2, max = 255,message = "O nomem deve ter entre 2 e 255 caracteres")
    private String nome;

    @NotNull(message = "O nome do jogo não pode ser vazio")
    private double preco;

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
