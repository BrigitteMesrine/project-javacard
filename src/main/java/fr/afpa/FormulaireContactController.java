package fr.afpa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FormulaireContactController {
    
    @FXML
    private TableView<?> contactsTable;
    @FXML
    private TableColumn<?, ?> nomColumn;
    @FXML
    private TableColumn<?, ?> prenomColumn;
    @FXML
    private TableColumn<?, ?> telColumn;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telPersoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField codePostalField;
    @FXML
    private RadioButton genreM;
    @FXML
    private RadioButton genreF;
    @FXML
    private RadioButton genreAutre;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private TextField telProField;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField lienGitField;
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
    private Button sauvegarderButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button quitterButton;

    // Méthodes d'initialisation et gestion des événements
    @FXML
    public void initialize() {
        // Initialisation des colonnes de la table
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));

        // Ajouter les listeners et les handlers ici
    }

    @FXML
    private void handleSelectAll() {
        // Logique pour sélectionner tous les contacts
    }

    @FXML
    private void handleClear() {
        // Logique pour effacer les champs de saisie
    }

    @FXML
    private void handleVCard() {
        // Logique pour exporter en vCard
    }

    @FXML
    private void handleJSON() {
        // Logique pour exporter en JSON
    }

    @FXML
    private void handleNouveau() {
        // Logique pour ajouter un nouveau contact
    }

    @FXML
    private void handleModifier() {
        // Logique pour modifier un contact existant
    }

    @FXML
    private void handleSauvegarder() {
        // Logique pour sauvegarder les modifications
    }

    @FXML
    private void handleSupprimer() {
        // Logique pour supprimer un contact
    }

    @FXML
    private void handleQuitter() {
        // Logique pour quitter l'application
    }


}