package fr.afpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        /*System.out.println("----------" + " 2.3 \t2.3\tCREATION D’UN MODELE "+"-----------------" );
        Contact2 personne1 = new Contact2("Martin", "Dupont", "0635225912");
        // Afficher les détails de la personne :
        System.out.println(personne1);
        Contact2 personne2 = new Contact2("Jean", "Zannese", "0635221578");
        // Afficher les détails de la personne :
        System.out.println(personne2);*/
        // Vous pouvez ensuite accéder et modifier les attributs de la personne en utilisant les getters et setters:
       //  StringProperty utilisateur = utilisateur.getName(); // Récupère l'utilsateur

        scene = new Scene(loadFXML("formulaire_contact"), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        launch();
    }

}