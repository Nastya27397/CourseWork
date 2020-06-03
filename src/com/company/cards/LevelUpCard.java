package com.company.cards;

public class LevelUpCard implements Card {
    private final int LEVEL = 1;
    private String name = "LevelUp card";
    private final int COST = 1000;

    public int doLevelUp() {
        return LEVEL;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getCost() {
        return COST;
    }
    @Override
    public int getBonus() {return 0;}
}
