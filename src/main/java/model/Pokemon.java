package model;

import enums.PokemonType;

import java.util.Objects;

public class Pokemon implements Cloneable {
    private int code;
    private String name;
    private PokemonType type;
    private int level;
    private int baseStrength;
    private int baseDefense;
    private int baseLife;
    private int life;

    public Pokemon(int code, String name, PokemonType type, int level, int baseStrength, int baseDefense, int baseLife) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.level = level;
        this.baseStrength = baseStrength;
        this.baseDefense = baseDefense;
        this.baseLife = baseLife;
        resetLife();
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public PokemonType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseLife() {
        return baseLife;
    }

    public int getStrength() {
        return baseStrength * level;
    }

    public int getDefense() {
        return baseDefense * level;
    }

    public int getLife() {
        return life;
    }

    public void resetLife() {
        life = baseLife * level;
    }

    public void receiveAttack(int damage) {
        this.life = Math.max(0, this.life - damage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return code == pokemon.code;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pokemon{");

        sb.append("code=").append(code);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", level=").append(level);
        sb.append(", baseStrength=").append(baseStrength);
        sb.append(", baseDefense=").append(baseDefense);
        sb.append(", baseLife=").append(baseLife);
        sb.append('}');

        return sb.toString();
    }

    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
