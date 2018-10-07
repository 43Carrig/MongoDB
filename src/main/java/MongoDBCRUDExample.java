import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoDBCRUDExample {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase database = mongoClient.getDatabase("testdb");

        CRUD c = new CRUD(mongoClient, database);

        c.readAll();

        c.create("MongoDB project - Sean", "102-304-506", Arrays.asList("Sean", "Byrne"), Arrays.asList("software", "software engineering"));

        c.create("MongoDB - Declan", "999-888-777", Arrays.asList("Declan", "Buckley"), Arrays.asList("math", "numerical"));

        c.readAll();

        c.updateCategory("999-888-777", Arrays.asList("mathematics","numerical", "computation"));

        c.readAll();

        c.delete("102-304-506");

        c.readAll();
    }
}