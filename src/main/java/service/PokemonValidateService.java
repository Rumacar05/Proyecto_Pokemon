package service;

import config.Configuration;
import model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonValidateService {
    private List<String> validationMessages;

    public PokemonValidateService(Pokemon pokemon) {
        this.validationMessages = new ArrayList<>();

        if (pokemon.getName() == null) validationMessages.add("Debe elegir un tipo para el Pokémon");
        if (pokemon.getType() == null) validationMessages.add("Debe elegir un nombre para el Pokémon");
        if (!ValidateService.itIsBetween(pokemon.getLevel(), Configuration.MIN_LEVEL, Configuration.MAX_LEVEL))
            validationMessages.add(String.format("El nivel debe estar entre %d y %d", Configuration.MIN_LEVEL, Configuration.MAX_LEVEL));
        if (!ValidateService.itIsBetween(pokemon.getBaseStrength(), Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER))
            validationMessages.add(String.format("La fuerza base debe estar entre %d y %d", Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER));
        if (!ValidateService.itIsBetween(pokemon.getBaseDefense(), Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER))
            validationMessages.add(String.format("La defensa base debe estar entre %d y %d", Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER));
        if (!ValidateService.itIsBetween(pokemon.getBaseLife(), Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER))
            validationMessages.add(String.format("La vida base debe estar entre %d y %d", Configuration.MIN_BASE_PARAMETER, Configuration.MAX_BASE_PARAMETER));
    }

    public boolean isPokemonValid() {
        return validationMessages.isEmpty();
    }

    @Override
    public String toString() {
        return String.join("\n", validationMessages);
    }
}
