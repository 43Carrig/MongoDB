import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

// Don't forget to run mongo in command line - C:\Program Files\MongoDB\Server\4.0\bin

public class MongoDriver {
    public static void main(String[] args) {

        // Connecting to the mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // Connecting to the database
        MongoDatabase database = mongoClient.getDatabase("testCarDb");

        MongoCRUD c = new MongoCRUD(mongoClient, database);

        c.mapReduce();

    }

}