package service;

import model.Pokemon;

import java.util.Random;

public class BattleService {
    public static String attack(Pokemon attacker, Pokemon defender) {
        int attack = new Random().nextInt(0, 3 + 1);
        int damage = Math.max(1, attacker.getStrength() * attack - defender.getDefense());
        defender.receiveAttack(damage);

        return String.format("%s ataca a %s con %d %s de daño y le deja con %d %s de vida",
                attacker.getName(), defender.getName(),
                damage, getLiterallyPoints(damage),
                defender.getLife(), getLiterallyPoints(defender.getLife()));
    }

    private static String getLiterallyPoints(int value) {
        return value == 1 ? "punto" : "puntos";
    }
}
