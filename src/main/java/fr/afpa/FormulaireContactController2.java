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

    private ObservableList<Contact> observableContactList = FXCollections.observableArrayList();
    private ObservableList<ViewableContact> viewableContactsList = FXCollections.observableArrayList();

    private ViewableContact viewableContact;

            // initialiser un contact "temporaire"
    private Contact contactTemp = new Contact(null, null, null, null, null, null, null, null, null, null, null);


    public FormulaireContactController2() {
        // Ajouter des données d'exemple

    }

    @FXML
    private void initialize() {

        hommeRadio.setToggleGroup(genreGroup);
        femmeRadio.setToggleGroup(genreGroup);
        nonBinaireRadio.setToggleGroup(genreGroup);

        // Ajouter des contacts fictifs pour le test
        observableContactList.add(new Contact("Dupont", "Jean", "0123456789", "jean.dupont@example.com",
                "1 rue de Paris",
                "75000", Contact.Gender.NON_BINARY, null, "0987654321", "jdupont", "https://github.com/jdupont"));
        observableContactList
                .add(new Contact("Zannese", "Aurélie", "0987654321", "jean.dupont@example.com", "1 rue de Paris",
                        "75000", Contact.Gender.FEMALE, null, "0987654321", "jdupont", "https://github.com/jdupont"));
        observableContactList
                .add(new Contact("Ford", "Mélanie", "0854796314", "jean.dupont@example.com", "1 rue de Paris",
                        "75000", Contact.Gender.MALE, LocalDate.of(1985, 10, 26), "0987654321", "jdupont",
                        "https://github.com/jdupont"));

        // convertir les Contact en ViewableContact

        for (Contact contact : observableContactList) {
            viewableContactsList.add(new ViewableContact(contact.getLastName(), contact.getFirstName(),
                    contact.getPersoPhone(), contact.getEmail(),
                    contact.getAddress(), contact.getZipCode(), contact.getGender(), contact.getBirthDate(),
                    contact.getProPhone(), contact.getPseudo(), contact.getGitLink()));
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
        sauvegarderButton.setOnAction(event -> System.out.println(contactTemp.toString()));

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
    }

    // Gestion des événements (à implémenter)
    @FXML
    private void handleNouveau() {
        // Logique pour créer un nouveau contact

        observableContactList.add(contactTemp);

        if (contactTemp.verifyContact(contactTemp)) {
            viewableContactsList.clear();
            for (Contact contact : observableContactList) {
                viewableContactsList.add(new ViewableContact(contact.getLastName(), contact.getFirstName(),
                        contact.getPersoPhone(), contact.getEmail(),
                        contact.getAddress(), contact.getZipCode(), contact.getGender(), contact.getBirthDate(),
                        contact.getProPhone(), contact.getPseudo(), contact.getGitLink()));
            }
            contactsTable.setItems(viewableContactsList);
            showContactDetails(contactTemp);
            contactTemp = new Contact(null, null, null, null, null, null, null, null, null, null, null);
            //clearFields();
        } else {
            System.out.println("Erreur");
        }
    }

    @FXML
    private void handleModifier() {
        // Logique pour modifier un contact existant
        ViewableContact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setNom(nomField.getText());
            selectedContact.setPrenom(prenomField.getText());
            selectedContact.setTelephonePersonnel(telephonePersonnelField.getText());
            selectedContact.setEmail(emailField.getText());
            selectedContact.setAdresse(adresseField.getText());
            selectedContact.setCodePostal(codePostalField.getText());
            // selectedContact.setGenre(getSelectedGenre());
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

        // TODO déclarer le Togglegroup
        Enum<Contact.Gender> genre = null;
        // switch (genreGroup.getSelectedToggle()) {
        // case hommeRadio.selectedProperty() == true:
        // genre = Contact.Gender.MALE;
        // break;
        // case femmeRadio:
        // genre = Contact.Gender.FEMALE;
        // break;
        // case nonBinaireRadio:
        // genre = Contact.Gender.NON_BINARY;
        // break;

        // default:
        // break;
        // }

        LocalDate dateNaissance = dateNaissanceField.getValue();
        String telephoneProfessionnel = telephoneProfessionnelField.getText();
        String pseudo = pseudoField.getText();
        String lienDepotGit = lienDepotGitField.getText();

        Contact contact = new Contact(nom, prenom, telephonepersonnel, email, adresse, codePostal, genre,
                dateNaissance, telephoneProfessionnel, pseudo, lienDepotGit);
        observableContactList.add(contact);

        // Vider le formulaire
        nomField.setText("");
        prenomField.setText("");
        telephonePersonnelField.setText("");
        emailField.setText("");
        codePostalField.setText("");
        dateNaissanceField.setValue(null);
        telephoneProfessionnelField.setText("");
        pseudoField.setText("");
        lienDepotGitField.setText("");

        /*
         * 1. Gestion des Exceptions lors de la Sauvegarde
         * Ajoutez une gestion appropriée des exceptions dans les méthodes de sauvegarde
         * pour gérer les erreurs d'entrée/sortie et autres problèmes :
         */
        /*
         * FileChooser fileChooser = new FileChooser();
         * File file = fileChooser.showSaveDialog(null);
         * if (file != null) {
         * try {
         * // Sérialisation des contacts
         * ContactJsonSerializer serializer = new ContactJsonSerializer();
         * serializer.saveList(file.getAbsolutePath(), new ArrayList<>(contactList));
         * } catch (IOException e) {
         * showAlert("Error", "Could not save data", "Could not save data to file:\n" +
         * file.getAbsolutePath() + "\n" + e.getMessage());
         * }
         * }
         */

        // Création du contact avec les données des champs
        /*
         * 1. Validation des Champs dans le Contrôleur
         * Vous pouvez ajouter des méthodes de validation spécifiques pour chaque champ
         * de saisie dans le contrôleur. Voici comment intégrer ces validations dans la
         * méthode handleSaveContact() :
         */
        TableColumnBase<ViewableContact, String> telephonePersoField;
        viewableContact = new ViewableContact(
                contact.getLastName(),
                contact.getFirstName(),
                contact.getPersoPhone(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getZipCode(),
                contact.getGender(),
                contact.getBirthDate(),
                contact.getProPhone(),
                contact.getPseudo(),
                contact.getGitLink());

        // Validation des champs
        String validationMessage = validateContact(viewableContact);

        if (validationMessage == null) {
            // Ajouter le contact à la liste si valide
            observableContactList.add(contact);
            clearFields();
        } else {
            // Afficher un message d'erreur si la validation échoue
            showAlert("Validation Error", "Invalid Contact", validationMessage);
        }
    }

    /*
     * 2. Méthode de Validation des Contacts
     * Créez une méthode validateContact(Contact contact) pour vérifier chaque champ
     * :
     */

    private String validateContact(ViewableContact ViewableContact) {
        StringBuilder errors = new StringBuilder();

        // Validation du nom
        if (ViewableContact.getNom().trim().isEmpty()) {
            errors.append("Le nom est requis.\n");
        }

        // Validation du prénom
        if (ViewableContact.getPrenom().trim().isEmpty()) {
            errors.append("Le prénom est requis.\n");
        }

        // Validation du téléphone personnel
        if (!ViewableContact.isPhoneValid()) {
            errors.append("Numéro de téléphone personnel invalide. Il devrait y avoir 10 chiffres.\n");
        }

        // Validation de l'email
        if (!ViewableContact.isEmailValid()) {
            errors.append("Adresse e-mail invalide.\n");
        }

        // Validation du code postal (exemple simple)
        if (ViewableContact.getCodePostal().trim().isEmpty()) {
            errors.append("Postal code is required.\n");
        }

        // Validation de la date de naissance (exemple simple)
        // if (!ViewableContact.getDateNaissance().matches("\\d{2}/\\d{2}/\\d{4}")) {
        // errors.append("Date of birth should be in the format dd/MM/yyyy.\n");
        // }

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

        ViewableContact ViewableContact = contactsTable.getSelectionModel().getSelectedItem();
        if (ViewableContact != null) {
            observableContactList.remove(ViewableContact);
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
    private void showContactDetails(Contact newContact) {
        if (viewableContact != null) {
            nomField.setText(newContact.getLastName());
            prenomField.setText(newContact.getFirstName());
            telephonePersonnelField.setText(newContact.getPersoPhone());
            emailField.setText(newContact.getEmail());
            adresseField.setText(newContact.getAddress());
            codePostalField.setText(newContact.getZipCode());
            setselectGenre(newContact);

            // formater la LocalDate en String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String formattedBirthDate = newContact.getBirthDate().format(formatter);

            dateNaissanceField.setValue(newContact.getBirthDate());
            telephoneProfessionnelField.setText(newContact.getProPhone());
            pseudoField.setText(newContact.getPseudo());
            lienDepotGitField.setText(newContact.getGitLink());

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
    private void setselectGenre(Contact contact) {
        if (contact.getGender() == Contact.Gender.MALE) {
            hommeRadio.setSelected(true);
        } else if (contact.getGender() == Contact.Gender.FEMALE) {
            femmeRadio.setSelected(true);
        } else if (contact.getGender() == Contact.Gender.NON_BINARY) {
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
        showAlert("Pas mis en œuvre", "Fonctionnalité non implémentée",
                "L'exportation vCard n'est pas encore implémentée.");
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

    // private void populateFields(ViewableContact ViewableContact) {
    // nomField.setText(ViewableContact.getNom());
    // prenomField.setText(ViewableContact.getPrenom());
    // telephonePersonnelField.setText(ViewableContact.getTelephonePersonnel());
    // emailField.setText(ViewableContact.getEmail());
    // adresseField.setText(ViewableContact.getAdresse());
    // codePostalField.setText(ViewableContact.getCodePostal());
    // dateNaissanceField.setText(ViewableContact.getDateNaissance());
    // telephoneProfessionnelField.setText(ViewableContact.getTelephoneProfessionnel());
    // pseudoField.setText(ViewableContact.getPseudo());
    // lienDepotGitField.setText(ViewableContact.getLienDepotGit());
    // }

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
     * 2. Gestion des Exceptions lors de la Chargement des Données
     * De même, gérez les exceptions lors du chargement des données depuis un
     * fichier :
     */

    /*
     * @FXML
     * private void handleLoadContact() {
     * FileChooser fileChooser = new FileChooser();
     * File file = fileChooser.showOpenDialog(null);
     * if (file != null) {
     * try {
     * // Désérialisation des contacts
     * ContactJsonDeserializer deserializer = new ContactJsonDeserializer();
     * List<Contact> contacts = deserializer.loadList(file.getAbsolutePath());
     * contactList.setAll(contacts);
     * } catch (IOException e) {
     * showAlert("Error", "Could not load data", "Could not load data from file:\n"
     * + file.getAbsolutePath() + "\n" + e.getMessage());
     * }
     * }
     * }
     */
}
