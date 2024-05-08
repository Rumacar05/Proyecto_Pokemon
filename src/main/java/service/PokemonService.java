package service;

import model.Pokemon;

import java.util.List;

public class PokemonService {
    private List<Pokemon> pokemons;

    public PokemonService(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public int getMaxCode() {
        return pokemons.stream().mapToInt(Pokemon::getCode).max().orElse(0);
    }

    public List<String> getPokemonsName() {
        return pokemons.stream().map(Pokemon::getName).toList();
    }

    public Pokemon getPokemonByName(String name) {
        return pokemons.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
    }
}
