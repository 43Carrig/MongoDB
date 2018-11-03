import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.client.FindIterable;

public class MongoCRUD {

     MongoClient mongoClient = null;
     MongoDatabase _db = null;
     String DATABASE = "testdb";

    MongoDatabase database = mongoClient.getDatabase("testdb");

    public MongoCRUD(MongoClient mongoClient, MongoDatabase database) {
        this.mongoClient = mongoClient;
        this._db = database;
    }

    public void create(String studentName, String tnumber, String course ) {
        try {
            mongoClient.getDatabase(DATABASE)
                    .getCollection("students")
                    .insertOne(new Document()
                    .append("Student Name", studentName)
                    .append("tnumber", tnumber)
                    .append("Course", course));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readAll() {
        try {
            FindIterable<Document> iter = mongoClient
                    .getDatabase(DATABASE)
                    .getCollection("students").find();

            iter.forEach(new Block<Document>() {
                @Override
                public void apply(Document doc) {
                    System.out.println(doc);
                }
            });

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCategory(String tnumber, String course) {

        try {
            mongoClient.getDatabase(DATABASE).getCollection("students")
                    .updateOne(new Document("tnumber", tnumber),
                            new Document("$set", new Document("Course", course)));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(String tnumber) {

        try {
            mongoClient.getDatabase(DATABASE).getCollection("students")
                    .deleteOne(new Document("tnumber", tnumber));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //**********


    DB db = null;


    //MongoCollection properties = database.getCollection("testdb");

    //MongoCollection<Document> properties = new MongoClient().getDatabase("Database").getCollection("collection");

    DBCollection properties = db.getCollection("testdb");

    String tnumber = "";

    public void runMapReduce(DBCollection bands){
        MapReduceOutput out = bands.mapReduce(new MapReduceCommand(bands,
                "function(){ " +
                        "for (var album in this.albums) { " +
                        "emit({band: this.name}, 1); " +
                        "} " +
                        "}",
                "function(key, values){ " +
                        "var sum = 0; " +
                        "for (var i in values) { " +
                        "sum += values[i]; " +
                        "} " +
                        "return sum; }",
                null, MapReduceCommand.OutputType.INLINE, null));
        System.out.println("Mapreduce results");
        for (DBObject o : out.results()) {
            System.out.println(o.toString());
        }
    }

//    String map = "function() { "+
//            "var houseCategory; " +
//            "if ( this.county == "+ tnumber +  ") "+
//            "emit(houseCategory, {houseId: this.houseId});}";
//
//    public void runMapReduce(DBCollection bands){
////        MapReduceOutput out = bands.mapReduce(new MapReduceCommand( properties,
////                "function(){ " +
////                        "for (var album in this.albums) { " +
////                        "emit({band: this.name}, 1); " +
////                        "} " +
////                        "}",
////                "function(key, values){ " +
////                        "var sum = 0; " +
////                        "for (var i in values) { " +
////                        "sum += values[i]; " +
////                        "} " +
////                        "return sum; }",
////                null, MapReduceCommand.OutputType.INLINE, null));
//
//        String reduce = "function( key, values) { " +
//                "var num = 0; " +
//                "values.forEach(function(doc) { " +
//                "num += 1; " + " }); " +
//                "return {properties: num};}";
//
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
//
//
//        //MapReduceCommand cmd = new MapReduceCommand(properties, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
//        //MapReduceOutput out = properties.mapReduce(cmd);
//
//        System.out.println("Mapreduce results");
//        for (DBObject o : out.results()) {
//            System.out.println(o.toString());
//        }
//    }


}