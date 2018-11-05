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

        System.out.println("First read all");
//        c.getCarById();

//        //because of static - can't use
//        c.addCar("1", "161-D-2261", "True", "Ford", "Fiesta", "2016", "20000", "Petrol", "1600", "Automatic", "Blue", "5");
//        c.addCar("2", "151-D-2261", "False", "Opel", "Astra", "2015", "20000", "Petrol", "1800", "Automatic", "Black", "4");
//

        System.out.println("Second read all");
        //c.getCarById("2");

        c.mapReduce();

//        c.addCar("Declan Buckley", "t00143448", "Business Studies");
//
//        c.addCar("Barry Keane", "t00143449", "Business Studies");

//        Car car = new Car();
//
//        car.set_id("1");
//        car.setSold(Boolean.valueOf("True"));
//        car.setRegistration("12-D-265162");
//        car.setCarMake("Audi");
//        car.setCarModel("A4");
//        car.setYear("2018");
//        car.setPrice("50000");
//        car.setFuelType("Petrol");
//        car.setEngineSizeCC("3000");
//        car.setTransmission("Automatic");
//        car.setColor("Red");
//        car.setNumberOfDoors("4");
//
//        MongoCRUD.addCar(car);

//******************************************************************


//        System.out.println("Second read all");
//        c.getCarById();
//
//        c.updateCarDetails("t00143448", "Software Development");
//
//        System.out.println("Third read all");
//        c.getCarById();
//
//        c.deleteCar("t00143448");
//
//        System.out.println("Fourth read all");
//        c.getCarById();




//******************************************************************************

        // Populate collection to perform Mapreduce
        //MongoCollection<Document> bands = database.getCollection("testdb");

        //MongoCollection bands = database.getCollection("testdb");

//******************************************************************************

//        DB db = null;
//
//        DBCollection bands = db.getCollection("testdb");
//
//        // Populate collection to perform Mapreduce
//        //DBCollection bands = db.getCollection("bands");
//        BasicDBObject band = new BasicDBObject();
//        band.put("name", "U2");
//        String albums[] = {"The Joshua Tree", "War", "Achtung Baby"};
//        band.put("albums", albums);
//        bands.insert(band);
//        band = new BasicDBObject();
//        band.put("name", "The Beatles");
//        String albums1[] = {"Abbey Road", "Let It Be"};
//        band.put("albums", albums1);
//        bands.insert(band);
//        // Mapreduce
//        c.runMapReduce(bands);

//******************************************************************************


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