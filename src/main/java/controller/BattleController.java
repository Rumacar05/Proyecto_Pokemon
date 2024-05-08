package controller;

import config.Configuration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import model.Pokemon;
import repository.CRUDRepository;
import service.AlertService;
import service.BattleService;
import service.PokemonService;

import java.util.List;

public class BattleController {
    @FXML
    private Button btnStartBattle;
    @FXML
    private ChoiceBox<String> chbPokemon1;
    @FXML
    private ChoiceBox<String> chbPokemon2;
    @FXML
    private TextArea txtBattle;

    private CRUDRepository<Pokemon> pokemonRepository;
    private PokemonService pokemonService;
    private ObservableList<String> pokemonsName;

    @FXML
    void initialize() {
        pokemonRepository = Configuration.POKEMON_REPOSITORY;

        try {
            pokemonService = new PokemonService(pokemonRepository.getAll());
            pokemonsName = FXCollections.observableArrayList(pokemonService.getPokemonsName());
            chbPokemon1.setItems(pokemonsName);
            chbPokemon2.setItems(pokemonsName);
        } catch (Exception ex) {
            AlertService.showAlert(Alert.AlertType.ERROR, "Se ha producido un error cargando los pokémons", ex.getMessage());
        }

        chbPokemon1.getSelectionModel().selectedItemProperty().addListener(selectPokemonName());
        chbPokemon2.getSelectionModel().selectedItemProperty().addListener(selectPokemonName());
    }

    private ChangeListener<String> selectPokemonName() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (oldValue != null) pokemonsName.add(oldValue);
                pokemonsName.remove(newValue);
            }
        };
    }

    @FXML
    void btnStartBattle_clicked(MouseEvent event) {
        if (chbPokemon1.getValue() != null && chbPokemon2.getValue() != null) {
            Pokemon pokemon1 = pokemonService.getPokemonByName(chbPokemon1.getValue()).clone();
            Pokemon pokemon2 = pokemonService.getPokemonByName(chbPokemon2.getValue()).clone();

            StringBuilder sb = new StringBuilder("¡INICIO DE LA BATALLA!\n\n");

            while (pokemon1.getLife() > 0 && pokemon2.getLife() > 0) {
                sb.append(BattleService.attack(pokemon1, pokemon2)).append(System.lineSeparator());

                if (pokemon2.getLife() > 0) {
                    sb.append(BattleService.attack(pokemon2, pokemon1)).append(System.lineSeparator());
                }
            }

            if (pokemon1.getLife() == 0) {
                sb.append(String.format("¡%s gana la batalla!", pokemon1.getName()));
            } else {
                sb.append(String.format("¡%s gana la batalla!", pokemon2.getName()));
            }

            txtBattle.setText(sb.toString());
        } else {
            AlertService.showAlert(Alert.AlertType.INFORMATION, "La batalla no se puede realizar", "No hay dos pokemons seleccionados");
        }
    }
}
