package controller;

import config.Configuration;
import enums.PokemonType;
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

public class UpdatePokemonController {
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

    private Pokemon pokemon;
    private CRUDRepository<Pokemon> pokemonRepository;

    public UpdatePokemonController(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @FXML
    void initialize() {
        pokemonRepository = Configuration.POKEMON_REPOSITORY;

        txtCode.setDisable(true);
        txtCode.setText(String.valueOf(pokemon.getCode()));

        txtName.setDisable(true);
        txtName.setText(pokemon.getName());

        chbType.setDisable(true);
        chbType.getSelectionModel().select(pokemon.getType());

        txtLevel.setText(String.valueOf(pokemon.getLevel()));

        txtBaseDefense.setDisable(true);
        txtBaseDefense.setText(String.valueOf(pokemon.getBaseDefense()));

        txtBaseStrength.setDisable(true);
        txtBaseStrength.setText(String.valueOf(pokemon.getBaseStrength()));

        txtBaseLife.setDisable(true);
        txtBaseLife.setText(String.valueOf(pokemon.getBaseLife()));
    }

    @FXML
    void btnAddUpdate_clicked(MouseEvent event) {
        try {
            updatePokemonValues();
            savePokemonChanges();
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "No se ha podido modificar el pokémon", ex.getMessage());
        }
    }

    private void updatePokemonValues() {
        try {
            pokemon.setLevel(Integer.parseInt(txtLevel.getText()));
        } catch (NumberFormatException ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Los datos introducidos no son correctos", "Hay valores númericos incorrectos");
        }
    }

    private void savePokemonChanges() throws Exception {
        PokemonValidateService validation = new PokemonValidateService(pokemon);

        if (validation.isPokemonValid()) {
            if (pokemonRepository.update(pokemon)) {
                AlertService.showAlert(Alert.AlertType.INFORMATION, "Se ha actualizado el pokémon", "Se han modificado los datos del pokémon "+ pokemon.getName());
            } else {
                AlertService.showAlert(Alert.AlertType.ERROR, "No se ha podido modificar el pokémon", "Ha sido imposible modificar el pokémon "+ pokemon.getName());
            }
        } else {
            AlertService.showAlert(Alert.AlertType.ERROR, "Los datos introducidos son incorrectos", validation.toString());
        }
    }
}
