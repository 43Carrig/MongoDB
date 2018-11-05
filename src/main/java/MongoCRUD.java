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
    }



    public Car getCarById(String carId) { //read/ get
        try {
            MongoClient myClient = new MongoClient("localhost", 27017);
            MongoDatabase database = myClient.getDatabase(DATABASE);
            MongoCollection<Car> collection = database.getCollection(COLLECTION, Car.class);
            collection = collection.withCodecRegistry(pojoCodecRegistry);

            return collection.find(eq("carId", carId)).first();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void updateCarDetails(Car car) { // put / update

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

    public ArrayList<String> mapReduce()
    {
        ArrayList<String> values = new ArrayList<>();

        String map = "function () {" +
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
}