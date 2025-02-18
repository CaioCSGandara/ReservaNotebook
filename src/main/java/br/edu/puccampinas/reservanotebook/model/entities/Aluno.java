package br.edu.puccampinas.reservanotebook.model.entities;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;



public class Aluno {
    private String nome;
    private String ra;
    private String email;
    private String telefone;
    private String curso;
    private Integer qtdReservas;
    private LocalDateTime ultimoLogin;
    private LocalDateTime atualizadoEm;

    public Aluno(String nome, String ra, String email, String telefone, String curso, Integer qtdReservas, LocalDateTime ultimoLogin, LocalDateTime atualizadoEm) {
        //todo: fazer validação
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
        this.qtdReservas = qtdReservas;
        this.ultimoLogin = ultimoLogin;
        this.atualizadoEm = atualizadoEm;
    }

    public Aluno() {};

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

    public Integer getQtdReservas() {
        return qtdReservas;
    }

    public void setQtdReservas(Integer qtdReservas) {
        this.qtdReservas = qtdReservas;
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
                ", qtdReservas=" + qtdReservas +
                ", ultimoLogin=" + ultimoLogin +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }


}
