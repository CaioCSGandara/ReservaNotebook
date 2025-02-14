package br.edu.puccampinas.reservanotebook.model.entities.enums;

public enum MotivoReserva {
    TRABALHO("Trabalho acadêmico"),
    AULA ("Uso em aula"),
    ESTUDO("Estudo");

    String motivoFormato;

    MotivoReserva(String motivoFormatado) {
        this.motivoFormato = motivoFormatado;
    }

    public String getMotivoFormato() {
        return motivoFormato;
    }

    public void setMotivoFormato(String motivoFormato) {
        this.motivoFormato = motivoFormato;
    }
}
