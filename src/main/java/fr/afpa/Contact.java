package fr.afpa;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact implements Serializable {

    // attributes are declare as *Property objects
    // in order to be usable in TableView objects columns
    // see columnName.setCellValueFactory(cellData -> cellData.getValue().<object
    // getter>)

    // mandatory attributes
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty persoPhone;
    private StringProperty email;
    private StringProperty address;
    private StringProperty zipCode;

    // enum is public to avoid contructor warning
    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY
    }

    private ObjectProperty<Enum<Gender>> gender;

    // optional attributes
    private ObjectProperty<LocalDate> birthDate;
    private StringProperty proPhone;
    private StringProperty pseudo;
    private StringProperty gitLink;

    // warning suppression for
    // java:S107 too many parameters
    // java:S2293 reminder of SimpleObjectProperty<> type -> Enum<Gender>, LocalDate
    @SuppressWarnings({ "java:S107", "java:S2293" })
    public Contact(String firstName, String lastName, String persoPhone, String email,
            String address, String zipCode, Enum<Gender> gender, LocalDate birthDate,
            String proPhone, String pseudo, String gitLink) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.persoPhone = new SimpleStringProperty(persoPhone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.gender = new SimpleObjectProperty<Enum<Gender>>(gender);
        this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
        this.proPhone = new SimpleStringProperty(proPhone);
        this.pseudo = new SimpleStringProperty(pseudo);
        this.gitLink = new SimpleStringProperty(gitLink);
    }

    // setters to transform Object into ObjetProperty
    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public StringProperty getPersoPhone() {
        return persoPhone;
    }

    public void setPersoPhone(String persoPhone) {
        this.persoPhone = new SimpleStringProperty(persoPhone);
    }

    public StringProperty getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public StringProperty getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = new SimpleStringProperty(zipCode);
    }

    public ObjectProperty<Enum<Gender>> getGender() {
        return gender;
    }

    // warning suppression for
    // java:S2293 reminder of SimpleObjectProperty<> type -> Enum<Gender>
    @SuppressWarnings({ "java:S2293" })
    public void setGender(Enum<Gender> gender) {
        this.gender = new SimpleObjectProperty<Enum<Gender>>(gender);
    }

    public ObjectProperty<LocalDate> getBirthDate() {
        return birthDate;
    }

    // warning suppression for
    // java:S2293 reminder of SimpleObjectProperty<> type -> LocalDate
    @SuppressWarnings({ "java:S2293" })
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
    }

    public StringProperty getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = new SimpleStringProperty(proPhone);
    }

    public StringProperty getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = new SimpleStringProperty(pseudo);
    }

    public StringProperty getGitLink() {
        return gitLink;
    }

    public void setGitLink(String gitLink) {
        this.gitLink = new SimpleStringProperty(gitLink);
    }

    // toString() returns object.getValue() to avoid unnecessary
    // Object=ObjectProperty [value=String]
    // and to correctly return solely the value
    @Override
    public String toString() {
        return "Contact [firstName=" + firstName.getValue() + ", lastName=" + lastName.getValue() + ", persoPhone="
                + persoPhone.getValue() + ", email="
                + email.getValue() + ", address=" + address.getValue() + ", zipCode=" + zipCode.getValue() + ", gender="
                + gender.getValue() + ", birthDate="
                + birthDate.getValue() + ", proPhone=" + proPhone.getValue() + ", pseudo=" + pseudo.getValue()
                + ", gitLink=" + gitLink.getValue() + "]";
    }

}
