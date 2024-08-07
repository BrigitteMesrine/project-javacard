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
    private static Stage primaryStage;

    @SuppressWarnings({ "java:S2696" })
    @Override
    public void start(Stage stage) throws IOException {
           
        scene = new Scene(loadFXML("formulaire_contact"), 1024, 768);
        
         // Ajout du fichier CSS à la scène
        scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
       
        stage.setScene(scene);
        stage.show();
        App.primaryStage = stage;

    }

    public static void popToast(String toastMessage) {
        String toastMsg = toastMessage;
        int toastMsgTime = 3500; // 3.5 seconds
        int fadeInTime = 500; // 0.5 seconds
        int fadeOutTime = 500; // 0.5 seconds
        Toast.makeText(primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
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