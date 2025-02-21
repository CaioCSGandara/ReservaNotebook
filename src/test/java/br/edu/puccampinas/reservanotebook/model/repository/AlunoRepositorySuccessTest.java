package br.edu.puccampinas.reservanotebook.model.repository;

import br.edu.puccampinas.reservanotebook.model.database.MongoHandler;
import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoRepositorySuccessTest {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String DOMINIO = dotenv.get("DOMINIO");
    private final static AlunoRepository alunoRepository = new AlunoRepository();

    @BeforeEach
    public void limparBanco() {
        try (MongoClient client = MongoHandler.connect()) {
            MongoDatabase db = client.getDatabase("puc");
            MongoCollection<Document> collection = db.getCollection("alunos");
            collection.drop();
        }
    }


    @Test
    public void createAluno() {
        Aluno a1 = new Aluno("José Carlos de Mône", "12345678", "augusto_vfa" + DOMINIO,
                "(19)99414-9552", Curso.MEDICINA_VETERINARIA, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a1);
        Aluno a2 = alunoRepository.findByRa("12345678");
        assertNotNull(a2);
    }

    @Test
    public void listarAlunos() {
        Aluno a1 = new Aluno("Victor Patzi", "22987623", "victor.pld" + DOMINIO,
                "(19)99414-9552", Curso.FARMACIA, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a1);
        Aluno a2   = new Aluno("Guilherme Zansavio", "37019472", "guilherme.zl" + DOMINIO,
                "(19)99414-9552", Curso.NUTRICAO, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a2);
        Aluno a3 = new Aluno("Marcos Paulo", "09467891", "marcos.pcf" + DOMINIO,
                "(19)99414-9552", Curso.TERAPIA_OCUPACIONAL, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a3);

        ArrayList<Aluno> listaAlunos = alunoRepository.findAll();
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno.toString());
        }

        assertFalse(listaAlunos.isEmpty());
    }

    @Test
    public void listarAlunosVazia() {
        ArrayList<Aluno> listaAlunos = alunoRepository.findAll();
        assertTrue(listaAlunos.isEmpty());
    }

    @Test
    public void listarPorRaVazio() {
        Aluno a1 = alunoRepository.findByRa("01010101");
        assertNull(a1);
    }


    @Test
    public void updateCamposPossiveis() {
        Aluno a1 = new Aluno("Josue de Paula", "12345678", "josue_dp" + DOMINIO, "(19)99414-8134",
                Curso.MEDICINA_VETERINARIA, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a1);

        a1.setNome("Josue de Paula Santos");
        a1.setTelefone("(19)99414-8777");
        a1.setCurso(Curso.FONOAUDIOLOGIA);

        alunoRepository.update(a1);

        Aluno a2 = alunoRepository.findByRa("12345678");
        assertEquals("Josue de Paula Santos", a2.getNome());
        assertEquals("(19)99414-8777", a2.getTelefone());
        assertEquals(Curso.FONOAUDIOLOGIA, a2.getCurso());
    }



    @Test
    public void deleteAluno() {
        Aluno a1 = new Aluno("Pedro Junior", "98765432", "pedro.junior" + DOMINIO, "(19)98134-8104",
                Curso.MEDICINA, LocalDateTime.now(), LocalDateTime.now());
        alunoRepository.create(a1);
        alunoRepository.delete(a1.getRa());

        assertNull(alunoRepository.findByRa(a1.getRa()));

    }



}
