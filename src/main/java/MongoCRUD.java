import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoCRUD {

     MongoClient mongoClient = null;
     MongoDatabase _db = null;
     String DATABASE = "testCarDb";
     String COLLECTION = "cars";
     CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

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

    public void addCar(Car car) {
        MongoClient myClient = new MongoClient("localhost", 27017);
        MongoDatabase database = myClient.getDatabase(DATABASE);
        MongoCollection<Car> collection = database.getCollection(COLLECTION, Car.class);
        collection = collection.withCodecRegistry(pojoCodecRegistry);

        collection.insertOne(car);

       // collection.insertOne(ada);
    }



    public Car getCarById(String carId) { //read/ getCarById/ get
        try {
            MongoClient myClient = new MongoClient("localhost", 27017);
            MongoDatabase database = myClient.getDatabase(DATABASE);
            MongoCollection<Car> collection = database.getCollection(COLLECTION, Car.class);
            collection = collection.withCodecRegistry(pojoCodecRegistry);

            return collection.find(eq("carId", carId)).first();

            /*Car result = null;

            FindIterable<Car> iter = mongoClient
                    .getDatabase(DATABASE)
                    .getCollection("cars", Car.class).find(eq("Car ID", carId))
                    .limit(1);// Only get one

            return iter.first();*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void updateCarDetails(Car car) { //updateCarDetails / put / update

        try {
            MongoClient myClient = new MongoClient("localhost", 27017);
            MongoDatabase database = myClient.getDatabase(DATABASE);
            MongoCollection<Car> collection = database.getCollection(COLLECTION, Car.class);
            collection = collection.withCodecRegistry(pojoCodecRegistry);

            collection.updateOne(eq("carId", car.getCarId()),
                    combine(set("carRegistration", car.getCarRegistration()),
                    set("isSold", car.getIsSold()),
                    set("carMake", car.getCarMake()),
                    set("carModel", car.getCarModel()),
                    set("year", car.getYear()),
                    set("price", car.getPrice()),
                    set("fuelType", car.getFuelType()),
                    set("engineSizeCC", car.getEngineSizeCC()),
                    set("transmission", car.getTransmission()),
                    set("color", car.getColor()),
                    set("numberOfDoors", car.getNumberOfDoors())));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCar(String carId) {

        try {
            MongoClient myClient = new MongoClient("localhost", 27017);
            MongoDatabase database = myClient.getDatabase(DATABASE);
            MongoCollection<Car> collection = database.getCollection(COLLECTION, Car.class);
            collection = collection.withCodecRegistry(pojoCodecRegistry);

            collection.deleteOne(eq("carId", carId));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //**********

    public ArrayList<String> mapReduce()
    {
        ArrayList<String> values = new ArrayList<>();

        String map = "function () {" + // SPACES IN DATABASE FIELDS ARE FUCKING RETARDED, EXAMPLE A.
                "emit( this.carMake, 1); " +
                "}";

        String reduce = "function(key, values) { " +
                " return Array.sum(values);" +
                "}";
        MongoClient myClient = new MongoClient("localhost", 27017);
        MongoDatabase database = myClient.getDatabase("testCarDb");

        MongoCollection collection = database.getCollection("cars");

        MapReduceIterable<Car> result = collection.mapReduce(map, reduce);

        MongoCursor cursor = result.iterator();
        while (cursor.hasNext()) {
            Document carResult = (Document) cursor.next();
            String text = carResult.getString("_id") + ": " + carResult.getDouble("value").toString();
            values.add(text);
            System.out.println(carResult.toJson());
        }
        return values;
    }


    //**********

//
//    DB db = null;
//
//
//    MongoCollection properties = database.getCollection("testdb");
//
//    MongoCollection<Document> properties = new MongoClient().getDatabase("Database").getCollection("collection");
//
//    DBCollection properties = db.getCollection("testdb");
//
//    String tnumber = "";
//
//    public void runMapReduce(DBCollection bands){
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
//    }
//
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