package repository;

import enums.PokemonType;
import model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonMemoryRepository implements CRUDRepository<Pokemon> {
    private List<Pokemon> pokemonList;

    public PokemonMemoryRepository() {
        this.pokemonList = generateDefaultPokemonList();
    }

    @Override
    public List<Pokemon> getAll() {
        return pokemonList;
    }

    @Override
    public boolean add(Pokemon pokemon) throws Exception {
        if (pokemonList.contains(pokemon)) {
            pokemonList.add(pokemon);
        } else throw new Exception(String.format("El pokémon %s ya existe", pokemon.getName()));

        return true;
    }

    @Override
    public boolean update(Pokemon pokemon) throws Exception {
        int pokemonIndex = pokemonList.indexOf(pokemon);

        if (pokemonIndex != -1) {
            pokemonList.set(pokemonIndex, pokemon);
        } else throw new Exception(String.format("El pokémon %s no existe", pokemon.getName()));

        return true;
    }

    @Override
    public boolean delete(Pokemon pokemon) {
        return pokemonList.remove(pokemon);
    }

    private List<Pokemon> generateDefaultPokemonList() {
        List<Pokemon> pokemonList = new ArrayList<>();

        pokemonList.add(new Pokemon(1, "Pikachu", PokemonType.ELECTRICO, 2, 5, 5, 15));
        pokemonList.add(new Pokemon(2, "Chikorita", PokemonType.PLANTA, 1, 3, 7, 15));
        pokemonList.add(new Pokemon(3, "Totodile", PokemonType.AGUA, 1, 2, 4, 15));
        pokemonList.add(new Pokemon(4, "Charmandar", PokemonType.FUEGO, 2, 5, 5, 15));

        return pokemonList;
    }
}
