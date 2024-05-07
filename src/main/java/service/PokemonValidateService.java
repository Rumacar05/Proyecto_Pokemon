package service;

import model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonValidateService {
    private List<String> validationMessages;

    public PokemonValidateService(Pokemon pokemon) {
        this.validationMessages = new ArrayList<>();

        if (pokemon.getName() == null) validationMessages.add("Debe elegir un tipo para el Pokémon");
        if (pokemon.getType() == null) validationMessages.add("Debe elegir un nombre para el Pokémon");
        if (ValidateService.itIsBetween(pokemon.getLevel(), 1, 100))
            validationMessages.add(String.format("El nivel debe estar entre %d y %d", 1, 100));
        if (ValidateService.itIsBetween(pokemon.getBaseStrength(), 1, 15))
            validationMessages.add(String.format("La fuerza base debe estar entre %d y %d", 1, 15));
        if (ValidateService.itIsBetween(pokemon.getBaseDefense(), 1, 15))
            validationMessages.add(String.format("La defensa base debe estar entre %d y %d", 1, 15));
        if (ValidateService.itIsBetween(pokemon.getBaseLife(), 1, 15))
            validationMessages.add(String.format("La vida base debe estar entre %d y %d", 1, 15));
    }

    public boolean isPokemonValid() {
        return validationMessages.isEmpty();
    }

    @Override
    public String toString() {
        return String.join("\n", validationMessages);
    }
}
