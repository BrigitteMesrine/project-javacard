package fr.afpa;

import fr.afpa.models.Contact;
import fr.afpa.models.ViewableContact;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FormulaireContactController2 {

    // @FXML
    // private VBox mainVBox;

    // tableView
    @FXML
    private TableView<ViewableContact> contactsTable;
    @FXML
    private TableColumn<ViewableContact, String> nomColumn;
    @FXML
    private TableColumn<ViewableContact, String> prenomColumn;
    @FXML
    private TableColumn<ViewableContact, String> telephoneColumn;
    @FXML
    private TableColumn<ViewableContact, String> emailColumn;
    @FXML
    private TableColumn<ViewableContact, String> adresseColumn;
    @FXML
    private TableColumn<ViewableContact, String> codepostaleColumn;
    @FXML
    private TableColumn<ViewableContact, String> genreGroupColumn;
    @FXML
    private TableColumn<ViewableContact, String> datedenaissanceColumn;
    @FXML
    private TableColumn<ViewableContact, String> telephoneproColumn;
    @FXML
    private TableColumn<ViewableContact, String> pseudoColumn;
    @FXML
    private TableColumn<ViewableContact, String> liengitColumn;

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

    // Noms des RadioButtons
    // + ToggleGroup qui réunit les 3 boutons
    @FXML
    private ToggleGroup genreGroup = new ToggleGroup();
    @FXML
    private RadioButton hommeRadio = new RadioButton("Homme");
    @FXML
    private RadioButton femmeRadio = new RadioButton("Femme");
    @FXML
    private RadioButton nonBinaireRadio = new RadioButton("Non-binaire");

    // la partie de la fenêtre "Informations facultatives"
    @FXML
    private DatePicker dateNaissanceField;
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

    // list of contacts ; is used by serializers
    // is the list which is interacted with when pressing edition buttons
    private List<Contact> observableContactList = new ArrayList<>();

    // ObservableList is an observable representation of the previous contacts list
    private ObservableList<ViewableContact> viewableContactsList = FXCollections.observableArrayList();

    // serializers used in various methods
    private ContactBinarySerializer binarySerializer = new ContactBinarySerializer();
    private ContactVCardSerializer vCardSerializer = new ContactVCardSerializer();
    private ContactJSONSerializer jsonSerializer = new ContactJSONSerializer();

    // initialiser un contact "temporaire"
    private Contact contactTemp = new Contact(null, null, null, null, null, null, Contact.Gender.MALE, null, null, null,
            null);

    @FXML
    private void initialize() {

        hommeRadio.setToggleGroup(genreGroup);
        femmeRadio.setToggleGroup(genreGroup);
        nonBinaireRadio.setToggleGroup(genreGroup);

        // Ajouter des contacts fictifs pour le test
        // observableContactList.add(new Contact("Dupont", "Jean", "0123456789",
        // "jean.dupont@example.com",
        // "1 rue de Paris",
        // "75000", Contact.Gender.NON_BINARY, null, "0987654321", "jdupont",
        // "https://github.com/jdupont"));
        // observableContactList
        // .add(new Contact("Zannese", "Aurélie", "0987654321",
        // "jean.dupont@example.com", "1 rue de Paris",
        // "75000", Contact.Gender.FEMALE, null, "0987654321", "jdupont",
        // "https://github.com/jdupont"));
        // observableContactList
        // .add(new Contact("Ford", "Mélanie", "0854796314", "jean.dupont@example.com",
        // "1 rue de Paris",
        // "75000", Contact.Gender.MALE, LocalDate.of(1985, 10, 26), "0987654321",
        // "jdupont",
        // "https://github.com/jdupont"));

        // convertir les Contact en ViewableContact

        ContactBinarySerializer binarySerializer = new ContactBinarySerializer();
        ArrayList<Contact> contacts = binarySerializer.loadList("contacts.serial");
        if (contacts != null) {
            for (Contact contact : contacts) {
                observableContactList.add(contact);
            }
        }

        for (Contact contact : observableContactList) {
            viewableContactsList.add(new ViewableContact(contact));
        }
        // Ajouter les données au TableView
        contactsTable.setItems(viewableContactsList);

        // Initialiser les colonnes du TableView
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephonePersonnelProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        adresseColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        codepostaleColumn.setCellValueFactory(cellData -> cellData.getValue().codePostalProperty());
        genreGroupColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        datedenaissanceColumn.setCellValueFactory(cellData -> cellData.getValue().dateNaissanceProperty());
        telephoneproColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProfessionnelProperty());
        pseudoColumn.setCellValueFactory(cellData -> cellData.getValue().pseudoProperty());
        liengitColumn.setCellValueFactory(cellData -> cellData.getValue().lienDepotGitProperty());

        nomField.setOnKeyTyped(event -> contactTemp.setLastName(nomField.getText()));
        prenomField.setOnKeyTyped(event -> contactTemp.setFirstName(prenomField.getText()));
        telephonePersonnelField.setOnKeyTyped(event -> contactTemp.setPersoPhone(telephonePersonnelField.getText()));
        emailField.setOnKeyTyped(event -> contactTemp.setEmail(emailField.getText()));
        adresseField.setOnKeyTyped(event -> contactTemp.setAddress(adresseField.getText()));
        codePostalField.setOnKeyTyped(event -> contactTemp.setZipCode(codePostalField.getText()));
        // codePostalField.setOnKeyTyped(event ->
        // contactTemp.setZipCode(codePostalField.getText()));
        telephoneProfessionnelField
                .setOnKeyTyped(event -> contactTemp.setProPhone(telephoneProfessionnelField.getText()));
        pseudoField.setOnKeyTyped(event -> contactTemp.setPseudo(pseudoField.getText()));
        lienDepotGitField.setOnKeyTyped(event -> contactTemp.setGitLink(lienDepotGitField.getText()));

        genreGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton checkedButton = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                    switch (checkedButton.getText()) {
                        case "Homme":
                            contactTemp.setGender(Contact.Gender.MALE);
                            break;
                        case "Femme":
                            contactTemp.setGender(Contact.Gender.FEMALE);
                            break;
                        case "Non-binaire":
                            contactTemp.setGender(Contact.Gender.NON_BINARY);
                            break;
                    }

                }
            }
        });

        nouveauButton.setOnAction(event -> handleNouveau());
        supprimerButton.setOnAction(event -> handleSupprimer());
        sauvegarderButton.setOnAction(event -> handleEnregistrer());
        vCardButton.setOnAction(event -> handleVCard());
        jsonButton.setOnAction(event -> handleJson());

        contactsTable.setOnMouseClicked(event -> showContactDetails());

    }

    // Bouton ajouter :
    @FXML
    private void handleNouveau() {
        if (contactTemp.verifyContact()) {
            observableContactList.add(contactTemp);
            viewableContactsList.add(new ViewableContact(contactTemp));
            contactsTable.setItems(viewableContactsList);
            clearFields();
        } else {
            showAlert("Information erronée", "alerte");
        }
    }

    @FXML
    private void handleModifier() {
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            nomField.setText(selectedContact.getNom());
            prenomField.setText(selectedContact.getPrenom());
            adresseField.setText(selectedContact.getAdresse());
            emailField.setText(selectedContact.getEmail());
            codePostalField.setText(selectedContact.getCodePostal());
            telephonePersonnelField.setText(selectedContact.getTelephonePersonnel());
            // select the correponding toggle ; switch case probably
            // genreGroup.getToggles()(selectedContact.getNom());

            // select the date in DatePicker
            // nomField.setText(selectedContact.getNom());
            telephoneProfessionnelField.setText(selectedContact.getTelephoneProfessionnel());
            pseudoField.setText(selectedContact.getPseudo());
            lienDepotGitField.setText(selectedContact.getLienDepotGit());
        }
    }

    @FXML
    private void handleEnregistrer() {

        ArrayList<Contact> contactsToSave = new ArrayList<>();
        for (Contact contactLoop : observableContactList) {
            contactsToSave.add(contactLoop);
        }
        binarySerializer.saveList("contacts.serial", contactsToSave);
    }

    @FXML
    private void handleSupprimer() {
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            viewableContactsList.remove(selectedContact);
            ArrayList<Contact> newViewableContactsList = new ArrayList<>();
            for (ViewableContact contact : viewableContactsList) {
                newViewableContactsList.add(new Contact(contact.getPrenom(),
                        contact.getNom(),
                        contact.getTelephonePersonnel(),
                        contact.getEmail(), contact.getAdresse(),
                        contact.getCodePostal(),
                        Contact.Gender.MALE,
                        LocalDate.now(),
                        contact.getTelephoneProfessionnel(),
                        contact.getPseudo(),
                        contact.getLienDepotGit()));
            }

            this.contactsTable.setItems(viewableContactsList);
            System.out.println(newViewableContactsList);
            binarySerializer.saveList("contacts.serial", newViewableContactsList);
            observableContactList = newViewableContactsList;

        }
    }

    @FXML
    private void showContactDetails() {
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            nomField.setText(selectedContact.getNom());
            prenomField.setText(selectedContact.getPrenom());
            telephonePersonnelField.setText(selectedContact.getTelephonePersonnel());
            emailField.setText(selectedContact.getEmail());
            adresseField.setText(selectedContact.getAdresse());
            codePostalField.setText(selectedContact.getCodePostal());
            // setselectGenre(newContact);

            // formater la LocalDate en String
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            // String formattedBirthDate = selectedContact.getBirthDate().format(formatter);

            // dateNaissanceField.setValue(selectedContact.getBirthDate());
            telephoneProfessionnelField.setText(selectedContact.getTelephoneProfessionnel());
            pseudoField.setText(selectedContact.getPseudo());
            lienDepotGitField.setText(selectedContact.getLienDepotGit());
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
            dateNaissanceField.setValue(null);
            telephoneProfessionnelField.setText("");
            pseudoField.setText("");
            lienDepotGitField.setText("");
        }

    }

    @FXML
    private void handleSelectAll() {
        contactsTable.getSelectionModel().selectAll();
    }

    @FXML
    private void handleClear() {
        contactsTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleVCard() {
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        for (Contact contact : observableContactList) {
            if (contact.getLastName().equals(selectedContact.getNom())
                    && contact.getFirstName().equals(selectedContact.getPrenom())
                    && contact.getEmail().equals(selectedContact.getEmail())) {
                vCardSerializer.save(selectedContact.getNom() + selectedContact.getPrenom() + ".vcf", contact);
            }
        }
    }

    @FXML
    private void handleJson() {
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        for (Contact contact : observableContactList) {
            if (contact.getLastName().equals(selectedContact.getNom())
                    && contact.getFirstName().equals(selectedContact.getPrenom())
                    && contact.getEmail().equals(selectedContact.getEmail())) {
                jsonSerializer.save(selectedContact.getNom() + selectedContact.getPrenom(), contact);
            }
        }
    }

    @FXML
    private void handleQuitter() {
        System.exit(0);
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        telephonePersonnelField.clear();
        emailField.clear();
        adresseField.clear();
        codePostalField.clear();
        dateNaissanceField.setValue(null);
        telephoneProfessionnelField.clear();
        pseudoField.clear();
        lienDepotGitField.clear();
    }

    /*
     * Gestion des Erreurs dans l'Interface Utilisateur
     * 1. Affichage des Erreurs
     * Utilisez les alertes pour informer l'utilisateur des erreurs de validation ou
     * d'autres problèmes. Voici comment la méthode showAlert est utilisée pour
     * afficher des messages :
     */

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
