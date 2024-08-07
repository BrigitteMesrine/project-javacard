package fr.afpa;

import fr.afpa.models.Contact;
import fr.afpa.models.ViewableContact;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class FormulaireContactController2 {

    // <-- TABLEVIEW -->
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

    // <-- MANDATORY INFORMATIONS -->
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

    // RadioButton objects group
    @FXML
    private ToggleGroup genreGroup = new ToggleGroup();
    @FXML
    private RadioButton hommeRadio = new RadioButton("Homme");
    @FXML
    private RadioButton femmeRadio = new RadioButton("Femme");
    @FXML
    private RadioButton nonBinaireRadio = new RadioButton("Non-binaire");

    // <-- FACULTATIVE INFORMATIONS -->
    @FXML
    private DatePicker dateNaissanceField;
    @FXML
    private TextField telephoneProfessionnelField;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField lienDepotGitField;

    // <-- EXPORTS AND EDITION BUTTONS -->
    @FXML
    private Button selectAllButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button vCardButton;
    @FXML
    private Button jsonButton;
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

    // <-- ATTRIBUTES TO STORE DATA -->
    // contactsList stores serializable Contact objects
    private List<Contact> contactsList = new ArrayList<>();

    // viewableContactsList stores displayable ViewableContact objects
    private ObservableList<ViewableContact> viewableContactsList = FXCollections.observableArrayList();

    /**
     * binarySerializer serves both ini initialize(), for loading contacts
     * and in handleSauvegarder(), for saving contacts
     */
    private ContactBinarySerializer binarySerializer = new ContactBinarySerializer();

    /**
     * inputContact is the contact currently defined by the value of TextFields ; used in
     * listenToggleGroup(), handleNouveau(), handleModifier()
     */
    private Contact inputContact = new Contact(null, null, null, null, null, null, null, null, null, null, null);

    @FXML
    private void initialize() {

        // <-- BINARY DESERIALIZATION TO LOAD CONTACTS INTO LISTS -->

        // serializable Contact objects are deserialized from persistent "contacts.serial"
        // and loaded into an ArrayList
        ArrayList<Contact> contacts = binarySerializer.loadList("contacts.serial");
        if (contacts != null) {
            for (Contact contact : contacts) {
                contactsList.add(contact);
            }
        }

        // conversion of deserialized Contact objects attributes
        // to ObjectProperty attributes into ViewableContact
        for (Contact contact : contactsList) {
            viewableContactsList.add(new ViewableContact(contact));
        }

        // <-- TABLEVIEW COLUMNS DATA INITIALIZATION -->

        // setting ViewableContact objects into TableView
        contactsTable.setItems(viewableContactsList);

        // 1 row = 1 ViewableContact
        // 1 column = 1 attribute from ViewableContact
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

        // setting TableView selection mode to multiple,
        // in order to exports multiple contacts to vCard and JSON
        contactsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // <-- EVENTS LISTENERS -->

        genreGroup.selectedToggleProperty().addListener(listenToggleGroup());
        nouveauButton.setOnAction(event -> handleNouveau());
        supprimerButton.setOnAction(event -> handleSupprimer());
        sauvegarderButton.setOnAction(event -> handleEnregistrer());
        vCardButton.setOnAction(event -> export(".vcf"));
        jsonButton.setOnAction(event -> export(".json"));
        selectAllButton.setOnAction(event -> handleSelectAll());
        clearButton.setOnAction(event -> handleClear());
        contactsTable.setOnMouseClicked(event -> showContactDetails());

    }

    /**
     * Listens to changes from a ToggleGroup. The text associated with the Toggle is then
     * used in a switch to set the value of an Object attribute.
     * @return {@link ChangeListener}
     */
    public ChangeListener<Toggle> listenToggleGroup() {
        return new ChangeListener<Toggle>() {
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
        };
    }

    // TODO implement Regex check to handleNouveau and handleModifier 

    @FXML
    private void handleNouveau() {

        inputContact.setLastName(nomField.getText());
        inputContact.setFirstName(prenomField.getText());
        inputContact.setPersoPhone(telephonePersonnelField.getText());
        inputContact.setEmail(emailField.getText());
        inputContact.setAddress(adresseField.getText());
        inputContact.setZipCode(codePostalField.getText());
        // gender is set with listenGenreGroup()
        inputContact.setProPhone(telephoneProfessionnelField.getText());
        inputContact.setBirthDate(dateNaissanceField.getValue());
        inputContact.setPseudo(pseudoField.getText());
        inputContact.setGitLink(lienDepotGitField.getText());
        if (isRegexValid() && !nomField.getText().isEmpty() &&
                !prenomField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !adresseField.getText().isEmpty() &&
                !codePostalField.getText().isEmpty()) {
            contactsList.add(inputContact);
            viewableContactsList.add(new ViewableContact(inputContact));
            contactsTable.setItems(viewableContactsList);
            clearFields();
            genreGroup.getSelectedToggle().setSelected(false);
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

    private boolean isRegexValid() {
        boolean isRegexValid = false;

        isRegexValid = nomField.getText().toLowerCase().matches("[a-z]") 
            && prenomField.getText().toLowerCase().matches("[a-z]");

        return isRegexValid;
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

            if (!nomField.getText().isEmpty() &&
            !prenomField.getText().isEmpty() &&
            !emailField.getText().isEmpty() &&
            !adresseField.getText().isEmpty() &&
            !codePostalField.getText().isEmpty()) {
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
            // genreGroup.;
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

    private void export(String fileExtension) {
        ContactVCardSerializer vCardSerializer = new ContactVCardSerializer();
        ContactJSONSerializer jsonSerializer = new ContactJSONSerializer();
        Serializer<Contact> superserializer = null;
        ObservableList<ViewableContact> viewableContactSelection = contactsTable.getSelectionModel().getSelectedItems();
        List<Contact> contactSelection = new ArrayList<>();
        switch (fileExtension) {
            case ".vcf":
                superserializer = vCardSerializer;
                break;
            case ".json":
                superserializer = jsonSerializer;
                break;
        }
        for (ViewableContact contact : viewableContactSelection) {
            contactSelection.add(contact.getContact());
        }
        if (viewableContactSelection.size() == 1) {
            for (ViewableContact selectedContact : viewableContactSelection) {
                superserializer.save(selectedContact.getPrenom() + fileExtension, selectedContact.getContact());
            }
        } else {
            superserializer.saveList("allcontacts" + fileExtension, contactSelection);
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
}
