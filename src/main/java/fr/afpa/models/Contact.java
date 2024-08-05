package fr.afpa.models;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

public class Contact implements Serializable, Jsonable {

    // attributes are declare as *Property objects
    // in order to be usable in TableView objects columns
    // see columnName.setCellValueFactory(cellData -> cellData.getValue().<object
    // getter>)

    // mandatory attributes
    // Ici les attributs sont de classe "String" et "Enum" et "LocalDate"
    // elles ne peuvent pas être ajoutées à une TableView
    // il faut d'abord convertir la valeur de ces attributs
    // notamment avec
    // StringProperty observableNom = new SimpleStringProperty(contact.getLastName);
    // il faudra effectuer cette conversion dans le contrôleur ;
    // par exemple en créant une méthode "toObservableContact(Contact contact)"
    // qui convertira tous les attributs 
    // (pas obligé de créer une méthode, c'est juste un exemple;
    // tu peux effectuer la conversion directement dans l'initialisation de ton contrôleur)
    private String firstName;
    private String lastName;
    private String persoPhone;
    private String email;
    private String address;
    private String zipCode;

    // enum is public to avoid contructor warning
    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY
    }

    private Enum<Gender> gender;

    // optional attributes
    private LocalDate birthDate;
    private String proPhone;
    private String pseudo;
    private String gitLink;
    // private int id;
    
    
    // warning suppression for
    // java:S107 too many parameters
    @SuppressWarnings({ "java:S107" })
    public Contact(String firstName, String lastName, String persoPhone, String email, String address, String zipCode,
            Enum<Gender> gender, LocalDate birthDate, String proPhone, String pseudo, String gitLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.persoPhone = persoPhone;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.gender = gender;
        this.birthDate = birthDate;
        this.proPhone = proPhone;
        this.pseudo = pseudo;
        this.gitLink = gitLink;
        // this.id = id;
    }

    public  boolean verifyContact(Contact contact) {

        boolean isContact = false;

        if (contact.getLastName() != null && contact.getFirstName() != null && contact.getPersoPhone() != null
                && contact.getEmail() != null && contact.getAddress() != null && contact.getZipCode() != null
                && contact.getGender() != null) {
            isContact = true;
        }

        return isContact;
    }

    public void reinitalize() {
        this.lastName = null;
        this.firstName = null;
        this.persoPhone = null;
        this.email = null;
        this.address = null;
        this.lastName = null;
        this.lastName = null;
        this.lastName = null;
        this.lastName = null;
        this.lastName = null;
        this.lastName = null;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersoPhone() {
        return persoPhone;
    }

    public void setPersoPhone(String persoPhone) {
        this.persoPhone = persoPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public void setGender(Enum<Gender> gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getGitLink() {
        return gitLink;
    }

    public void setGitLink(String gitLink) {
        this.gitLink = gitLink;
    }

    // public int getId() {
    //     return id;
    // }

    // public void setId(int id) {
    //     this.id = id;
    // }

    @Override
    public String toString() {
        return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", persoPhone=" + persoPhone + ", email="
                + email + ", address=" + address + ", zipCode=" + zipCode + ", gender=" + gender + ", birthDate="
                + birthDate + ", proPhone=" + proPhone + ", pseudo=" + pseudo + ", gitLink=" + gitLink 
                + "]";
    }

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException caught) {
            throw new RuntimeException(caught);
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        String genderString = "";
        switch (this.gender) {
            case Contact.Gender.MALE:
                genderString = "MALE";
                break;

            case Contact.Gender.FEMALE:
                genderString = "FEMALE";
                break;

            case Contact.Gender.NON_BINARY:
                genderString = "NON_BINARY";
                break;

            default:
                break;
        }
        String bDateString = null;
        if (this.birthDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            bDateString = this.birthDate.format(formatter);
        }

        JsonObject json = new JsonObject();
        json.put("firstName", this.firstName);
        json.put("lastName", this.lastName);
        json.put("persoPhone", this.persoPhone);
        json.put("email", this.email);
        json.put("address", this.address);
        json.put("zipCode", this.zipCode);
        json.put("gender", genderString);
        json.put("birthDate", bDateString);
        json.put("proPhone", this.proPhone);
        json.put("pseudo", this.pseudo);
        json.put("gitLink", this.gitLink);
        // json.put("id", this.id);
        json.toJson(writer);
    }
}
