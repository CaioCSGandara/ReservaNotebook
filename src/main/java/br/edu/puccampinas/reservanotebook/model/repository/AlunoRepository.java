package br.edu.puccampinas.reservanotebook.model.repository;

import br.edu.puccampinas.reservanotebook.model.conversor.AlunoConversor;
import br.edu.puccampinas.reservanotebook.model.database.CRUD;
import br.edu.puccampinas.reservanotebook.model.database.MongoHandler;
import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.eq;


public class AlunoRepository implements CRUD<Aluno> {

    @Override
    public void create(Aluno obj) {

        try(MongoClient client = MongoHandler.connect())
        {
            MongoDatabase db = client.getDatabase(MongoHandler.getDbName());
            MongoCollection<Document> collection = db.getCollection("alunos");
            Document document = AlunoConversor.alunoToDocument(obj);
            InsertOneResult result = collection.insertOne(document);

            System.out.printf("Aluno registrado com _id: " + result.getInsertedId());
        }
        catch (MongoException e) {
            e.printStackTrace();
            throw new MongoException("Erro ao cadastrar novo aluno.", e);

        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro interno no servidor. Tente novamente mais tarde.", e);
        }
    }

    @Override
    public ArrayList<Aluno> findAll() {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        try(MongoClient client = MongoHandler.connect()) {
            MongoDatabase db = client.getDatabase(MongoHandler.getDbName());
            Bson excludeIdProjection = Projections.excludeId();
            MongoCollection<Document> collection= db.getCollection("alunos");
            for(Document document: collection.find().projection(excludeIdProjection)) {
                listaAlunos.add(AlunoConversor.documentToAluno(document));
                System.out.println(document.toJson());
            }
        }
        catch(MongoException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao exibir alunos", e);
        }
        catch(RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro interno no servidor. Tente novamente mais tarde.", e);
        }
        return listaAlunos;
    }

    @Override
    public void update(Aluno obj) {
        try(MongoClient client = MongoHandler.connect()) {
            MongoDatabase db = client.getDatabase(MongoHandler.getDbName());
            MongoCollection<Document> collection = db.getCollection("alunos");

            Bson updates = Updates.combine(
                    Updates.set("nome", obj.getNome()),
                    Updates.set("ra", obj.getRa()),
                    Updates.set("email", obj.getEmail()),
                    Updates.set("telefone", obj.getTelefone()),
                    Updates.set("curso", obj.getCurso()),
                    Updates.set("atualizadoEm", LocalDateTime.now()));

            UpdateResult result = collection.updateOne(eq("ra", obj.getRa()), updates);
            System.out.println("Documentos encontrados: " + result.getMatchedCount() + ", documentos alterados: " + result.getModifiedCount());
        }
        catch (MongoException e) {
            e.printStackTrace();
        }
        }


    @Override
    public void delete(String id) {
        try(MongoClient client = MongoHandler.connect()) {
            MongoDatabase db = client.getDatabase(MongoHandler.getDbName());
            MongoCollection<Document> collection = db.getCollection("alunos");
            DeleteResult result = collection.deleteOne(eq("ra", id));
            System.out.println("Documento(s) deletado(s) na tabela alunos: " + result.getDeletedCount());
        }

    }

    public Aluno findByRa(String ra) {
        try(MongoClient client = MongoHandler.connect()) {
            MongoDatabase db = client.getDatabase(MongoHandler.getDbName());
            Bson excludeIdProjection = Projections.excludeId();
            Document document = db.getCollection("alunos").find(eq("ra", ra)).projection(excludeIdProjection).first();
            return AlunoConversor.documentToAluno(document);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException("Busca por RA não encontrou nenhum aluno.");

        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro interno no servidor. Tente novamente mais tarde.", e);

        }
    }

}
