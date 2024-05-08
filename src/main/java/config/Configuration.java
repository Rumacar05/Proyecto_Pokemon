package config;

import model.Pokemon;
import repository.CRUDRepository;
import repository.PokemonMemoryRepository;

public class Configuration {
    public static CRUDRepository<Pokemon> POKEMON_REPOSITORY = new PokemonMemoryRepository();
    public static final int MIN_DAMAGE = 1;
    public static final int MIN_ATTACK = 0;
    public static final int MAX_ATTACK = 3;
    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 100;
    public static final int MIN_BASE_PARAMETER = 1;
    public static final int MAX_BASE_PARAMETER = 15;
}
