package config;

import model.Pokemon;
import repository.CRUDRepository;
import repository.PokemonMemoryRepository;

public class Configuration {
    public static CRUDRepository<Pokemon> POKEMON_REPOSITORY = new PokemonMemoryRepository();
}
