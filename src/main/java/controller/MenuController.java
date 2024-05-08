package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ruben.proyecto_pokemon.PokemonApp;
import service.AlertService;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btnBattle;
    @FXML
    private Button btnManagePokemons;

    @FXML
    void btnBattle_clicked(MouseEvent event) {
        showStage("battle-view.fxml", "Batalla Pokémon");
    }

    @FXML
    void btnManagePokemons_clicked(MouseEvent event) {
        showStage("manage-pokemons-controller.fxml", "Gestión de pokémons");
    }

    private void showStage(String viewFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(PokemonApp.class.getResource(viewFileName));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Se ha producido un error cargando la ventana", ex.getMessage());
        }
    }
}