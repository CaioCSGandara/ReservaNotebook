package br.edu.puccampinas.reservanotebook.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;


public class Aluno implements Cloneable{
    private String nome;
    private String ra;
    private String email;
    private String telefone;
    private String curso;
    private LocalDateTime ultimoLogin;
    private LocalDateTime atualizadoEm;

    //todo: 1-validação, 2-testes, 3-documentação
    public Aluno(String nome, String ra, String email, String telefone, String curso, LocalDateTime ultimoLogin, LocalDateTime atualizadoEm) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
        this.ultimoLogin = ultimoLogin;
        this.atualizadoEm = atualizadoEm;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }


    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    //todo: reescrever métodos obrigatórios


    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", ra='" + ra + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", curso='" + curso + '\'' +
                ", ultimoLogin=" + ultimoLogin +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(nome, aluno.nome) && Objects.equals(ra, aluno.ra) && Objects.equals(email, aluno.email) && Objects.equals(telefone, aluno.telefone) && Objects.equals(curso, aluno.curso) && Objects.equals(ultimoLogin, aluno.ultimoLogin) && Objects.equals(atualizadoEm, aluno.atualizadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ra, email, telefone, curso, ultimoLogin, atualizadoEm);
    }

    public Aluno(Aluno aluno) {
        this.nome = aluno.getNome();
        this.ra = aluno.getRa();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.curso = aluno.getCurso();
        this.ultimoLogin = aluno.getUltimoLogin();
        this.atualizadoEm = aluno.getAtualizadoEm();
    }


    @Override
    public Object clone() {
        Aluno copia = null;
        try {
            copia = new Aluno(this);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return copia;
    }
}


