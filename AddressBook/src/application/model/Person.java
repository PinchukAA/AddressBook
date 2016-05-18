package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person implements java.io.Serializable{

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty surName;
    private final StringProperty FIO;
    private final PersonAddress address;

    public Person() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.surName = new SimpleStringProperty();
        this.address = new PersonAddress();

        this.FIO = new SimpleStringProperty();
    }

    public Person(String firstName, String lastName, String surName, PersonAddress address) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.surName = new SimpleStringProperty(surName);
        this.address = address;

        this.FIO = new SimpleStringProperty(surName + ' ' + firstName + ' ' + lastName);
    }


    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }



    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String firstName) {
        this.lastName.set(firstName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }



    public String getSurName() {
        return surName.get();
    }

    public void setSurName(String firstName) {
        this.surName.set(firstName);
    }

    public StringProperty surNameProperty() {
        return firstName;
    }

    public PersonAddress getAddress(){
        return address;
    }


    public String getFIO() {
        return FIO.get();
    }

    public void setFIO(String lastName, String firstName, String surName) {
        this.FIO.set(surName + ' ' + firstName + ' ' + lastName);
    }

    public void setFIO() {
        this.FIO.set(surName.getValue() + ' ' + firstName.getValue() + ' ' + lastName.getValue());
    }


    public StringProperty FIOProperty() {
        return FIO;
    }
}