package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonAddress implements java.io.Serializable{
    private final StringProperty country;
    private final StringProperty region;
    private final StringProperty city;
    private final StringProperty street;
    private final IntegerProperty houseNumber;
    private final IntegerProperty pavilionNumber;
    private final IntegerProperty flatNumber;

    public PersonAddress(){
        this.country = new SimpleStringProperty();
        this.region = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.houseNumber = new SimpleIntegerProperty();
        this.pavilionNumber = new SimpleIntegerProperty();
        this.flatNumber = new SimpleIntegerProperty();
    }

    public PersonAddress(String country, String region, String city, String street, Integer houseNumber, Integer pavilionNumber, Integer flatNumber){
        this.country = new SimpleStringProperty(country);
        this.region = new SimpleStringProperty(region);
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.houseNumber = new SimpleIntegerProperty(houseNumber);
        this.pavilionNumber = new SimpleIntegerProperty(pavilionNumber);
        this.flatNumber = new SimpleIntegerProperty(flatNumber);
    }


    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public StringProperty countryProperty() {
        return country;
    }



    public String getRegion() {
        return region.get();
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public StringProperty regionProperty() {
        return region;
    }



    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }



    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }



    public Integer getHouseNumber() {
        return houseNumber.get();
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber.set(houseNumber);
    }

    public IntegerProperty houseNumberProperty() {
        return houseNumber;
    }



    public Integer getPavilionNumber() {
        return pavilionNumber.get();
    }

    public void setPavilionNumber(Integer pavilionNumber) {
        this.pavilionNumber.set(pavilionNumber);
    }

    public IntegerProperty pavilionNumberProperty() {
        return pavilionNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber.get();
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber.set(flatNumber);
    }

    public IntegerProperty flatNumberProperty() {
        return flatNumber;
    }
}
