package br.edu.puccampinas.reservanotebook.model.database;
import com.mongodb.client.*;
import io.github.cdimascio.dotenv.Dotenv;



public class MongoHandler {

    private static MongoHandler instance;
    private final MongoClient client;
    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String MONGO_URI = dotenv.get("MONGO_URI");
    private String dbName = dotenv.get("DB_NAME");


    private MongoHandler() {
        client = MongoClients.create(MONGO_URI);
    }

    public static synchronized MongoHandler getInstance() {
        if(instance==null) {
            synchronized (MongoHandler.class) {
                if (instance==null) {
                    instance = new MongoHandler();
                }
            }
        }
        return instance;
    }

    public MongoClient getClient() {
        return client;
    }

    public void toTestDb() {
        this.dbName = dotenv.get("DB_NAME_TEST");
    }

    public void close() {
        if(client!=null) client.close();
    }


}





