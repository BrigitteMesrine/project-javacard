package fr.afpa.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    // Déclaration des attributs :
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty city;

    // Constructeurs :
    public Person(String firstName, String lastName, String city) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.city = new SimpleStringProperty(city);
    }

    // Getters :

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getCity() {
        return city;
    }

    // Setters :
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    @Override
    public String toString() {
        return "Personne [Prénom=" + firstName.getValue() + '\'' + ", Nom=" + lastName.getValue() + '\'' + ", Ville=" + city.getValue() + "]";
    }
}