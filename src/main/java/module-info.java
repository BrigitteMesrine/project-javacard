module fr.afpa {
    requires javafx.controls;
    requires transitive javafx.fxml;

    opens fr.afpa to javafx.fxml;
    exports fr.afpa;
}
