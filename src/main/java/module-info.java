module ruben.proyecto_pokemon {
    requires javafx.controls;
    requires javafx.fxml;


    opens ruben.proyecto_pokemon to javafx.fxml;
    exports ruben.proyecto_pokemon;
}