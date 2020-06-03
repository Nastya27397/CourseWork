package com.company.cards;

public class MonstersCard {
    private int level;
    private int treasures;
    private int power;

    public MonstersCard () {
        power = powerGeneration();
        level = levelGeneration();
        treasures = treasuresGeneration();
    }

    private int powerGeneration () {
        return ((int)(Math.random() * 20) + 1);
    }

    private int treasuresGeneration () {
        return ((int)(power / 7) + 1);
    }

    private int levelGeneration () {
        if (power > 10) { return 2; } else { return 1; }
    }

    public int getPower () {
        return power;
    }

    public int getTreasures () {
        return treasures;
    }

    public int getLevel () {
        return level;
    }
}
