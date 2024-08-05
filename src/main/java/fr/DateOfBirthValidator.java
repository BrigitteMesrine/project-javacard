package fr;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateOfBirthValidator extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Entrez votre date de naissance (dd/mm/yyyy) :");
        TextField textField = new TextField();
        Label validationLabel = new Label();

        // Expression régulière pour valider une date au format dd/mm/yyyy
        String regex = "^([0-2][0-9]|(3)[0-1])\\/((0)[0-9]|(1)[0-2])\\/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = pattern.matcher(newValue);
            if (matcher.matches()) {
                validationLabel.setText("Date valide");
                validationLabel.setStyle("-fx-text-fill: green;");
            } else {
                validationLabel.setText("Date invalide");
                validationLabel.setStyle("-fx-text-fill: red;");
            }
        });

        VBox root = new VBox(10, label, textField, validationLabel);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Validation de Date de Naissance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

