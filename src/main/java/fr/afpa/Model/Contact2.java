package fr.afpa.Model;

import fr.afpa.Model.Contact2;

//import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.ObjectProperty;

//import java.time.LocalDate;

public class Contact2 {
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty telephonePersonnel;
    /*private final StringProperty email;
    private final StringProperty adresse;
    private final StringProperty codePostal;
    private final StringProperty genre;
    private final ObjectProperty<LocalDate> dateNaissance;
    private final StringProperty telephoneProfessionnel;
    private final StringProperty pseudo;
    private final StringProperty lienDepotGit;*/

    public Contact2(String nom, String prenom, String telephonePersonnel) {
    //public Contact2(String nom, String prenom, String telephonePersonnel/*, String email, String adresse, String codePostal, String genre, LocalDate dateNaissance, String telephoneProfessionnel, String pseudo, String lienDepotGit */) {

        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.telephonePersonnel = new SimpleStringProperty(telephonePersonnel);
        /*this.email = new SimpleStringProperty(email);
        this.adresse = new SimpleStringProperty(adresse);
        this.codePostal = new SimpleStringProperty(codePostal);
        this.genre = new SimpleStringProperty(genre);
        this.dateNaissance = new SimpleObjectProperty<>(dateNaissance);
        this.telephoneProfessionnel = new SimpleStringProperty(telephoneProfessionnel);
        this.pseudo = new SimpleStringProperty(pseudo);
        this.lienDepotGit = new SimpleStringProperty(lienDepotGit);
        this.adresse = new StringProperty();*/
    }

    // Getters et setters pour chaque propriété
    public String getNom() { return nom.get(); }
    public void setNom(String nom) { this.nom.set(nom); }
    public StringProperty nomProperty() { return nom; }

    public String getPrenom() { return prenom.get(); }
    public void setPrenom(String prenom) { this.prenom.set(prenom); }
    public StringProperty prenomProperty() { return prenom; }

    public String getTelephonePersonnel() { return telephonePersonnel.get(); }
    public void setTelephonePersonnel(String telephonePersonnel) { this.telephonePersonnel.set(telephonePersonnel); }
    public StringProperty telephonePersonnelProperty() { return telephonePersonnel; }

    /*public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    public String getAdresse() { return adresse.get(); }
    public void setAdresse(String adresse) { this.adresse.set(adresse); }
    public StringProperty adresseProperty() { return adresse; }

    public String getCodePostal() { return codePostal.get(); }
    public void setCodePostal(String codePostal) { this.codePostal.set(codePostal); }
    public StringProperty codePostalProperty() { return codePostal; }

    public String getGenre() { return genre.get(); }
    public void setGenre(String genre) { this.genre.set(genre); }
    public StringProperty genreProperty() { return genre; }

    public LocalDate getDateNaissance() { return dateNaissance.get(); }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance.set(dateNaissance); }
    public ObjectProperty<LocalDate> dateNaissanceProperty() { return dateNaissance; }

    public String getTelephoneProfessionnel() { return telephoneProfessionnel.get(); }
    public void setTelephoneProfessionnel(String telephoneProfessionnel) { this.telephoneProfessionnel.set(telephoneProfessionnel); }
    public StringProperty telephoneProfessionnelProperty() { return telephoneProfessionnel; }

    public String getPseudo() { return pseudo.get(); }
    public void setPseudo(String pseudo) { this.pseudo.set(pseudo); }
    public StringProperty pseudoProperty() { return pseudo; }

    public String getLienDepotGit() { return lienDepotGit.get(); }
    public void setLienDepotGit(String lienDepotGit) { this.lienDepotGit.set(lienDepotGit); }
    public StringProperty lienDepotGitProperty() { return lienDepotGit; }*/
}