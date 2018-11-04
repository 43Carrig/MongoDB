import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.client.FindIterable;

public class MongoCRUD {

     MongoClient mongoClient = null;
     MongoDatabase _db = null;
     String DATABASE = "testCarDb";

    //MongoDatabase database = mongoClient.getDatabase("testdb");

    public MongoCRUD() {

    }

    public MongoCRUD(MongoClient mongoClient, MongoDatabase database) {
        this.mongoClient = mongoClient;
        this._db = database;
    }

    public void addCar(String carId, String carRegistration, String sold, String carMake, String carModel, String year,
                       String price, String fuelType, String engineSizeCC, String transmission, String color, String numberOfDoors) { //addCar/ post/ create
        try {
            mongoClient.getDatabase(DATABASE)
                    .getCollection("cars")
                    .insertOne(new Document()
                    .append("Car ID", carId)
                    .append("Car Registration", carRegistration)
                    .append("Sold (True/False)", sold)
                    .append("Car Make", carMake)
                    .append("Car Model", carModel)
                    .append("Year", year)
                    .append("Price", price)
                    .append("Fuel Type", fuelType)
                    .append("EngineSizeCC", engineSizeCC)
                    .append("Transmission",transmission)
                    .append("Color", color)
                    .append("Number Of Doors", numberOfDoors));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



    public void getCarById(String carId) { //read/ getCarById/ get
        try {
            FindIterable<Document> iter = mongoClient
                    .getDatabase(DATABASE)
                    .getCollection("cars").find(new Document("Car ID", carId));

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

    public void updateCarDetails(String tnumber, String course) { //updateCarDetails / put / update

        try {
            mongoClient.getDatabase(DATABASE).getCollection("students")
                    .updateOne(new Document("tnumber", tnumber),
                            new Document("$set", new Document("Course", course)));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCar(String tnumber) {

        try {
            mongoClient.getDatabase(DATABASE).getCollection("students")
                    .deleteOne(new Document("tnumber", tnumber));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //**********

    public void mapReduce()
    {
        String map = "function () {" +
                "emit('carId',{count:1}); " +
                "}";

        String reduce = "function(key, values) { " +
                " return sum(values);" +
                "}";
        MongoClient myclient = new MongoClient("localhost", 27017);
        MongoDatabase database = myclient.getDatabase("testCarDb");

        ////////////
        DB db = null;
        //////////////

        MongoCollection collection = (MongoCollection) database.getCollection("cars").mapReduce(map, reduce);
        //.mapReduce(map, reduce);


        System.out.println(collection.toString());

    }


    //**********


    DB db = null;


    MongoCollection properties = database.getCollection("testdb");

    MongoCollection<Document> properties = new MongoClient().getDatabase("Database").getCollection("collection");

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

    String map = "function() { "+
            "var houseCategory; " +
            "if ( this.county == "+ tnumber +  ") "+
            "emit(houseCategory, {houseId: this.houseId});}";

    public void runMapReduce(DBCollection bands){
//        MapReduceOutput out = bands.mapReduce(new MapReduceCommand( properties,
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

        String reduce = "function( key, values) { " +
                "var num = 0; " +
                "values.forEach(function(doc) { " +
                "num += 1; " + " }); " +
                "return {properties: num};}";

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


        //MapReduceCommand cmd = new MapReduceCommand(properties, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
        //MapReduceOutput out = properties.mapReduce(cmd);

        System.out.println("Mapreduce results");
        for (DBObject o : out.results()) {
            System.out.println(o.toString());
        }
    }


}