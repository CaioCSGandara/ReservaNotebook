package br.edu.puccampinas.reservanotebook.model.entities;


import java.util.Map;

public class Notebook {
    private String modelo;
    private String patrimonio;
    private Map<String, Integer> historicoUso;
    private boolean emUso;

    public Notebook(String modelo, String patrimonio, Map<String, Integer> historicoUso, boolean emUso) {
        //todo: validação dos campos
        this.modelo = modelo;
        this.patrimonio = patrimonio;
        this.historicoUso = historicoUso;
        this.emUso = emUso;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Map<String, Integer> getHistoricoUso() {
        return historicoUso;
    }

    public void setHistoricoUso(Map<String, Integer> historicoUso) {
        this.historicoUso = historicoUso;
    }

    public boolean isEmUso() {
        return emUso;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    //todo: métodos cabíveis
}
