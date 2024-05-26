package repository;

import database.MySQLDatabase;
import enums.PokemonType;
import model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PokemonMySQLRepository implements CRUDRepository<Pokemon> {
    @Override
    public List<Pokemon> getAll() throws Exception {
        List<Pokemon> pokemons = new ArrayList<>();

        try (Connection connection = MySQLDatabase.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT code, name, type, level, baseStrength, baseDefense, baseLife FROM pokemons;")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pokemons.add(new Pokemon(rs.getInt("code"), rs.getString("name"), PokemonType.valueOf(rs.getString("type")),
                        rs.getInt("level"), rs.getInt("baseStrength"), rs.getInt("baseDefense"), rs.getInt("baseLife")));
            }
        } catch (Exception ex) {
            throw new Exception("Se ha producido un error consultando los pokémons " + ex.getMessage());
        }

        return pokemons;
    }

    @Override
    public boolean add(Pokemon pokemon) throws Exception {
        boolean added;

        try (Connection connection = MySQLDatabase.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pokemons VALUES(?, ?, ?, ?, ?, ?, ?, ?);")) {
            ps.setInt(1, pokemon.getCode());
            ps.setString(2, pokemon.getName());
            ps.setString(3, pokemon.getType().name());
            ps.setInt(4, pokemon.getLevel());
            ps.setInt(5, pokemon.getBaseStrength());
            ps.setInt(6, pokemon.getBaseDefense());
            ps.setInt(7, pokemon.getBaseLife());
            ps.setInt(8, pokemon.getLife());

            added = ps.executeUpdate() >= 1;
        }

        return added;
    }

    @Override
    public boolean update(Pokemon pokemon) throws Exception {
        boolean updated;
        String SQLQuery = "UPDATE pokemons" +
                "SET level=?" +
                "WHERE code=?";

        try (Connection connection = MySQLDatabase.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery)) {
            ps.setInt(1, pokemon.getLevel());
            ps.setInt(2, pokemon.getCode());

            updated = ps.executeUpdate() >= 1;
        }

        return updated;
    }

    @Override
    public boolean delete(Pokemon pokemon) throws Exception {
        boolean removed;

        try (Connection connection = MySQLDatabase.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM pokemons WHERE code=?")) {

            ps.setInt(1, pokemon.getCode());

            removed = ps.executeUpdate() >= 1;
        }

        return removed;
    }
}
