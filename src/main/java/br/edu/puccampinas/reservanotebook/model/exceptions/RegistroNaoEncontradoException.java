package br.edu.puccampinas.reservanotebook.model.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
