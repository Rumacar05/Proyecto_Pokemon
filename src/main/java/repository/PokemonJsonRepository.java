package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import config.Configuration;
import model.Pokemon;
import service.FileReaderService;
import service.FileWriterService;

import java.util.ArrayList;
import java.util.List;

public class PokemonJsonRepository implements CRUDRepository<Pokemon> {

    @Override
    public List<Pokemon> getAll() throws Exception {
        String pokemonsJson = FileReaderService.readFile(Configuration.POKEMONS_JSON_FILE_PATH);
        List<Pokemon> pokemons = getPokemonsFromJson(pokemonsJson);
        return pokemons != null ? pokemons : new ArrayList<>();
    }

    @Override
    public boolean add(Pokemon pokemon) throws Exception {
        List<Pokemon> pokemons = getAll();

        if (!pokemons.contains(pokemon)) {
            pokemons.add(pokemon);
            FileWriterService.saveFile(Configuration.POKEMONS_JSON_FILE_PATH, getJsonFromPokemons(pokemons));
        } else throw new Exception(String.format("El pokémon %s ya existe", pokemon.getName()));

        return true;
    }

    @Override
    public boolean update(Pokemon pokemon) throws Exception {
        List<Pokemon> pokemons = getAll();
        int pokemonIndex = pokemons.indexOf(pokemon);

        if (pokemonIndex != -1) {
            pokemons.set(pokemonIndex, pokemon);
            FileWriterService.saveFile(Configuration.POKEMONS_JSON_FILE_PATH, getJsonFromPokemons(pokemons));
        } else throw new Exception(String.format("El pokémon %s ya existe", pokemon.getName()));

        return true;
    }

    @Override
    public boolean delete(Pokemon pokemon) throws Exception {
        List<Pokemon> pokemons = getAll();

        pokemons.remove(pokemon);
        FileWriterService.saveFile(Configuration.POKEMONS_JSON_FILE_PATH, getJsonFromPokemons(pokemons));

        return true;
    }

    private List<Pokemon> getPokemonsFromJson(String pokemonsJson) {
        Gson gson = new Gson();

        return gson.fromJson(pokemonsJson, new TypeToken<>() {});
    }

    private String getJsonFromPokemons(List<Pokemon> pokemons) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(pokemons);
    }
}
