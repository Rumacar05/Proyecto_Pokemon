package config;

import model.Pokemon;
import repository.CRUDRepository;
import repository.PokemonJsonRepository;
import repository.PokemonMemoryRepository;
import repository.PokemonMySQLRepository;
import ruben.proyecto_pokemon.PokemonApp;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {
    private static final String FILE_PROPERTIES_PATH = "/config/config.properties";
    private static Properties properties;

    public static CRUDRepository<Pokemon> POKEMON_REPOSITORY;
    public static int MIN_DAMAGE;
    public static int MIN_ATTACK;
    public static int MAX_ATTACK;
    public static int MIN_LEVEL;
    public static int MAX_LEVEL;
    public static int MIN_BASE_PARAMETER;
    public static int MAX_BASE_PARAMETER;
    public static String POKEMONS_JSON_FILE_PATH;

    public static void start() throws Exception {
        if (properties == null) {
            properties = new Properties();
            properties.load(new FileInputStream(Configuration.class.getResource(FILE_PROPERTIES_PATH).toURI().getPath()));
            setProperties();
        }
    }

    private static void setProperties() throws Exception {
        POKEMON_REPOSITORY = setRepository();
        MIN_DAMAGE = Integer.parseInt(properties.getProperty("min_damage", "1"));
        MIN_ATTACK = Integer.parseInt(properties.getProperty("min_attack", "0"));
        MAX_ATTACK = Integer.parseInt(properties.getProperty("max_attack", "3"));
        MIN_LEVEL = Integer.parseInt(properties.getProperty("min_level", "1"));
        MAX_LEVEL = Integer.parseInt(properties.getProperty("max_level", "100"));
        MIN_BASE_PARAMETER = Integer.parseInt(properties.getProperty("min_base_parameter", "1"));
        MAX_BASE_PARAMETER = Integer.parseInt(properties.getProperty("max_base_parameter", "15"));
        POKEMONS_JSON_FILE_PATH = "src/main/resources/pokemons.json";
    }

    private static CRUDRepository<Pokemon> setRepository() {
        CRUDRepository<Pokemon> repository;
        String repositoryType = properties.getProperty("repository", "memory");

        switch (repositoryType) {
            case "json" -> repository = new PokemonJsonRepository();
            case "mysql" -> repository = new PokemonMySQLRepository();

            default -> repository = new PokemonMemoryRepository();
        }

        return repository;
    }
}
