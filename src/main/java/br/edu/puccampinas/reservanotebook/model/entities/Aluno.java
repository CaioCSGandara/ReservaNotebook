package br.edu.puccampinas.reservanotebook.model.entities;

import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;

public class Aluno {
    private String nome;
    private String ra;
    private String email;
    private String telefone;
    private Curso curso;

    public Aluno(String nome, String ra, String email, String telefone, Curso curso) {
        //todo: fazer validação
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    //todo: reescrever métodos obrigatórios
}
