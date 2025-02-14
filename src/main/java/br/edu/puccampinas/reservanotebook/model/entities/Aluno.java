package br.edu.puccampinas.reservanotebook.model.entities;

import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

public class Aluno {
    private ObjectId id;
    private String nome;
    private String ra;
    private String email;
    private String telefone;
    private Curso curso;
    private int qtdReservas;
    private ZonedDateTime ultimaReserva;

    public Aluno(ObjectId id, String nome, String ra, String email, String telefone, Curso curso, int qtdReservas, ZonedDateTime ultimaReserva) {
        //todo: fazer validação
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
        this.qtdReservas = qtdReservas;
        this.ultimaReserva = ultimaReserva;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public int getQtdReservas() {
        return qtdReservas;
    }

    public void setQtdReservas(int qtdReservas) {
        this.qtdReservas = qtdReservas;
    }

    public ZonedDateTime getUltimaReserva() {
        return ultimaReserva;
    }

    public void setUltimaReserva(ZonedDateTime ultimaReserva) {
        this.ultimaReserva = ultimaReserva;
    }

    //todo: reescrever métodos obrigatórios
}
