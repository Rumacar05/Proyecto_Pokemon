package controller;

import config.Configuration;
import enums.PokemonType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Pokemon;
import repository.CRUDRepository;
import service.AlertService;
import service.PokemonValidateService;

import java.util.Arrays;
import java.util.Comparator;

public class AddPokemonController {
    @FXML
    private Button btnAddEdit;
    @FXML
    private ChoiceBox<PokemonType> chbType;
    @FXML
    private TextField txtBaseDefense;
    @FXML
    private TextField txtBaseLife;
    @FXML
    private TextField txtBaseStrength;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtLevel;
    @FXML
    private TextField txtName;

    private Pokemon pokemonCreated;
    private CRUDRepository<Pokemon> pokemonRepository;

    public Pokemon getPokemonCreated() {
        return pokemonCreated;
    }

    @FXML
    void initialize() {
        pokemonRepository = Configuration.POKEMON_REPOSITORY;
        chbType.setItems(FXCollections.observableArrayList(Arrays.stream(PokemonType.values())
                                                        .sorted(Comparator.comparing(Enum::name))
                                                        .toList()));
    }

    @FXML
    void btnAddUpdate_clicked(MouseEvent event) {
        try {
            Pokemon pokemon = instancePokemonFromData();
            if (pokemon != null) savePokemon(pokemon);
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "No se ha podido agregar el pokémon", ex.getMessage());
        }
    }

    private Pokemon instancePokemonFromData() {
        Pokemon pokemon = null;

        try {
            pokemon = new Pokemon(Integer.parseInt(txtCode.getText()), txtName.getText(), chbType.getValue(),
                    Integer.parseInt(txtLevel.getText()), Integer.parseInt(txtBaseStrength.getText()),
                    Integer.parseInt(txtBaseDefense.getText()), Integer.parseInt(txtBaseLife.getText()));
        } catch (NumberFormatException ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Los datos introducidos no son correctos", "Hay valores númericos incorrectos");
        }

        return pokemon;
    }

    private void savePokemon(Pokemon pokemon) throws Exception {
        PokemonValidateService validation = new PokemonValidateService(pokemon);

        if (validation.isPokemonValid()) {
            if (pokemonRepository.add(pokemon)) {
                AlertService.showAlert(Alert.AlertType.INFORMATION, "Nuevo pokémon agregado", "Se ha agregado el pokémon "+ pokemon.getName());
                this.pokemonCreated = pokemon;
            } else {
                AlertService.showAlert(Alert.AlertType.ERROR, "No se ha podido agregar el pokémon", "Ha sido imposible agregar el pokémon "+ pokemon.getName());
            }
        } else {
            AlertService.showAlert(Alert.AlertType.ERROR, "Los datos introducidos son incorrectos", validation.toString());
        }
    }
}