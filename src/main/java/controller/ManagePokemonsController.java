package controller;

import config.Configuration;
import enums.PokemonType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pokemon;
import repository.CRUDRepository;
import ruben.proyecto_pokemon.PokemonApp;
import service.AlertService;

import java.io.IOException;

public class ManagePokemonsController {
    private final static String ADD_UPDATE_POKEMON_FILE = "add-update-pokemon-view.fxml";

    @FXML
    private Button btnAddNewPokemon;
    @FXML
    private TableColumn<Pokemon, Integer> colCode;
    @FXML
    private TableColumn<Pokemon, Integer> colDefense;
    @FXML
    private TableColumn<Pokemon, Integer> colLevel;
    @FXML
    private TableColumn<Pokemon, Integer> colLife;
    @FXML
    private TableColumn<Pokemon, String> colName;
    @FXML
    private TableColumn<Pokemon, Integer> colStrength;
    @FXML
    private TableColumn<Pokemon, PokemonType> colType;
    @FXML
    private TableView<Pokemon> tblPokemons;

    private CRUDRepository<Pokemon> pokemonRepository;

    @FXML
    void initialize() {
        pokemonRepository = Configuration.POKEMON_REPOSITORY;
        mapColumns();
        try {
            loadData();
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Se ha producido un error cargando los pokémons", ex.getMessage());
        }
    }

    private void mapColumns() {
        // Map pokemon attributes
        this.colCode.setCellValueFactory(new PropertyValueFactory("code"));
        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.colType.setCellValueFactory(new PropertyValueFactory("type"));
        this.colLevel.setCellValueFactory(new PropertyValueFactory("level"));

        // Map pokemon methods
        this.colStrength.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStrength()).asObject());
        this.colDefense.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDefense()).asObject());
        this.colLife.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLife()).asObject());
    }

    private void loadData() throws Exception {
        tblPokemons.setItems(FXCollections.observableArrayList(pokemonRepository.getAll()));
    }

    @FXML
    void btnAddNewPokemon_clicked() throws Exception {
        AddPokemonController controller = new AddPokemonController();

        showStage(ADD_UPDATE_POKEMON_FILE, controller, "Nuevo Pokémon");

        if (controller.getPokemonCreated() != null) {
            tblPokemons.getItems().add(controller.getPokemonCreated());
        }
    }

    @FXML
    void tblPokemons_clicked(MouseEvent event) {
        if (thereArePokemonSelected(event)) {
            Pokemon pokemon = tblPokemons.getSelectionModel().getSelectedItem();
            showStage(ADD_UPDATE_POKEMON_FILE, new UpdatePokemonController(pokemon), "Modificar Pokémon");

            tblPokemons.refresh();
        }
    }

    @FXML
    void tblPokemons_drag(MouseEvent event) {
        try {
            if (thereArePokemonSelected(event)) {
                Pokemon pokemon = tblPokemons.getSelectionModel().getSelectedItem();
                ButtonType response = AlertService.showAlert(Alert.AlertType.CONFIRMATION, "Eliminar pokémon",
                        String.format("¿Quieres borrar el pokémon %s?", pokemon.getName()));

                if (response == ButtonType.OK) {
                    pokemonRepository.delete(pokemon);
                    tblPokemons.getItems().remove(pokemon);
                    AlertService.showAlert(Alert.AlertType.INFORMATION, "Pokémon eliminado", "Se ha eliminado el pokémon " + pokemon.getName());
                }

                tblPokemons.refresh();
            }
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Se ha producido un error eliminando el pokémon", ex.getMessage());
        }
    }

    private boolean thereArePokemonSelected(MouseEvent event) {
        return !(event.getTarget() instanceof TableColumnHeader) && tblPokemons.getSelectionModel().getSelectedItem() != null;
    }

    private void showStage(String viewFileName, Object controller, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(PokemonApp.class.getResource(viewFileName));
            loader.setController(controller);

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
            ex.printStackTrace();
        }
    }
}
