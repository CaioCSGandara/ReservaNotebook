package br.edu.puccampinas.reservanotebook.model.entities.enums;

public enum Curso {
    BIOLOGIA("Biologia"),
    BIOMEDICINA("Biomedicina"),
    ENFERMAGEM("Enfermagem"),
    FARMACIA("Farmácia"),
    FISIOTERAPIA("Fisioterapia"),
    FONOAUDIOLOGIA("Fonoaudiologia"),
    MEDICINA("Medicina"),
    MEDICINA_VETERINARIA("Medicina Veterinária"),
    NUTRICAO("Nutrição"),
    ODONTOLOGIA("Odontologia"),
    PSICOLOGIA("Psicologia"),
    TERAPIA_OCUPACIONAL("Terapia Ocupacional");

    private String nomeFormatado;

    Curso(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }
}
