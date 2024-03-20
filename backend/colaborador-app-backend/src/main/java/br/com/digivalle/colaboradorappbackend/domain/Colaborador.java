package br.com.digivalle.colaboradorappbackend.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Colaborador", uniqueConstraints = {@UniqueConstraint(columnNames = "nome")})
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ColaboradorCargo cargo;
    private String senha;
    private int senhaScore;
    private String complexidade;

    public Colaborador() {
    }
    public Colaborador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

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

    public ColaboradorCargo getCargo() {
        return cargo;
    }

    public void setCargo(ColaboradorCargo cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSenhaScore() {
        return senhaScore;
    }

    public void setSenhaScore(int senhaScore) {
        this.senhaScore = senhaScore;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colaborador that = (Colaborador) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", senha='" + senha + '\'' +
                ", senhaScore=" + senhaScore +
                ", complexidade='" + complexidade + '\'' +
                '}';
    }
}

