package br.edu.puccampinas.reservanotebook.model.entities;
import br.edu.puccampinas.reservanotebook.model.entities.enums.MotivoReserva;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reserva {
    private Map<String, String> aluno = new HashMap<String, String>();
    private Map<String, String> notebook = new HashMap<String, String>();
    private MotivoReserva motivo;
    private ZonedDateTime horarioInicio;
    private ZonedDateTime horarioFim;
    private boolean ativa;

    public Reserva(Aluno aluno, Notebook notebook, MotivoReserva motivo, ZonedDateTime horarioInicio, ZonedDateTime horarioFim, boolean ativa) {
        setAluno(aluno);
        setNotebook(notebook);
        this.motivo = motivo;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.ativa = ativa;
    }

    public Map<String, String> getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno.put("nome", aluno.getNome());
        this.aluno.put("ra", aluno.getRa());
        this.aluno.put("email", aluno.getEmail());
        this.aluno.put("telefone", aluno.getTelefone());
        this.aluno.put("curso", aluno.getCurso().getNomeFormatado());
    }

    public Map<String, String> getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook.put("modelo", notebook.getModelo());
        this.notebook.put("patrimonio", notebook.getPatrimonio());
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
