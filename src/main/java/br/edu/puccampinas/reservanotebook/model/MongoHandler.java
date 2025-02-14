package br.edu.puccampinas.reservanotebook.model;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;


public class MongoHandler {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String MONGO_URI = dotenv.get("MONGO_URI");
    private final static String DB_NAME = "puc";

    public static MongoClient connect() throws Exception {
        try {
            return MongoClients.create(MONGO_URI);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}





