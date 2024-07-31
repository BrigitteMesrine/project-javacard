package fr.afpa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import fr.afpa.Model.Person;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

<<<<<<< HEAD
    

    @Override
    public void start(Stage stage) throws IOException {

        System.out.println("----------" + " 2.3 \t2.3\tCREATION D’UN MODELE "+"-----------------" );
        Person personne1 = new Person("Martin", "Dupont", "Toulouse");
        // Afficher les détails de la personne :
        System.out.println(personne1);
        Person personne2 = new Person("Jean", "Zannese", "Agen");
        // Afficher les détails de la personne :
        System.out.println(personne2);
        // Vous pouvez ensuite accéder et modifier les attributs de la personne en utilisant les getters et setters:
       //  StringProperty utilisateur = utilisateur.getName(); // Récupère l'utilsateur



        scene = new Scene(loadFXML("tableview"));
        stage.setMinWidth(600);
        stage.setMinHeight(300); 
=======
    // warning suppression for
    // java:S2696 : asking for method to be static
    @SuppressWarnings({"java:S2696"})
    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("tableview"), 1024, 768);
>>>>>>> 63819de082be06295eb148a96a4be625ab7552c2
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