import org.bson.types.ObjectId;

import javax.print.DocFlavor;
import java.util.List;

public class Car {

    private ObjectId _id;
    private String carId;
    private String carRegistration;
    private Boolean isSold;
    private String carMake;
    private String carModel;
    private String year;
    private String price;
    private String fuelType;
    private String engineSizeCC;
    private String transmission;
    private String color;
    private String numberOfDoors;

    public Car() {
    }

    public Car(String carId, String carRegistration, Boolean isSold, String carMake, String carModel, String year, String price, String fuelType, String engineSizeCC, String transmission, String color, String numberOfDoors) {
        this.carId = carId;
        this.carRegistration = carRegistration;
        this.isSold = isSold;
        this.carMake = carMake;
        this.carModel = carModel;
        this.year = year;
        this.price = price;
        this.fuelType = fuelType;
        this.engineSizeCC = engineSizeCC;
        this.transmission = transmission;
        this.color = color;
        this.numberOfDoors = numberOfDoors;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean sold) {
        isSold = sold;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngineSizeCC() {
        return engineSizeCC;
    }

    public void setEngineSizeCC(String engineSizeCC) {
        this.engineSizeCC = engineSizeCC;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(String numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
