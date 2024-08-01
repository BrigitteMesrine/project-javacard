package fr.afpa;

import fr.afpa.Model.Contact2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FormulaireContactController2 {

    @FXML
    private TableView<Contact2> contactsTable;
    @FXML
    private TableColumn<Contact2, String> nomColumn;
    @FXML
    private TableColumn<Contact2, String> prenomColumn;
    @FXML
    private TableColumn<Contact2, String> telephoneColumn;
    
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telephonePersonnelField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField codePostalField;
    @FXML
    private RadioButton hommeRadio;
    @FXML
    private RadioButton femmeRadio;
    @FXML
    private RadioButton nonBinaireRadio;
    @FXML
    private TextField dateNaissanceField;
    @FXML
    private TextField telephoneProfessionnelField;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField lienDepotGitField;

    private final ObservableList<Contact2> contactData = FXCollections.observableArrayList();

    public FormulaireContactController2() {
        // Ajouter des données d'exemple
        contactData.add(new Contact2("Dupont", "Jean", "0123456789"/*, "jean.dupont@example.com", "1 rue de Paris", "75000", "Homme", null, "0987654321", "jdupont", "https://github.com/jdupont" */));
    }

    @FXML
    private void initialize() {
        // Initialiser les colonnes du TableView
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        
        // Ajouter les données au TableView
        contactsTable.setItems(contactData);
    }
    
    @FXML
    private void handleNouveau() {
        // Logique pour créer un nouveau contact
        Contact2 newContact = new Contact2("", "", ""/*, "", "", "", "", null, "", "", "" */);
        contactData.add(newContact);
        contactsTable.getSelectionModel().select(newContact);
        showContactDetails(newContact);
    }
    
    @FXML
    private void handleModifier() {
        // Logique pour modifier un contact existant
        Contact2 selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setNom(nomField.getText());
            selectedContact.setPrenom(prenomField.getText());
            selectedContact.setTelephonePersonnel(telephonePersonnelField.getText());
            /*selectedContact.setEmail(emailField.getText());
            selectedContact.setAdresse(adresseField.getText());
            selectedContact.setCodePostal(codePostalField.getText());
            selectedContact.setGenre(getSelectedGenre());
            // Date de naissance et autres champs facultatifs
            selectedContact.setTelephoneProfessionnel(telephoneProfessionnelField.getText());
            selectedContact.setPseudo(pseudoField.getText());
            selectedContact.setLienDepotGit(lienDepotGitField.getText()); */
        }
    }
    
    @FXML
    private void handleEnregistrer() {
        // Logique pour enregistrer un contact
        handleModifier();
    }
    
    @FXML
    private void handleSupprimer() {
        // Logique pour supprimer un contact
        int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contactsTable.getItems().remove(selectedIndex);
        }
    }
    
    @FXML
    private void handleQuitter() {
        // Logique pour quitter l'application
        System.exit(0);
    }
    
    private void showContactDetails(Contact2 contact) {
        if (contact != null) {
            nomField.setText(contact.getNom());
            prenomField.setText(contact.getPrenom());
            telephonePersonnelField.setText(contact.getTelephonePersonnel());
            /*emailField.setText(contact.getEmail());
            adresseField.setText(contact.getAdresse());
            codePostalField.setText(contact.getCodePostal());
            selectGenre(contact.getGenre());
            dateNaissanceField.setText(contact.getDateNaissance() != null ? contact.getDateNaissance().toString() : "");
            telephoneProfessionnelField.setText(contact.getTelephoneProfessionnel());
            pseudoField.setText(contact.getPseudo());
            lienDepotGitField.setText(contact.getLienDepotGit());*/
        } else {
            nomField.setText("");
            prenomField.setText("");
            telephonePersonnelField.setText("");
            /*emailField.setText("");
            adresseField.setText("");
            codePostalField.setText("");
            hommeRadio.setSelected(false);
            femmeRadio.setSelected(false);
            nonBinaireRadio.setSelected(false);
            dateNaissanceField.setText("");
            telephoneProfessionnelField.setText("");
            pseudoField.setText("");
            lienDepotGitField.setText("");*/
        }
    }

    private String getSelectedGenre() {
        if (hommeRadio.isSelected()) {
            return "Homme";
        } else if (femmeRadio.isSelected()) {
            return "Femme";
        } else if (nonBinaireRadio.isSelected()) {
            return "Non binaire";
        } else {
            return "";
        }
    }

    private void setselectGenre(String genre) {
        if ("Homme".equals(genre)) {
            hommeRadio.setSelected(true);
        } else if ("Femme".equals(genre)) {
            femmeRadio.setSelected(true);
        } else if ("Non binaire".equals(genre)) {
            nonBinaireRadio.setSelected(true);
        }
    }
}
