package enums;

public enum PokemonType {
    INSECT, DRAGON, ELECTRIC, FAIRY, DISPUTE, FIRE, FLYING, GHOST, PLANT, GROUND,
    ICE, NORMAL, VENOM, PSYCHIC, ROCK, STEEL, WATER;

    @Override
    public String toString() {
        String type = "Undefined";
        
        switch (this) {
            case INSECT -> type = "Bicho";
            case DRAGON -> type = "Dragón";
            case ELECTRIC -> type = "Eléctrico";
            case FAIRY -> type = "Hada";
            case DISPUTE -> type = "Lucha";
            case FIRE -> type = "Fuego";
            case FLYING -> type = "Volador";
            case GHOST -> type = "Fantasma";
            case PLANT -> type = "Planta";
            case GROUND -> type = "Tierra";
            case ICE -> type = "Hielo";
            case NORMAL -> type = "Normal";
            case VENOM -> type = "Veneno";
            case PSYCHIC -> type = "Psíquico";
            case ROCK -> type = "Roca";
            case STEEL -> type = "Acero";
            case WATER -> type = "Agua";
        }

        return type;
    }
}
