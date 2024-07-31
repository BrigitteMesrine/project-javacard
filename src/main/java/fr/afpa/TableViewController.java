package fr.afpa;

import fr.afpa.Model.Person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TableViewController {
   
    /*@FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("tableview");
    }*/

    @FXML
    private VBox mainVBox;
    @FXML
    private TableView<Person> personnesTable;
    @FXML
    private TableColumn<Person, String> prenomCol;
    @FXML
    private TableColumn<Person, String> nomCol;
    @FXML
    private TableColumn<Person, String> villeCol;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField villeTextField;
    @FXML
    private Button sauverButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button annulerButton;

    private ObservableList<Person> personnes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // on donne une fonction particulière à "setCellValueFactory"
        // cette fonction est appelée "lambda" ou "fonction flêchée"
        // c'est une fonction "anonyme" 
        // cette fonction doit retourner l'information que nous souhaitons afficher dans le tableau (la "TableView")
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        villeCol.setCellValueFactory(cellData -> cellData.getValue().getCity());

        // Initialisation des données (exemple)
        personnes.add(new Person("Martin", "Dupont", "Toulouse"));
        personnes.add(new Person("Durant", "Léa", "Paris"));
        personnes.add(new Person("Lloris", "Hugo", "Nice"));

        // Initialisation de la table
        personnesTable.setItems(personnes);

        // Gestion des événements (à implémenter)
        sauverButton.setOnAction(this::handleSauverAction);
        supprimerButton.setOnAction(this::handleSupprimerAction);
        annulerButton.setOnAction(this::handleAnnulerAction);
    }

    @FXML
    private void handleSauverAction(ActionEvent event) {
        // TODO Implémenter la logique de sauvegarde d'une nouvelle personne

        // 1 instancier une nouvelle personne en utilisant les informations contenues dans les "TextField"
        // Pour obtenir la valeur contenue dans un "TextField" il est possible d'utiliser la méthode ".getText()". Par exemple "prenomTextField.getText()"

        // Pour instancier une nouvelle personne tu peux utiliser l'opérateur "new"

        // 2 ajouter la nouvelle personne à la liste des personnes qui s'appelle "personnes".
        // POur ajouter à la liste tu peux utiliser la méthode ".add()" (je ne me souveins jamais car parfois c'est "push", parfois c'est "append", parfois c'est un autre mot)
        // conseil : quand t'as un doute tu peux écrire le nom de la liste et essayer de trouver la bonne méthode en utilisant "CTRL + espace"
        // "CTRL + espace" => appelle le menu contextuel d'aide de VSCode

        String prenom = prenomTextField.getText();
        String nom = nomTextField.getText();
        String ville = villeTextField.getText();

        Person personne = new Person(prenom, nom, ville);
        personnes.add(personne);

        // Vider le formulaire
        prenomTextField.setText("");
        nomTextField.setText("");
        villeTextField.setText("");
    }

    @FXML
    private void handleSupprimerAction(ActionEvent event) {
        // TODO Implémenter la logique de suppression d'une personne sélectionnée

        // 1 retrouver la personne à supprimer du tableau (sauvegarder dans une variable)
        // personnesTable.getSelectionModel().getSelectedItem()

        // 2 supprimer la personne de la liste des personnes   

        Person personne = personnesTable.getSelectionModel().getSelectedItem();
        if (personne != null) {
            personnes.remove(personne);
        }
    }
    
    @FXML
    private void handleAnnulerAction(ActionEvent event) {
        // Implémenter la logique d'annulation de la saisie
        // permet de vider les "TextField" "prénom", "nom", "ville"

        // Méthode 1 : pour vider les "TextField" : utilisation de la méthode "clear()"
        prenomTextField.clear();
        nomTextField.clear();
        villeTextField.clear();
        
        /* méthode 2 : nom d'entrée avec ".setText("");
        prenomTextField.setText("");
        nomTextField.setText("");
        villeTextField.setText("");
         */
    }
}