package ruben.proyecto_pokemon;

import config.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.AlertService;

import java.io.IOException;

public class PokemonApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(PokemonApp.class.getResource("menu-view.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setTitle("Pokédex");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Se ha producido una excepción no controlada", ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}