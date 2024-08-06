package fr.afpa.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewableContact {

    // Déclaration des attributs :
    // la classe StringProperty permet d'afficher correctement
    // la valeur d'un attribut dans une "ObservableList<>"
    // pour JavaFX
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty telephonePersonnel;
    private StringProperty email;
    private StringProperty adresse;
    private StringProperty codePostal;
    private StringProperty genre;
    private StringProperty dateDeNaissance;
    private StringProperty telephoneProfessionnel;
    private StringProperty pseudo;
    private StringProperty lienDepotGit;

    // Constructeurs :
    // public ViewableContact(String nom, String prenom, String telephonePersonnel,
    // String email, String adresse, String codePostal, String genre) {
    public ViewableContact(Contact contact) {

        this.nom = new SimpleStringProperty(contact.getLastName());
        this.prenom = new SimpleStringProperty(contact.getFirstName());
        this.telephonePersonnel = new SimpleStringProperty(contact.getPersoPhone());
        this.email = new SimpleStringProperty(contact.getEmail());
        this.adresse = new SimpleStringProperty(contact.getAddress());
        this.codePostal = new SimpleStringProperty(contact.getZipCode());

        // conversion de la valeur du genre en String
        String genreString = "";
        switch (contact.getGender()) {
            case Contact.Gender.MALE:
                genreString = "Homme";
                break;
            case Contact.Gender.FEMALE:
                genreString = "Femme";
                break;
            case Contact.Gender.NON_BINARY:
                genreString = "Non-Binaire";
                break;

            default:
                break;
        }

        // conversion de la LocalDate en String
        String dateDeNaissanceString = null;
        if (contact.getBirthDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            dateDeNaissanceString = contact.getBirthDate().format(formatter);
        }

        this.genre = new SimpleStringProperty(genreString);
        this.dateDeNaissance = new SimpleStringProperty(dateDeNaissanceString);
        this.telephoneProfessionnel = new SimpleStringProperty(contact.getPersoPhone());
        this.pseudo = new SimpleStringProperty(contact.getPseudo());
        this.lienDepotGit = new SimpleStringProperty(contact.getGitLink());
    }

    // Getters et setters pour chaque propriété
    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public String getTelephonePersonnel() {
        return telephonePersonnel.get();
    }

    public void setTelephonePersonnel(String telephonePersonnel) {
        this.telephonePersonnel.set(telephonePersonnel);
    }

    public StringProperty telephonePersonnelProperty() {
        return telephonePersonnel;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal.get();
    }

    public void setCodePostal(String codePostal) {
        this.codePostal.set(codePostal);
    }

    public StringProperty codePostalProperty() {
        return codePostal;
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getDateNaissance() {
        return dateDeNaissance.get();
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateDeNaissance.set(dateNaissance);
    }

    public StringProperty dateNaissanceProperty() {
        return dateDeNaissance;
    }

    public String getTelephoneProfessionnel() {
        return telephoneProfessionnel.get();
    }

    public void setTelephoneProfessionnel(String telephoneProfessionnel) {
        this.telephoneProfessionnel.set(telephoneProfessionnel);
    }

    public StringProperty telephoneProfessionnelProperty() {
        return telephoneProfessionnel;
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    public StringProperty pseudoProperty() {
        return pseudo;
    }

    public String getLienDepotGit() {
        return lienDepotGit.get();
    }

    public void setLienDepotGit(String lienDepotGit) {
        this.lienDepotGit.set(lienDepotGit);
    }

    public StringProperty lienDepotGitProperty() {
        return lienDepotGit;
    }



}