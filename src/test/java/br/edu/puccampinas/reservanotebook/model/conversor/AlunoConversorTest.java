package br.edu.puccampinas.reservanotebook.model.conversor;

import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;

public class AlunoConversorTest {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String DOMINIO = dotenv.get("DOMINIO");

    @Test
    public void converteAlunoParaDocument() {
        Aluno aluno = new Aluno("Claudio Santos", "12345678", "claudio.anderson"+DOMINIO,
                "(21)98025-9914", Curso.BIOLOGIA, LocalDateTime.now(), LocalDateTime.now());

        Document document = AlunoConversor.alunoToDocument(aluno);

        assertNotNull(document);
    }

    @Test
    public void converteDocumentParaAluno() {
        Document document = new Document()
                .append("nome", "Claudio Santos")
                .append("ra", "12345678")
                .append("email", "claudio.anderson"+DOMINIO)
                .append("telefone", "(21)98025-9914")
                .append("curso", Curso.BIOLOGIA.getNomeFormatado())
                .append("ultimoLogin", new Date())
                .append("atualizadoEm", new Date());

        Aluno aluno = AlunoConversor.documentToAluno(document);

        assertNotNull(aluno);
    }

    @Test
    public void exceptionAlunoToDocument01() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> AlunoConversor.alunoToDocument(null));

        assertEquals("Parâmetro 'aluno' não pode ser nulo (alunoToDocument)", exception.getMessage());
    }

    @Test
    public void exceptionDocumentToAluno01() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> AlunoConversor.documentToAluno(null));

        assertEquals("Parâmetro 'document' não pode ser nulo (documentToAluno).", exception.getMessage());
    }
}
