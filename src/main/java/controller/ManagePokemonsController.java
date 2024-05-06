package controller;

import config.Configuration;
import enums.PokemonType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Pokemon;
import repository.CRUDRepository;
import service.AlertService;

public class ManagePokemonsController {
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
        ObservableList<Pokemon> pokemonList = FXCollections.observableList(pokemonRepository.getAll());
        tblPokemons.setItems(FXCollections.observableList(pokemonList));
    }

    @FXML
    void btnAddNewPokemon_clicked(MouseEvent event) {

    }
}
