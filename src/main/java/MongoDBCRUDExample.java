import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

// Don't forget to run mongo in command line - C:\Program Files\MongoDB\Server\4.0\bin

public class MongoDBCRUDExample {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase database = mongoClient.getDatabase("testdb");

        Mongo m = null;

        CRUD c = new CRUD(mongoClient, database);

        //

        System.out.println("First read all");
        c.readAll();

        c.create("Sean Byrne", "t00143447", "Software Development");

        c.create("Declan Buckley", "t00143448", "Business Studies");

        c.create("Barry Keane", "t00143449", "Business Studies");

        System.out.println("Second read all");
        c.readAll();

        c.updateCategory("t00143448", "Software Development");

        System.out.println("Third read all");
        c.readAll();

        c.delete("t00143448");

        System.out.println("Fourth read all");
        c.readAll();




//******************************************************************************

        // Populate collection to perform Mapreduce
        //MongoCollection<Document> bands = database.getCollection("testdb");

        //MongoCollection bands = database.getCollection("testdb");

//******************************************************************************

        DB db = null;

        DBCollection bands = db.getCollection("testdb");

        // Populate collection to perform Mapreduce
        //DBCollection bands = db.getCollection("bands");
        BasicDBObject band = new BasicDBObject();
        band.put("name", "U2");
        String albums[] = {"The Joshua Tree", "War", "Achtung Baby"};
        band.put("albums", albums);
        bands.insert(band);
        band = new BasicDBObject();
        band.put("name", "The Beatles");
        String albums1[] = {"Abbey Road", "Let It Be"};
        band.put("albums", albums1);
        bands.insert(band);
        // Mapreduce
        c.runMapReduce(bands);

//        MapReduceOutput out = bands.mapReduce(new MapReduceCommand(bands,
//                "function(){ " +
//                        "for (var album in this.albums) { " +
//                        "emit({band: this.name}, 1); " +
//                        "} " +
//                        "}",
//                "function(key, values){ " +
//                        "var sum = 0; " +
//                        "for (var i in values) { " +
//                        "sum += values[i]; " +
//                        "} " +
//                        "return sum; }",
//                null, MapReduceCommand.OutputType.INLINE, null));
//        System.out.println("Mapreduce results");
//        for (DBObject o : out.results()) {
//            System.out.println(o.toString());
//        }




        ////**************************



    }



}