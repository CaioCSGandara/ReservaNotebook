package br.edu.puccampinas.reservanotebook.model.entities;

import br.edu.puccampinas.reservanotebook.model.entities.enums.MotivoReserva;

import java.time.ZonedDateTime;

public class Reserva {
    private Aluno aluno;
    private Notebook notebook;
    private MotivoReserva motivo;
    private ZonedDateTime horarioInicio;
    private ZonedDateTime horarioFim;
    private boolean ativa;

    public Reserva(Aluno aluno, Notebook notebook, MotivoReserva motivo, ZonedDateTime horarioInicio, ZonedDateTime horarioFim, boolean ativa) {
        //todo: validar campos
        this.aluno = aluno;
        this.notebook = notebook;
        this.motivo = motivo;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.ativa = ativa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public MotivoReserva getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoReserva motivo) {
        this.motivo = motivo;
    }

    public ZonedDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(ZonedDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public ZonedDateTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(ZonedDateTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    //todo: métodos cabíveis
}
