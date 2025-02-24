package br.edu.puccampinas.reservanotebook.model.repository;

import br.edu.puccampinas.reservanotebook.model.database.MongoHandler;
import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import br.edu.puccampinas.reservanotebook.model.exceptions.RegistroNaoEncontradoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class AlunoRepositoryExceptionTest {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String DOMINIO = dotenv.get("DOMINIO");
    private final static AlunoRepository alunoRepository = AlunoRepository.getInstance();


    @BeforeEach
    public void limparBanco() {
            MongoCollection<Document> collection = alunoRepository.getCollection();
            collection.drop();

    }

    @AfterAll
    public static void fecharConexao() {
        alunoRepository.getClient().close();
    }


    @Test
    public void createAlunoNull() {
        Aluno a1 = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> alunoRepository.create(a1));

        assertEquals("Parâmetro 'obj' (aluno) não pode ser nulo (create).", exception.getMessage());

    }

    @Test
    public void createAlunoRaRepetido() {
        Aluno a1 = new Aluno("José Carlos de Mône", "12345678", "augusto_vfa" + DOMINIO,
                "(19)99414-9552", Curso.ODONTOLOGIA, LocalDateTime.now(), LocalDateTime.now());

        Aluno a2 = new Aluno("Maria Andressa", "12345678", "maria_andressa" + DOMINIO,
                "(19)93511-9552", Curso.ODONTOLOGIA, LocalDateTime.now(), LocalDateTime.now());

        alunoRepository.create(a1);

        RegistroNaoEncontradoException exception = assertThrows(RegistroNaoEncontradoException.class,
                ()-> alunoRepository.create(a2));

        assertEquals("Este RA já foi cadastrado", exception.getMessage());

    }


    @Test
    public void updateAlunoNull() {
        Aluno a1 = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> alunoRepository.update(a1));
        assertEquals("Parâmetro 'obj' não pode ser nulo (update).", exception.getMessage());
    }

    @Test
    public void updateRaNaoEncontrado() {
        Aluno a1 = new Aluno("Marcos de Paula", "93718399", "jorge_augusto_vfa" + DOMINIO, "(19)99414-8104",
                Curso.MEDICINA_VETERINARIA, LocalDateTime.now(), LocalDateTime.now());
        RegistroNaoEncontradoException exception = assertThrows(RegistroNaoEncontradoException.class,
                ()-> alunoRepository.update(a1));
        assertEquals("RA do documento a ser alterado não encontrado.", exception.getMessage());
    }

    @Test
    public void deleteNullQuery() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> alunoRepository.delete(null));

        assertEquals("Parâmetro 'query' (RA) não pode ser nulo (delete).", exception.getMessage());
    }

    @Test
    public void deleteRaNaoEncontrado() {
        RegistroNaoEncontradoException exception = assertThrows(RegistroNaoEncontradoException.class,
                ()-> alunoRepository.delete("66666666"));

        assertEquals("RA para deletar documento não encontrado.", exception.getMessage());
    }



    @Test
    public void findByRaNaoExistente() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> alunoRepository.findByRa(null));
        assertEquals("Parâmetro 'ra' não pode ser nulo (findByRa).", exception.getMessage());
    }
}
