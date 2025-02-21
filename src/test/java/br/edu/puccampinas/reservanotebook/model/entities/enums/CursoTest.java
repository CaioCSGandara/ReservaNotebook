package br.edu.puccampinas.reservanotebook.model.entities.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CursoTest {

    @Test
    public void cursoValido() {
        Curso curso = Curso.encontrarCursoPorString("Medicina");
        assertEquals(Curso.MEDICINA, curso);
    }

    @Test
    public void cursoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> Curso.encontrarCursoPorString("Engenheria Elétrica"));
        assertEquals("Curso não encontrado na lista.", exception.getMessage());
    }
}
