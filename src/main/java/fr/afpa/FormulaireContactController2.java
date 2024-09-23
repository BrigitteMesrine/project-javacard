package fr.afpa;

import fr.afpa.models.Contact;
import fr.afpa.models.ViewableContact;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private Label verif = new Label();

    // Déclare une variable pour suivre l'état de disponibilité de l'action.
    private boolean isActionAvailable = false;

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
        // System.out.println(contacts.toString());
        if (contacts != null) {
            for (Contact contact : contacts) {
                contactsList.add(contact);
            }
        }

        System.out.println(contactsList);

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
        // nouveauButton.setOnAction(event -> handleNouveau());
        // supprimerButton.setOnAction(event -> handleSupprimer());
        // sauvegarderButton.setOnAction(event -> handleEnregistrer());
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

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Nouveau validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

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
        if (!nomField.getText().isEmpty() &&
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

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Modifier validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

        // on récupère le contact séléctionné dans la tableView -> selectedContact
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();

        // si le contact existe (par sécurité)
        if (selectedContact != null) {

            // on crée une nouvelle liste d'objets Contact
            ArrayList<Contact> newContactsList = new ArrayList<>();

            // // on remplit la liste avec les objets de l'"ancienne" liste de Contact contactsList
            // for (ViewableContact contact : viewableContactsList) {
            //     newContactsList.add(new Contact(contact.getPrenom(),
            //             contact.getNom(),
            //             contact.getTelephonePersonnel(),
            //             contact.getEmail(), contact.getAdresse(),
            //             contact.getCodePostal(),
            //             contact.getRawGender(),
            //             contact.getRawBirthDate(),
            //             contact.getTelephoneProfessionnel(),
            //             contact.getPseudo(),
            //             contact.getLienDepotGit()));
            // }

            // on crée un nouveau contact à partir des champs modifiés
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

            // si les champs modifiés sont valides (c'est là qu'il faudra mettre isRegexValid)      
            if (!nomField.getText().isEmpty() &&
            !prenomField.getText().isEmpty() &&
            !emailField.getText().isEmpty() &&
            !adresseField.getText().isEmpty() &&
            !codePostalField.getText().isEmpty()) {

                // 1) on retire de la TableView le contact séléctionné qui a été modifié
                viewableContactsList.remove(selectedContact);

                // 2) on y ajoute la version modifiée du contact
                viewableContactsList.add(new ViewableContact(modifiedContact));

                // on met à jour la liste de Contact sérialisable
                // contactsList = newContactsList;

                contactsList.add(modifiedContact);
                contactsTable.setItems(viewableContactsList);
            }

        }

    }

    @FXML
    private void handleEnregistrer() {

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Sauvegarder validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

        ArrayList<Contact> contactsToSave = new ArrayList<>();
        for (Contact contactLoop : contactsList) {
            contactsToSave.add(contactLoop);
        }
        binarySerializer.saveList("contacts.serial", contactsToSave);
    }

    @FXML
    private void handleSupprimer() {

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Supprimer validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

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

            // cas où on a cliqué sur un "Contact", on active le bouton
            sauvegarderButton.setDisable(false);

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

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Select All validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

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

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Quitter validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

        System.exit(0);
    }

    private void clearFields() {

        // Méthode appelée lorsque le bouton "actionButton" est cliqué
        System.out.println("Bouton Clear validé!");
        // Affiche un message dans la console pour indiquer que l'action a été réalisée

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

    @FXML
    private void toggleButtonState() {
        // Méthode appelée lorsque le bouton "Toggle Button State" est cliqué
        isActionAvailable = !isActionAvailable;
        // Inverse l'état de isActionAvailable
        updateButtonState();
        // Met à jour l'état du bouton pour refléter le nouvel état de isActionAvailable
    }

    @FXML
    public void handleTextFieldChanged(KeyEvent event) {

        // Vérification des champs obligatoire du formulaire
        // Si c'est pas vide, alors on active le bouton
        // Règle d'inversion d'une expression booléenne :
        // - les || deviennent des && (les "OU" deviennent des "ET")
        // - on doit ajouter la négation sur chacune des opérandes booléennes
        // Empty = Vide --> "" est vide
        if (!nomField.getText().isEmpty() &&
                !prenomField.getText().isEmpty() &&
                !telephonePersonnelField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !adresseField.getText().isEmpty() &&
                !codePostalField.getText().isEmpty()) {

            // ici, les champs sont bien renseignés, on active le bouton !
            sauvegarderButton.setDisable(false);
            } else {
            //sinon, on force la désactivation
            sauvegarderButton.setDisable(true);
        }

        if (nomField.getText().isEmpty() && 
            prenomField.getText().isEmpty() &&
            telephonePersonnelField.getText().isEmpty() &&
            emailField.getText().isEmpty() &&
            adresseField.getText().isEmpty() &&
            codePostalField.getText().isEmpty()) {
            // là on change la couleur
            nomField.setStyle("-fx-background-color: red");
            prenomField.setStyle("-fx-background-color: red");
            telephonePersonnelField.setStyle("-fx-background-color: red");
            emailField.setStyle("-fx-background-color: red");
            adresseField.setStyle("-fx-background-color: red");
            codePostalField.setStyle("-fx-background-color: red");
            //App.popToast("Veuillez entrer un nom");
        
        
        } else {
            // sinon, on force la désactivation
            nomField.setStyle("-fx-background-color: white");
            prenomField.setStyle("-fx-background-color: white");
            telephonePersonnelField.setStyle("-fx-background-color: white");
            emailField.setStyle("-fx-background-color: white");
            adresseField.setStyle("-fx-background-color: white");
            codePostalField.setStyle("-fx-background-color: white");

        
        }
    }
    

    /**
     * Méthode qui sera appelée à chaque modification d'un champ de texte.
     */
    // @FXML
    // public void handleNomTextFieldChanged(KeyEvent event) {

    // // Vérification des champs obligatoire du formulaire
    // // Si c'est pas vide, alors on active le bouton
    // // Règle d'inversion d'une expression booléenne :
    // // - les || deviennent des && (les "OU" deviennent des "ET")
    // // - on doit ajouter la négation sur chacune des opérandes booléennes
    // // Empty = Vide --> "" est vide
    // if (!nomField.getText().isEmpty() &&
    // !prenomField.getText().isEmpty() &&
    // !emailField.getText().isEmpty() &&
    // !adresseField.getText().isEmpty() &&
    // !codePostalField.getText().isEmpty()) {

    // // ici, les champs sont bien renseignés, on active le bouton !
    // sauvegarderButton.setDisable(false);

    // } else {
    // // sinon, on force la désactivation
    // sauvegarderButton.setDisable(true);

    // }

    // // Par exemple, pour la vérification du nom de famille
    // if (nomField.getText().isEmpty() && prenomField.getText().isEmpty()) {
    // // là on change la couleur
    // nomField.setStyle("-fx-background-color: red");
    // App.popToast("Veuillez entrer un nom");
    // }

    // else {
    // nomField.setStyle("-fx-background-color: white");
    // }

    // }

    // @FXML
    // public void handlePrenomTextFieldChanged(KeyEvent event) {

    // // Vérification des champs obligatoire du formulaire
    // // Si c'est pas vide, alors on active le bouton
    // // Règle d'inversion d'une expression booléenne :
    // // - les || deviennent des && (les "OU" deviennent des "ET")
    // // - on doit ajouter la négation sur chacune des opérandes booléennes
    // // Empty = Vide --> "" est vide
    // if (!nomField.getText().isEmpty() &&
    // !prenomField.getText().isEmpty() &&
    // !emailField.getText().isEmpty() &&
    // !adresseField.getText().isEmpty() &&
    // !codePostalField.getText().isEmpty()) {

    // // ici, les champs sont bien renseignés, on active le bouton !
    // sauvegarderButton.setDisable(false);

    // } else {
    // // sinon, on force la désactivation
    // sauvegarderButton.setDisable(true);

    // }

    // // Par exemple, pour la vérification du nom de famille
    // if (prenomField.getText().isEmpty()) {
    // // là on change la couleur
    // // nomField.setStyle("-fx-background-color: red");
    // App.popToast("Veuillez entrer un prénom");
    // }
    // }


    /**
     * Méthode qui va permettre d'activer ou de désactiver les boutons en fonction
     * du contenu du formulaire.
     * 
     * Cette méthode sera appelée à chaque changement de contenu d'un champ de
     * texte.
     * Pour la déclencher à chaque modification de contenu, ajout de la gestion
     * d'évènements.
     * 
     */
    private void updateButtonState() {
        // Méthode pour mettre à jour l'état du bouton "actionButton"
        // Désactive le bouton si isActionAvailable est false, et l'active si true
        // actionButton.setDisable(!isActionAvailable);
        // if (isActionAvailable) {
        // actionButton.getStyleClass().remove("disabled-button");
        // actionButton.getStyleClass().add("enabled-button");
        // statusLabel.setText("Status: Action Available");
        // } else {
        // actionButton.getStyleClass().remove("enabled-button");
        // actionButton.getStyleClass().add("disabled-button");
        // statusLabel.setText("Status: Action Unavailable");
        // }
    }
}
