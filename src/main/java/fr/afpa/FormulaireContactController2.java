package fr.afpa;

import fr.afpa.Model.Contact2;
import fr.afpa.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormulaireContactController2 {

    @FXML
    private VBox mainVBox;

    // tableView
    @FXML
    private TableView<Contact2> contactsTable;
    @FXML
    private TableColumn<Contact2, String> nomColumn;
    @FXML
    private TableColumn<Contact2, String> prenomColumn;
    @FXML
    private TableColumn<Contact2, String> telephoneColumn;
    @FXML
    private TableColumn<Contact2, String> emailColumn;
    @FXML
    private TableColumn<Contact2, String> adresseColumn;
    @FXML
    private TableColumn<Contact2, String> codepostaleColumn;
    @FXML
    private TableColumn<Contact2, String> toggleButtonGroupColumn;

    @FXML
    private TableColumn<Contact2, String> datedenaissanceColumn;
    @FXML
    private TableColumn<Contact2, String> telephoneproColumn;
    @FXML
    private TableColumn<Contact2, String> pseudoColumn;
    @FXML
    private TableColumn<Contact2, String> liengitColumn;

    // la partie de la fenêtre "Informations obligatoires"
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telephonePersonnelField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField adresseField; // il pense que c'est un Label
    // il faut retrouver le Label adresseField
    @FXML
    private TextField codePostalField;
    @FXML
    private ToggleButtonGroup genreGroup;

    // Noms des RadioButtons
    private RadioButton hommeRadio;
    @FXML
    private RadioButton femmeRadio;
    @FXML
    private RadioButton nonBinaireRadio;

    // la partie de la fenêtre "Informations facultatives"
    @FXML
    private TextField dateNaissanceField;
    @FXML
    private TextField telephoneProfessionnelField;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField lienDepotGitField;

    // Noms des boutons à gauche
    @FXML
    private Button selectAllButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button vCardButton;
    @FXML
    private Button jsonButton;

    // Noms des boutons à droite
    @FXML
    private Button nouveauButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button sauvegarderButton;
    @FXML
    private Button quitterButton;

    private ObservableList<Contact2> contactData = FXCollections.observableArrayList();

    private Contact2 contact2;

    public FormulaireContactController2() {
        // Ajouter des données d'exemple

    }

    @FXML
    private void initialize() {

        // Initialiser les colonnes du TableView
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        adresseColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        codepostaleColumn.setCellValueFactory(cellData -> cellData.getValue().codePostalProperty());
        toggleButtonGroupColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        datedenaissanceColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        telephoneproColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        pseudoColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        liengitColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());

        // Ajouter des contacts fictifs pour le test
        contactData.add(new Contact2("Dupont", "Jean", "0123456789", "jean.dupont@example.com", "1 rue de Paris",
                "75000", "Homme", null, "0987654321", "jdupont", "https://github.com/jdupont"));
        contactData.add(new Contact2("Zannese", "Aurélie", "0987654321", "jean.dupont@example.com", "1 rue de Paris",
                "75000", "Homme", null, "0987654321", "jdupont", "https://github.com/jdupont"));
        contactData.add(new Contact2("Ford", "Mélanie", "0854796314", "jean.dupont@example.com", "1 rue de Paris",
                "75000", "Homme", null, "0987654321", "jdupont", "https://github.com/jdupont"));

        // Ajouter les données au TableView
        contactsTable.setItems(contactData);
    }

    // Gestion des événements (à implémenter)
    @FXML
    private void handleNouveau() {
        // Logique pour créer un nouveau contact
        Contact2 newContact = new Contact2("", "", "", "", "", "", "", null, "", "", "");
        contactData.add(newContact);
        contactsTable.getSelectionModel().select(newContact);
        showContactDetails(newContact);
        // clearFields();
    }

    @FXML
    private void handleModifier() {
        // Logique pour modifier un contact existant
        Contact2 selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setNom(nomField.getText());
            selectedContact.setPrenom(prenomField.getText());
            selectedContact.setTelephonePersonnel(telephonePersonnelField.getText());
            selectedContact.setEmail(emailField.getText());
            selectedContact.setAdresse(adresseField.getText());
            selectedContact.setCodePostal(codePostalField.getText());
            selectedContact.setGenre(getSelectedGenre());
            // Date de naissance et autres champs facultatifs
            selectedContact.setTelephoneProfessionnel(telephoneProfessionnelField.getText());
            selectedContact.setPseudo(pseudoField.getText());
            selectedContact.setLienDepotGit(lienDepotGitField.getText());
        } else {
            showAlert("No selection", "No Contact Selected", "Please select a contact in the table.");
        }
    }

    @FXML
    private void handleEnregistrer() {
        // Logique pour enregistrer un contact
        // Todo Implémenter la logique de sauvegarde d'une nouvelle personne

        // 1 instancier une nouvelle personne en utilisant les informations contenues
        // dans les "TextField"
        // Pour obtenir la valeur contenue dans un "TextField" il est possible
        // d'utiliser la méthode ".getText()". Par exemple "prenomTextField.getText()"

        // Pour instancier une nouvelle personne tu peux utiliser l'opérateur "new"

        // 2 ajouter la nouvelle personne à la liste des contacts qui s'appelle
        // "contactData".
        // POur ajouter à la liste tu peux utiliser la méthode ".add()" (je ne me
        // souveins jamais car parfois c'est "push", parfois c'est "append", parfois
        // c'est un autre mot)
        // conseil : quand t'as un doute tu peux écrire le nom de la liste et essayer de
        // trouver la bonne méthode en utilisant "CTRL + espace"
        // "CTRL + espace" => appelle le menu contextuel d'aide de VSCode

        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String telephonepersonnel = telephonePersonnelField.getText();
        String email = emailField.getText();
        String adresse = adresseField.getText();
        String codePostal = codePostalField.getText();
        
        Object ToggleButtonGroup = oggleButtonGroup.getText();
        Object dateNaissance = dateNaissanceField.getText();
        String telephoneProfessionnel = telephoneProfessionnelField.getText();
        String pseudo = pseudoField.getText();
        String lienDepotGit = lienDepotGitField.getText();

        
        Contact2 contact = new Contact2(nom, prenom, telephonepersonnel, email, adresse, codePostal, genreGroup,
                dateNaissance, telephoneProfessionnel, pseudo, lienDepotGit);
        contactData.add(contact);

        // Vider le formulaire
        nomField.setText("");
        prenomField.setText("");
        telephonePersonnelField.setText("");
        emailField.setText("");
        codePostalField.setText("");
        dateNaissanceField.setText("");
        telephoneProfessionnelField.setText("");
        pseudoField.setText("");
        lienDepotGitField.setText("");

        /*1. Gestion des Exceptions lors de la Sauvegarde
        Ajoutez une gestion appropriée des exceptions dans les méthodes de sauvegarde pour gérer les erreurs d'entrée/sortie et autres problèmes :*/
        /*FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                // Sérialisation des contacts
                ContactJsonSerializer serializer = new ContactJsonSerializer();
                serializer.saveList(file.getAbsolutePath(), new ArrayList<>(contactList));
            } catch (IOException e) {
                showAlert("Error", "Could not save data", "Could not save data to file:\n" + file.getAbsolutePath() + "\n" + e.getMessage());
            }
        }*/

        // Création du contact avec les données des champs
        /*
         * 1. Validation des Champs dans le Contrôleur
         * Vous pouvez ajouter des méthodes de validation spécifiques pour chaque champ
         * de saisie dans le contrôleur. Voici comment intégrer ces validations dans la
         * méthode handleSaveContact() :
         */
        TableColumnBase<Contact2, String> telephonePersoField;
        Contact2 contact2 = new Contact2(
                nomField.getText(),
                prenomField.getText(),
                telephonePersoField.getText(),
                emailField.getText(),
                adresseField.getText(),
                codePostalField.getText(),
                genreGroup.getSelectedToggle() != null ? ((RadioButton) genreGroup.getSelectedToggle()).getText() : "",
                dateNaissanceField.getText(),
                telephoneProfessionnelField.getText(),
                pseudoField.getText(),
                lienDepotGitField.getText());

        // Validation des champs
        String validationMessage = validateContact(contact2);

        if (validationMessage == null) {
            // Ajouter le contact à la liste si valide
            contactData.add(contact2);
            clearFields();
        } else {
            // Afficher un message d'erreur si la validation échoue
            showAlert("Validation Error", "Invalid Contact", validationMessage);
        }

        /*
        * 2. Méthode de Validation des Contacts
        * Créez une méthode validateContact(Contact contact) pour vérifier chaque champ :
        */

    private String validateContact(Contact2 contact2) {
        StringBuilder errors = new StringBuilder();

        
        // Validation du nom
        if (contact2.getNom().trim().isEmpty()) {
            errors.append("Le nom est requis.\n");
        }

        // Validation du prénom
        if (contact2.getPrenom().trim().isEmpty()) {
            errors.append("Le prénom est requis.\n");
        }

        // Validation du téléphone personnel
        if (!contact2.isPhoneValid()) {
            errors.append("Numéro de téléphone personnel invalide. Il devrait y avoir 10 chiffres.\n");
        }

        // Validation de l'email
        if (!contact2.isEmailValid()) {
            errors.append("Adresse e-mail invalide.\n");
        }

        // Validation du code postal (exemple simple)
        if (contact2.getCodePostal().trim().isEmpty()) {
            errors.append("Postal code is required.\n");
        }

        // Validation de la date de naissance (exemple simple)
        if (!contact2.getDateNaissance().matches("\\d{2}/\\d{2}/\\d{4}")) {
            errors.append("Date of birth should be in the format dd/MM/yyyy.\n");
        }

        // Autres validations selon les besoins
        // ...

        return errors.length() > 0 ? errors.toString() : null;

    }

    @FXML
    private void handleSupprimer() {
        // Logique pour supprimer un contact
        // Todo Implémenter la logique de suppression d'une personne sélectionnée

        // 1 retrouver l" contact à supprimer du tableau (sauvegarder dans une variable)
        // contactsTable.getSelectionModel().getSelectedItem()

        // 2 supprimer le contact de la liste des contacts (contactData)

        Contact2 contact2 = contactsTable.getSelectionModel().getSelectedItem();
        if (contact2 != null) {
            contactData.remove(contact2);
        }

        /*
         * int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
         * if (selectedIndex >= 0) {
         * contactsTable.getItems().remove(selectedIndex);
         * }
         */
    }

    @FXML
    private void handleQuitter() {
        // Logique pour quitter l'application
        System.exit(0);
    }

    @FXML
    private void showContactDetails(Contact2 contact) {
        if (contact2 != null) {
            nomField.setText(contact2.getNom());
            prenomField.setText(contact2.getPrenom());
            telephonePersonnelField.setText(contact2.getTelephonePersonnel());
            emailField.setText(contact2.getEmail());
            adresseField.setText(contact.getAdresse());
            codePostalField.setText(contact.getCodePostal());
            selectGenre(contact2.getGenre());
            dateNaissanceField.setText(contact2.getDateNaissance() != null ?
            contact2.getDateNaissance().toString() : "");
            telephoneProfessionnelField.setText(contact.getTelephoneProfessionnel());
            pseudoField.setText(contact.getPseudo());
            lienDepotGitField.setText(contact.getLienDepotGit());
            
        } else {
            nomField.setText("");
            prenomField.setText("");
            telephonePersonnelField.setText("");
            emailField.setText("");
            adresseField.setText("");
            codePostalField.setText("");
            hommeRadio.setSelected(false);
            femmeRadio.setSelected(false);
            nonBinaireRadio.setSelected(false);
            dateNaissanceField.setText("");
            telephoneProfessionnelField.setText("");
            pseudoField.setText("");
            lienDepotGitField.setText("");
        }
    }

    @FXML
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

    @FXML
    private void setselectGenre(String genre) {
        if ("Homme".equals(genre)) {
            hommeRadio.setSelected(true);
        } else if ("Femme".equals(genre)) {
            femmeRadio.setSelected(true);
        } else if ("Non binaire".equals(genre)) {
            nonBinaireRadio.setSelected(true);
        }
    }

    @FXML
    private void handleSelectAll() {
        contactsTable.getSelectionModel().selectAll();
    }

    @FXML
    private void handleClear() {
        contactsTable.getItems().clear();
    }

    @FXML
    private void handleVCard() {
        // Logique pour exporter en vCard
        showAlert("Pas mis en œuvre", "Fonctionnalité non implémentée", "L'exportation vCard n'est pas encore implémentée.");
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        telephonePersonnelField.clear();
        emailField.clear();
        adresseField.clear();
        codePostalField.clear();
        dateNaissanceField.clear();
        telephoneProfessionnelField.clear();
        pseudoField.clear();
        lienDepotGitField.clear();
    }


    private void populateFields(Contact2 contact2) {
        nomField.setText(contact2.getNom());
        prenomField.setText(contact2.getPrenom());
        telephonePersonnelField.setText(contact2.getTelephonePersonnel());
        emailField.setText(contact2.getEmail());
        adresseField.setText(contact2.getAdresse());
        codePostalField.setText(contact2.getCodePostal());
        dateNaissanceField.setText(contact2.getDateNaissance());
        telephoneProfessionnelField.setText(contact2.getTelephoneProfessionnel());
        pseudoField.setText(contact2.getPseudo());
        lienDepotGitField.setText(contact2.getLienDepotGit());
    }

    /*
     * Gestion des Erreurs dans l'Interface Utilisateur
     * 1. Affichage des Erreurs
     * Utilisez les alertes pour informer l'utilisateur des erreurs de validation ou
     * d'autres problèmes. Voici comment la méthode showAlert est utilisée pour
     * afficher des messages :
     */

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     *  2. Gestion des Exceptions lors de la Chargement des Données
    De même, gérez les exceptions lors du chargement des données depuis un fichier :
     */
   
    /*@FXML
    private void handleLoadContact() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                // Désérialisation des contacts
                ContactJsonDeserializer deserializer = new ContactJsonDeserializer();
                List<Contact> contacts = deserializer.loadList(file.getAbsolutePath());
                contactList.setAll(contacts);
            } catch (IOException e) {
                showAlert("Error", "Could not load data", "Could not load data from file:\n" + file.getAbsolutePath() + "\n" + e.getMessage());
            }
        }
    }*/
}
