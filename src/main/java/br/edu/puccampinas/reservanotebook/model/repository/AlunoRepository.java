package br.edu.puccampinas.reservanotebook.model.repository;

import br.edu.puccampinas.reservanotebook.model.conversor.AlunoConversor;
import br.edu.puccampinas.reservanotebook.model.database.CRUD;
import br.edu.puccampinas.reservanotebook.model.database.MongoHandler;
import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.model.exceptions.RegistroNaoEncontradoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.eq;


public class AlunoRepository implements CRUD<Aluno> {

    private static AlunoRepository alunoRepository;
    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private static String dbName = dotenv.get("DB_NAME");
    private final MongoClient client = MongoHandler.getInstance().getClient();
    private final MongoDatabase db = client.getDatabase(dbName);
    private final MongoCollection<Document> collection = db.getCollection("alunos");

    private AlunoRepository() {

    }

    public static AlunoRepository getInstance() {
        if(alunoRepository==null) {
            synchronized (AlunoRepository.class) {
                if(alunoRepository==null) {
                    alunoRepository =  new AlunoRepository();
                }
            }
        }
        return alunoRepository;
    }

    @Override
    public void create(Aluno obj) {

        if (obj == null) throw new IllegalArgumentException("Parâmetro 'obj' (aluno) não pode ser nulo (create).");
        if(findByRa(obj.getRa())!=null) throw new RegistroNaoEncontradoException("Este RA já foi cadastrado");

        Document document = AlunoConversor.alunoToDocument(obj);
        InsertOneResult result = collection.insertOne(document);

        System.out.println("Aluno registrado com _id: " + result.getInsertedId());

    }

    @Override
    public ArrayList<Aluno> findAll() {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();

        Bson excludeIdProjection = Projections.excludeId();
        for (Document document : collection.find().projection(excludeIdProjection)) {
            listaAlunos.add(AlunoConversor.documentToAluno(document));
        }

        return listaAlunos;
    }

    @Override
    public void update(Aluno obj) {
        if(obj==null) throw new IllegalArgumentException("Parâmetro 'obj' não pode ser nulo (update).");
        if(findByRa(obj.getRa())==null) throw new RegistroNaoEncontradoException("RA do documento a ser alterado não encontrado.");

        Bson updates = Updates.combine(
                Updates.set("nome", obj.getNome()),
                Updates.set("telefone", obj.getTelefone()),
                Updates.set("curso", obj.getCurso().getNomeFormatado()),
                Updates.set("atualizadoEm", LocalDateTime.now()));

        UpdateResult result = collection.updateOne(eq("ra", obj.getRa()), updates);
        System.out.println("Documentos encontrados: " + result.getMatchedCount() + ", documentos alterados: " + result.getModifiedCount());

    }


    @Override
    public void delete(String query) {
        if(query==null) throw new IllegalArgumentException("Parâmetro 'query' (RA) não pode ser nulo (delete).");
        if (findByRa(query)==null) throw new RegistroNaoEncontradoException("RA para deletar documento não encontrado.");

        DeleteResult result = collection.deleteOne(eq("ra", query));
        System.out.println("Documentos encontrados: " + result.getDeletedCount());


    }

    public Aluno findByRa(String ra) {
        if(ra==null) throw new IllegalArgumentException("Parâmetro 'ra' não pode ser nulo (findByRa).");
        Bson excludeIdProjection = Projections.excludeId();
        Document document = collection.find(eq("ra", ra)).projection(excludeIdProjection).first();

        if(document==null) return null;

        return AlunoConversor.documentToAluno(document);
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDb() {
        return db;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
}
