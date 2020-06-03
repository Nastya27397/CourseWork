package com.company.cards;

public class EquipmentCard implements Card {
    private int cost;
    private int bonus;
    private String name;
    private Equipments kind;

    public EquipmentCard(int num) {
        bonus = bonusGeneration();
        cost = costGeneration();
        kind = Equipments.values()[num];

        switch (kind) {
            case FOR_BODY: {
                name = "body";
                break;
            }
            case FOR_HEAD: {

                name = "head";
                break;
            }
            case FOR_LEGS: {
                name = "legs";
                break;
            }
            case FOR_HANDS: {
                name = "hand";
                break;
            }
            default: break;
        }
    }

    private int bonusGeneration() {
        return ((int) (Math.random() * 5) + 1);
    }

    private int costGeneration() {
        return (bonus * 100);
    }
    @Override
    public int getBonus() {
        return bonus;
    }
    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }
/*
    private Equipments eqGeneration () {
        for (int i=0; i<10; i++) {
            return (Math.random() * Equipments.)
        }
    }*/
}

enum Equipments {FOR_HEAD, FOR_HANDS, FOR_BODY, FOR_LEGS}
