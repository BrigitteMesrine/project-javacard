module fr.afpa {

    requires json.simple;
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires java.base;
    requires javafx.base;
    requires org.apache.log4j;

    opens fr.afpa to javafx.fxml;
    exports fr.afpa;
    exports fr.afpa.models;

}
