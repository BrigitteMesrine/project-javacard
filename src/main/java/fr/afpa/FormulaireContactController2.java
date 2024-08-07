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
import org.apache.log4j.*;;


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
    private List<Contact> contactsList = new ArrayList<>();

    // ObservableList is an observable representation of the previous contacts list
    private ObservableList<ViewableContact> viewableContactsList = FXCollections.observableArrayList();
    

    // serializers used in various methods
    private ContactBinarySerializer binarySerializer = new ContactBinarySerializer();
    private ContactVCardSerializer vCardSerializer = new ContactVCardSerializer();
    private ContactJSONSerializer jsonSerializer = new ContactJSONSerializer();

    // initialiser un contact "temporaire"
    private Contact inputContact = new Contact(null, null, null, null, null, null, Contact.Gender.MALE, null, null,
            null,
            null);

    // private static final Logger logger = Logger.getLogger(FormulaireContactController2.class);

    @FXML
    private void initialize() {

        hommeRadio.setToggleGroup(genreGroup);
        femmeRadio.setToggleGroup(genreGroup);
        nonBinaireRadio.setToggleGroup(genreGroup);
        
        contactsList.add(new Contact("Dupont", "Jean", "0123456789",
        "jean.dupont@example.com",
        "1 rue de Paris",
        "75000", Contact.Gender.NON_BINARY, null, "0987654321", "jdupont",
        "https://github.com/jdupont"));
        contactsList
        .add(new Contact("Zannese", "Aurélie", "0987654321",
        "jean.dupont@example.com", "1 rue de Paris",
        "75000", Contact.Gender.FEMALE, null, "0987654321", "jdupont",
        "https://github.com/jdupont"));
        contactsList
        .add(new Contact("Ford", "Mélanie", "0854796314", "jean.dupont@example.com",
        "1 rue de Paris",
        "75000", Contact.Gender.MALE, LocalDate.of(1985, 10, 26), "0987654321",
        "jdupont",
        "https://github.com/jdupont"));

        // convertir les Contact en ViewableContact

        ContactBinarySerializer binarySerializer = new ContactBinarySerializer();
        ArrayList<Contact> contacts = binarySerializer.loadList("contacts.serial");
        if (contacts != null) {
            for (Contact contact : contacts) {
                contactsList.add(contact);
            }
        }

        for (Contact contact : contactsList) {
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

        genreGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton checkedButton = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                    switch (checkedButton.getText()) {
                        case "Homme":
                            inputContact.setGender(Contact.Gender.MALE);
                            break;
                        case "Femme":
                            inputContact.setGender(Contact.Gender.FEMALE);
                            break;
                        case "Non-binaire":
                            inputContact.setGender(Contact.Gender.NON_BINARY);
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
        selectAllButton.setOnAction(event -> handleSelectAll());
        clearButton.setOnAction(event -> handleClear());

        contactsTable.setOnMouseClicked(event -> showContactDetails());

    }

    // TODO create method to prevent creating a existing contact
    @FXML
    private void handleNouveau() {
        
        inputContact.setLastName(nomField.getText());
        inputContact.setFirstName(prenomField.getText());
        inputContact.setPersoPhone(telephonePersonnelField.getText());
        inputContact.setEmail(emailField.getText());
        inputContact.setAddress(adresseField.getText());
        inputContact.setZipCode(codePostalField.getText());
        inputContact.setProPhone(telephoneProfessionnelField.getText());
        inputContact.setBirthDate(dateNaissanceField.getValue());
        inputContact.setPseudo(pseudoField.getText());
        inputContact.setGitLink(lienDepotGitField.getText());
        System.out.println(inputContact);
        if (
            !nomField.getText().isEmpty() &&
            !prenomField.getText().isEmpty() &&
            !emailField.getText().isEmpty() &&
            !adresseField.getText().isEmpty() &&
            !codePostalField.getText().isEmpty()
        ) {
            contactsList.add(inputContact);
            viewableContactsList.add(new ViewableContact(inputContact));
            contactsTable.setItems(viewableContactsList);
            clearFields();
        } else {
            showAlert("Information erronée", "alerte");
        }
    }

    private boolean isNotInList() {
        boolean isNotInList = false;
        for (Contact contact : contactsList) {
            if (nomField.getText() != contact.getLastName()
                    && prenomField.getText() != contact.getFirstName()
                    && adresseField.getText() != contact.getAddress()
                    && emailField.getText() != contact.getEmail()
                    // && genreGroup.getSelectedToggle().getUserData() != contact.getLastName()
                    && telephonePersonnelField.getText() != contact.getPersoPhone()
                    && dateNaissanceField.getValue() != contact.getBirthDate()
                    && telephoneProfessionnelField.getText() != contact.getProPhone()
                    && pseudoField.getText() != contact.getPseudo()
                    && lienDepotGitField.getText() != contact.getGitLink()) {
                isNotInList = true;
            } else {
                clearFields();
            }
        }
        return isNotInList;
    }

    @FXML
    private void handleModifier() {

        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {

            ArrayList<Contact> newViewableContactsList = new ArrayList<>();
            for (ViewableContact contact : viewableContactsList) {
                newViewableContactsList.add(new Contact(contact.getPrenom(),
                        contact.getNom(),
                        contact.getTelephonePersonnel(),
                        contact.getEmail(), contact.getAdresse(),
                        contact.getCodePostal(),
                        contact.getRawGender(),
                        contact.getRawBirthDate(),
                        contact.getTelephoneProfessionnel(),
                        contact.getPseudo(),
                        contact.getLienDepotGit()));
            }

            Contact modifiedContact = new Contact(prenomField.getText(),
                    nomField.getText(),
                    telephonePersonnelField.getText(),
                    emailField.getText(),
                    adresseField.getText(),
                    codePostalField.getText(),
                    inputContact.getGender(),
                    dateNaissanceField.getValue(),
                    telephoneProfessionnelField.getText(),
                    pseudoField.getText(),
                    lienDepotGitField.getText());

            if (modifiedContact.verifyContact()) {
                viewableContactsList.remove(selectedContact);
                viewableContactsList.add(new ViewableContact(modifiedContact));
                contactsList = newViewableContactsList;
                contactsList.add(modifiedContact);
                contactsTable.setItems(viewableContactsList);
            }

        }

    }

    @FXML
    private void handleEnregistrer() {

        ArrayList<Contact> contactsToSave = new ArrayList<>();
        for (Contact contactLoop : contactsList) {
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
                        contact.getRawGender(),
                        contact.getRawBirthDate(),
                        contact.getTelephoneProfessionnel(),
                        contact.getPseudo(),
                        contact.getLienDepotGit()));
            }

            contactsTable.setItems(viewableContactsList);
            contactsList = newViewableContactsList;

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
            dateNaissanceField.setValue(selectedContact.getRawBirthDate());
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
        for (Contact contact : contactsList) {
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
        for (Contact contact : contactsList) {
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
