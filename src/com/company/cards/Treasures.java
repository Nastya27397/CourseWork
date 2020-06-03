package com.company.cards;

public class Treasures {
    private Card card;

    public Treasures(int type, int size) {

        switch (type) {
            case 1:
                card = new EquipmentCard((int) (size / 7));
                break;
            case 2:
                card = new LevelUpCard();
                break;
            case 3:
                card = new BattleCard();
                break;
        }
    }

    public String getCardName() {
        return card.getName();
    }

    public int getCardCost() {
        return card.getCost();
    }

    public int getCardBonus() {
        return card.getBonus();
    }

    public Card getCard() {
        return card;
    }
}
