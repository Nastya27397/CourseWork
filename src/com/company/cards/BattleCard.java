package com.company.cards;

public class BattleCard implements Card {
    private int bonus;
    private int cost;
    private String name = "Battle card";

    public BattleCard () {
        bonus = bonusGeneration();
        cost = costGeneration();
    }

    private int bonusGeneration () {
        return ((int)(Math.random() * 5) + 1);
    }

    private int costGeneration () {
        return (bonus * 100);
    }

    public int doBattleCard (int playersPower) {
        return (playersPower + bonus);
    }
    @Override
    public int getBonus() {return bonus;}
    @Override
    public int getCost () {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }
}
