package com.company.players;

import com.company.cards.EquipmentCard;
import com.company.cards.Treasures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AI implements Player {
    private int playersLevel = 1;
    private int playersPower;
    private String playersName;
    private Boolean isInBattle = false;
    public LinkedList<Treasures> playersHand = new LinkedList<Treasures>();

    public Map<String, Treasures> playersEquipment = new HashMap<String, Treasures>();

    public AI(String name) {
        playersEquipment.put("hand", null);
        playersEquipment.put("head", null);
        playersEquipment.put("body", null);
        playersEquipment.put("legs", null);
        playersName = name;
        playersPower = playersLevel;
    }

    private int countEquipmentBonus() {
        int countPower = 0;
        if (playersEquipment.get("hand") != null) {
            countPower += playersEquipment.get("hand").getCard().getBonus();
        }
        if (playersEquipment.get("head") != null) {
            countPower += playersEquipment.get("head").getCard().getBonus();
        }
        if (playersEquipment.get("body") != null) {
            countPower += playersEquipment.get("body").getCard().getBonus();
        }
        if (playersEquipment.get("legs") != null) {
            countPower += playersEquipment.get("legs").getCard().getBonus();
        }
        return countPower;
    }

    public void useLvlUpCard(Treasures t) {
        addLevel(1);
        removeCardFromHand(t);
    }

    public String getName() {
        return playersName;
    }

    @Override
    public void removeEquipmentByName(String name) {
        playersEquipment.put(name, null);
    }

    @Override
    public Treasures getEquipmentByName(String name) {
        return playersEquipment.get(name);
    }

    @Override
    public Treasures putOnEquipment(Treasures card) {
        Treasures t = playersEquipment.get(card.getCardName());
        removeCardFromHand(card);
        playersEquipment.put(card.getCardName(), card);
        return t;
    }

    @Override
    public Treasures getCardFromHandByID(int id) {
        return playersHand.get(id);
    }

    @Override
    public boolean cardIsInEquipment(EquipmentCard card) {
        return playersEquipment.containsValue(card);
    }

    @Override
    public int sellCardFromHandByID(int id) {
        int cost = playersHand.get(id).getCard().getCost();
        playersHand.remove(id);
        return cost;
    }

    @Override
    public void removeCardFromHand(int index) {
        playersHand.remove(index);
    }

    @Override
    public void removeCardFromHand(Treasures card) {
        playersHand.remove(card);
    }

    @Override
    public void addToHand(Treasures card) {
        playersHand.add(card);
    }

    @Override
    public LinkedList<Treasures> getHand() {
        return playersHand;
    }


    @Override
    public Boolean addLevel(int level) {
        if ((playersLevel + level) >= 10) {
            if (isInBattle) {
                playersLevel += level;
                return true;
            } else {
                return false;
            }
        } else if ((playersLevel + level) < 1) {
            playersLevel = 1;
            return true;
        } else {
            playersLevel += level;
            return true;
        }
    }

    @Override
    public void setIsInBattle(Boolean value) {
        isInBattle = value;
    }

    @Override
    public void countPower() {
        playersPower = playersLevel + countEquipmentBonus();
    }

    @Override
    public int getPower() {
        countPower();
        return playersPower;
    }

    @Override
    public int getLevel() {
        return playersLevel;
    }
}
