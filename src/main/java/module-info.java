module ruben.proyecto_pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires mysql.connector.j;


    opens ruben.proyecto_pokemon to javafx.fxml;
    exports ruben.proyecto_pokemon;
    opens controller to javafx.fxml;
    exports controller;
    exports model;
    opens model to javafx.fxml, com.google.gson;
    exports enums;
}