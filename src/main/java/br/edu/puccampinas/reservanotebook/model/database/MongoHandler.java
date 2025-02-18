package br.edu.puccampinas.reservanotebook.model.database;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;


public class MongoHandler {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String MONGO_URI = dotenv.get("MONGO_URI");
    private final static String DB_NAME = "puc";

    public static MongoClient connect()  {
        try {
            return MongoClients.create(MONGO_URI);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro interno no servidor. Tente novamente mais tarde", e);
        }
    }

    public static String getDbName() {
        return DB_NAME;
    }
}





