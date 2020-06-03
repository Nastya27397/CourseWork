package com.company.players;

import com.company.cards.EquipmentCard;
import com.company.cards.Treasures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public interface Player {
    void countPower();

    int getPower();

    int getLevel();

    Boolean addLevel(int level);

    void setIsInBattle(Boolean value);

    void addToHand(Treasures card);

    LinkedList<Treasures> getHand();

    Treasures putOnEquipment(Treasures card);

    void removeEquipmentByName(String name);

    Treasures getEquipmentByName(String name);

    Treasures getCardFromHandByID(int id);

    boolean cardIsInEquipment(EquipmentCard card);

    int sellCardFromHandByID(int id);

    void removeCardFromHand(int index);

    void removeCardFromHand(Treasures card);

}
