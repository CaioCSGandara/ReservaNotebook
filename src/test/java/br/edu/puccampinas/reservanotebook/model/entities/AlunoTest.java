package br.edu.puccampinas.reservanotebook.model.entities;


import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import br.edu.puccampinas.reservanotebook.model.exceptions.ValidacaoException;
import br.edu.puccampinas.reservanotebook.model.validators.AlunoValidator;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String DOMINIO = dotenv.get("DOMINIO");

    @Test
    public void nomeValido() {

        assertTrue(AlunoValidator.isNomeValido("José Carlos de Mône"));
        assertTrue(AlunoValidator.isNomeValido("Helena Dall'acqua"));
        assertTrue(AlunoValidator.isNomeValido("Augusto Baía Villas-Boas"));
    }

    @Test
    public void nomeEmBrancoInvalido() {
        assertFalse(AlunoValidator.isNomeValido(""));
    }

    @Test
    public void nomeComNumeroInvalido() {
        assertFalse(AlunoValidator.isNomeValido("J0se Marcos"));
    }

    @Test
    public void nomeSemSobrenomeInvalido() {
        assertFalse(AlunoValidator.isNomeValido("Jefferson"));
    }

    @Test
    public void nomeComCaracterEspecialInvalido() {
        assertFalse(AlunoValidator.isNomeValido("Maria $onia"));
        assertFalse(AlunoValidator.isNomeValido("Maria Sonia."));
        assertFalse(AlunoValidator.isNomeValido("Maria Sonia_"));
    }

    @Test
    public void raValido() {
        assertTrue(AlunoValidator.isRaValido("12345678"));
    }

    @Test
    public void raInvalidoPorLength() {
        assertFalse(AlunoValidator.isRaValido("123456789"));
        assertFalse(AlunoValidator.isRaValido("1234567"));
    }

    @Test
    public void raInvalidoPorCaracter() {
        assertFalse(AlunoValidator.isRaValido("1234567c"));
        assertFalse(AlunoValidator.isRaValido("1234#678"));
    }

    @Test
    public void raInvalidoEmBranco() {
        assertFalse(AlunoValidator.isRaValido(""));
    }

    @Test
    public void emailValido() {
        assertTrue(AlunoValidator.isEmailValido("augusto_vfa"+DOMINIO));
        assertTrue(AlunoValidator.isEmailValido("julio.cgs2"+DOMINIO));
        assertTrue(AlunoValidator.isEmailValido("maria-ts"+DOMINIO));
    }

    @Test
    public void emailInvalidoEmBranco() {
        assertFalse(AlunoValidator.isEmailValido(""));
    }

    @Test
    public void emailInvalidoPorCaracter() {
        assertFalse(AlunoValidator.isEmailValido("junior#"+DOMINIO));
        assertFalse(AlunoValidator.isEmailValido("junior'"+DOMINIO));
    }

    @Test
    public void emailInvalidoPorDominio() {
        assertFalse(AlunoValidator.isEmailValido("junior.santos@gmail.com"));
        assertFalse(AlunoValidator.isEmailValido("junior.santos@outlook.com"));
    }

    @Test
    public void telefoneValido() {
        assertTrue(AlunoValidator.isTelefoneValido("(19)99130-4509"));
    }

    @Test
    public void telefoneInvalidoPorCaracter() {
        assertFalse(AlunoValidator.isTelefoneValido("(19)99130-450#"));
        assertFalse(AlunoValidator.isTelefoneValido("(19)991d0-4503"));
    }

    @Test
    public void telefoneInvalidoEmBranco() {
        assertFalse(AlunoValidator.isTelefoneValido(""));
    }

    @Test
    public void alunoValido() {
        Aluno a1 = new Aluno("José Carlos de Mône", "12345678", "augusto_vfa"+DOMINIO,
                "(19)99130-4509", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now());

        assertEquals("12345678", a1.getRa());
    }

    @Test
    public void exceptionNomeInvalido() {
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                ()-> new Aluno("J0se Marcos", "12345678", "augusto_vfa"+DOMINIO,
                "(19)99130-4509", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now()));

        assertEquals("Erro no builder de Aluno: Nome não pode ser vazio, deve ter sobrenome e não possuir símbolos e nem números", exception.getMessage());
    }

    @Test
    public void exceptionRaInvalido() {
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                ()-> new Aluno("José Carlos de Mône", "1234567", "augusto_vfa"+DOMINIO,
                "(19)99130-4509", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now()));

        assertEquals("Erro no builder de Aluno: RA deve conter 8 dígitos numéricos e não pode ser vazio", exception.getMessage());
    }

    @Test
    public void exceptionEmailInvalido() {
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                ()-> new Aluno("José Carlos de Mône", "12345678", "junior.santos@gmail.com",
                "(19)99130-4509", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now()));

        assertEquals("Erro no builder de Aluno: E-mail não pode ser vazio e deve ser um e-mail acadêmico válido", exception.getMessage());
    }

    @Test
    public void exceptionTelefoneInvalido() {
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                ()-> new Aluno("José Carlos de Mône", "12345678", "jaugusto_vfa"+DOMINIO,
                "(19)99130-450", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now()));

        assertEquals("Erro no builder de Aluno: Telefone não pode ser vazio e deve estar no formato (XX)XXXXX-XXXX", exception.getMessage());
    }













}
