package service;

import config.Configuration;
import model.Pokemon;

import java.util.Random;

public class BattleService {
    public static String attack(Pokemon attacker, Pokemon defender) {
        int attack = new Random().nextInt(Configuration.MIN_ATTACK, Configuration.MAX_ATTACK + 1);
        int damage = Math.max(Configuration.MIN_DAMAGE, attacker.getStrength() * attack - defender.getDefense());
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
