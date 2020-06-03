package com.company.field;

import com.company.cards.*;
import com.company.players.AI;
import com.company.players.HumanPlayer;
import com.company.players.Player;

import java.util.LinkedList;
import java.util.Scanner;

public class Battle {
    public static Scanner input;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Decks deck;
    private static int counter = 0;
    public static Values botLevelUpValues;
    public static Values botEquipmentValues;
    public static Values botBattleValues;

    public static void startBattle(HumanPlayer humanPlayer, AI bot, Decks d, int botIndex) {
        boolean isEnd = false;
        deck = d;
        while (!isEnd) {
            System.out.println("Step " + counter++ + "\n");
            mainMenu(humanPlayer);
            battle(humanPlayer);

            if (playerHasWon(humanPlayer)) {
                System.out.println("\n\nYou won!!!!!!!!!");
                return;
            }
            switch (botIndex) {
                case 1: {
                    if (levelBotTurn(bot)) {
                        System.out.println("\n\n" + bot.getName() + " won!!!!!");
                        return;
                    }
                    break;
                }
                case 2: {
                    if (equipmentBotTurn(bot)) {
                        System.out.println("\n\n" + bot.getName() + " won!!!!!");
                        return;
                    }
                    break;
                }
                case 3: {
                    if (battleBotTurn(bot)) {
                        System.out.println("\n\n" + bot.getName() + " won!!!!!");
                        return;
                    }
                    break;
                }
            }
        }
    }

    public static void startBattleBotVSBot(AI bot1, AI bot2, AI bot3, Decks d) {
        boolean isEnd = false;
        deck = d;
        counter = 0;
        while (!isEnd) {
            System.out.println("Step " + counter + "\n");
            if (levelBotTurn(bot1)) {
                botLevelUpValues.steps += counter;
                botLevelUpValues.wins++;
                System.out.println("\n\n" + bot1.getName() + " won!!!!!");
                return;
            }
            if (equipmentBotTurn(bot2)) {
                botEquipmentValues.steps += counter;
                botEquipmentValues.wins++;
                System.out.println("\n\n" + bot2.getName() + " won!!!!!");
                return;
            }

            if (battleBotTurn(bot3)) {
                botBattleValues.steps += counter;
                botBattleValues.wins++;
                System.out.println("\n\n" + bot3.getName() + " won!!!!!");
                return;
            }
            counter++;
        }
    }

    private static void mainMenu(HumanPlayer humanPlayer) {
        while (true) {
            System.out.println(ANSI_CYAN + "----------------MAIN MENU-------------------" + ANSI_RESET);
            System.out.println("");
            System.out.println("Your level: " + humanPlayer.getLevel());
            System.out.println("");
            if (humanPlayer.getHand().size() == 0) {
                System.out.println(ANSI_RED + "Your hand is empty" + ANSI_RESET);
            } else {
                System.out.println("Your hand:");
                for (int i = 0; i < humanPlayer.getHand().size(); i++) {
                    Card card = humanPlayer.getCardFromHandByID(i).getCard();
                    if (card.getClass().getSimpleName().equals("LevelUpCard")) {
                        System.out.println("    " + (i + 1) + ". " + card.getName() + ": price - " + card.getCost() + " coins.");
                    } else {
                        System.out.println("    " + (i + 1) + ". " + card.getName() + ": bonus - +" + card.getBonus() + ", price - " + card.getCost() + " coins.");
                    }
                }
                System.out.println("Your equipment:");
                if (humanPlayer.getEquipmentByName("head") == null) {
                    System.out.println("    Head: none");
                } else {
                    System.out.println("    Head: " + "bonus - +" + humanPlayer.getEquipmentByName("head").getCard().getBonus() + ", price - " + humanPlayer.getEquipmentByName("head").getCard().getCost());
                }
                if (humanPlayer.getEquipmentByName("body") == null) {
                    System.out.println("    Body: none");
                } else {
                    System.out.println("    Body: " + "bonus - +" + humanPlayer.getEquipmentByName("body").getCard().getBonus() + ", price - " + humanPlayer.getEquipmentByName("body").getCard().getCost());
                }
                if (humanPlayer.getEquipmentByName("legs") == null) {
                    System.out.println("    Legs: none");
                } else {
                    System.out.println("    Legs: " + "bonus - +" + humanPlayer.getEquipmentByName("legs").getCard().getBonus() + ", price - " + humanPlayer.getEquipmentByName("legs").getCard().getCost());
                }
                if (humanPlayer.getEquipmentByName("hand") == null) {
                    System.out.println("    Hand: none");
                } else {
                    System.out.println("    Hand: " + "bonus - +" + humanPlayer.getEquipmentByName("hand").getCard().getBonus() + ", price - " + humanPlayer.getEquipmentByName("hand").getCard().getCost());
                }
                System.out.println("-----------------------------------");
            }
            System.out.println("MENU (First turn - Dealing with your equipment):\n 1 - sell cards,\n 2 - put on cards,\n 3 - go to second turn (Taking a door card...)");
            int n = input.nextInt();
            switch (n) {
                case 1: {
                    if (humanPlayer.getHand().size() == 0) {
                        System.out.println(ANSI_RED + "Sorry, you have no cards to sell" + ANSI_RESET);
                        break;
                    }
                    for (int i = 0; i < 5; i++) System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    sellCards(humanPlayer);
                    for (int i = 0; i < 5; i++) System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    break;
                }
                case 2: {
                    if (humanPlayer.getHand().size() == 0) {
                        System.out.println(ANSI_RED + "Sorry, you have no cards to put on" + ANSI_RESET);
                        break;
                    }
                    for (int i = 0; i < 5; i++) System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    putOnEquipment(humanPlayer);
                    for (int i = 0; i < 5; i++) System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    break;
                }
                case 3: {
                    for (int i = 0; i < 5; i++) System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    return;
                }
                default: {
                    System.out.println(ANSI_RED + "Sorry, your answer is incorrect" + ANSI_RESET);
                    break;
                }
            }
        }
    }

    private static void battle(HumanPlayer humanPlayer) {
        humanPlayer.setIsInBattle(true);
        Doors door = deck.takeDoor();
        int currentPower = humanPlayer.getPower();
        while (true) {
            System.out.println(ANSI_BLUE + "-----------------BATTLE------------------" + ANSI_RESET);

            System.out.println("Monster: power - " + door.getCard().getPower() + ", levels - " + door.getCard().getLevel() + ", treasures - " + door.getCard().getTreasures());
            System.out.println("-----------vs-----------");
            System.out.println("Your power - " + currentPower);
            if (currentPower > door.getCard().getPower()) {
                System.out.println("You won!!!");
                humanPlayer.addLevel(door.getCard().getLevel());
                for (int i = 0; i < door.getCard().getTreasures(); i++) {
                    if (humanPlayer.getHand().size() <= 6) {
                        humanPlayer.addToHand(deck.takeTreasure());
                    }
                }
                deck.usedDoor(door);
                humanPlayer.setIsInBattle(false);
                return;
            } else {
                int count = 0;
                System.out.println("Your battle cards:");
                for (Treasures t : humanPlayer.getHand()) {
                    if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                        BattleCard card = (BattleCard) t.getCard();
                        System.out.println("    " + ++count + ". " + card.getName() + ": bonus - +" + card.getBonus() + ", price - " + card.getCost());
                    }

                }
                if (count == 0) {
                    System.out.println(ANSI_RED + "You have less power to win monster... So you are losing 2 levels." + ANSI_RESET);
                    humanPlayer.addLevel(-2);
                    deck.usedDoor(door);
                    humanPlayer.setIsInBattle(false);
                    return;
                }
                count = 0;
                System.out.println("Chose card`s index, or type '-1' to give up");
                int index = input.nextInt();
                if (index == (-1)) {
                    humanPlayer.addLevel(-2);
                    deck.usedDoor(door);
                    humanPlayer.setIsInBattle(false);
                    System.out.println(ANSI_RED + "You are losing 2 levels." + ANSI_RESET);
                    return;
                }
                index--;

                if (index <= humanPlayer.getHand().size() && index >= 0) {
                    for (Treasures t : humanPlayer.getHand()) {
                        if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                            BattleCard card = (BattleCard) t.getCard();
                            if (index == count) {
                                currentPower += card.getBonus();
                                humanPlayer.removeCardFromHand(t);
                                deck.usedTreasure(t);
                                break;
                            }
                            count++;
                        }
                    }
                } else {
                    System.out.println(ANSI_RED + "Invalid index" + ANSI_RESET);
                }
            }
        }
    }

    private static void sellCards(HumanPlayer humanPlayer) {
        int wallet = 0;
        int newsize = humanPlayer.getHand().size();
        LinkedList<Treasures> tmpContainer = new LinkedList();
        while (true) {
            System.out.println(ANSI_BLUE + "-----------------SELLING------------------" + ANSI_RESET);
            for (int i = 0; i < humanPlayer.getHand().size(); i++) {
                Card card = humanPlayer.getCardFromHandByID(i).getCard();
                if (card.getClass().getSimpleName().equals("LevelUpCard")) {
                    System.out.println("    " + (i + 1) + ". " + card.getName() + ": price - " + card.getCost() + " coins.");
                } else {
                    System.out.println("    " + (i + 1) + ". " + card.getName() + ": bonus - +" + card.getBonus() + ", price - " + card.getCost() + " coins.");
                }
            }

            if (humanPlayer.getHand().isEmpty()) {
                System.out.println(ANSI_RED + "Your hand is empty. Type '0' to exit" + ANSI_RESET);
            } else {
                System.out.println("Your wallet: " + wallet + ". Chose card`s index to sell (1 lvl = 1000 coins) or type '0' to exit");
            }
            int index = input.nextInt();
            if (index == 0) {
                for (Treasures t : tmpContainer) {
                    humanPlayer.addToHand(t);
                }
                return;
            } else if (index > humanPlayer.getHand().size() || index < 0) {
                System.out.println(ANSI_RED + "Invalid index" + ANSI_RESET);
            } else {
                Treasures card = humanPlayer.getCardFromHandByID(--index);
                tmpContainer.add(card);
                wallet += humanPlayer.sellCardFromHandByID(index);
                if (wallet >= 1000) {
                    if (!humanPlayer.addLevel(1)) {
                        System.out.println(ANSI_RED + "Sorry, you can get 10th level only in battle" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_YELLOW + "Now your level is " + humanPlayer.getLevel() + ANSI_RESET);

                        for (Treasures t : tmpContainer) {
                            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard")) {
                                EquipmentCard ecard = (EquipmentCard) t.getCard();
                                if (humanPlayer.cardIsInEquipment(ecard)) {
                                    humanPlayer.removeEquipmentByName(ecard.getName());
                                }
                            }
                            deck.usedTreasure(t);
                        }
                        tmpContainer.clear();
                        wallet -= 1000;
                    }
                }
            }
        }
    }

    private static void putOnEquipment(HumanPlayer humanPlayer) {
        System.out.println(ANSI_BLUE + "-----------------PUTTING ON------------------" + ANSI_RESET);
        int count = 0;
        for (int i = 0; i < humanPlayer.getHand().size(); i++) {
            if (humanPlayer.getCardFromHandByID(i).getCard().getClass().getSimpleName().equals("EquipmentCard")) {
                EquipmentCard card = (EquipmentCard) humanPlayer.getCardFromHandByID(i).getCard();
                if (!humanPlayer.cardIsInEquipment(card)) {
                    System.out.println("    " + ++count + ". " + card.getName() + ": bonus - +" + card.getBonus() + ", price - " + card.getCost());
                }
            }
        }
        if (count == 0) {
            System.out.println(ANSI_RED + "You have no equipment cards..." + ANSI_RESET);
            return;
        }
        count = 0;
        System.out.println("Chose card`s index or type '-1' to exit");
        int index = input.nextInt();
        if (index == (-1)) {
            return;
        }
        for (int i = 0; i < humanPlayer.getHand().size(); i++) {
            if (humanPlayer.getCardFromHandByID(i).getCard().getClass().getSimpleName().equals("EquipmentCard")) {
                EquipmentCard card = (EquipmentCard) humanPlayer.getCardFromHandByID(i).getCard();
                if (!humanPlayer.cardIsInEquipment(card)) {
                    count++;
                    if (index == count) {
                        Treasures t = humanPlayer.putOnEquipment(humanPlayer.getCardFromHandByID(i));
                        if (t != null) {
                            deck.usedTreasure(t);
                        }
                        return;
                    }
                }
            }
        }
        System.out.println(ANSI_RED + "Oops" + ANSI_RESET);
    }

    private static Boolean playerHasWon(Player player) {
        return player.getLevel() >= 10 ? true : false;
    }

    //////bot level up

    private static Boolean levelBotTurn(AI bot) {
        //используем карты повышения уровня
        if (!tryToUseLvlUpCard(bot)) {
            //пытаемся продать снаряжение
            if (!tryToSellEquipmentCards(bot)) ;
            {
                //пытаемся продать боевые карты
                tryToSellBattleCards(bot);
            }
        }
        //одеваемся
        tryToPutOnEquipment(bot);
        //начинаем бой
        botStartBattle(bot);

        //проверяем выиграл ли бот
        if (bot.getLevel() >= 10) {
            return true;
        }
        return false;
    }

    private static Boolean tryToUseLvlUpCard(AI bot) {
        for (Treasures t : bot.getHand()) {
            if (t.getCard().getClass().getSimpleName().equals("LevelUpCard")) {
                bot.useLvlUpCard(t);
                deck.usedTreasure(t);
                return true;
            }
        }
        return false;
    }

    private static Boolean tryToSellEquipmentCards(AI bot) {
        int wallet = 0;
        LinkedList<Treasures> tmpContainer = new LinkedList();
        for (Treasures t : bot.getHand()) {
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard")) {
                wallet += t.getCardCost();
                tmpContainer.add(t);
                if (wallet >= 1000) {
                    for (Treasures tmpT : tmpContainer) {
                        bot.removeCardFromHand(tmpT);
                        deck.usedTreasure(t);
                        bot.addLevel(1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Boolean tryToSellBattleCards(AI bot) {
        int wallet = 0;
        LinkedList<Treasures> tmpContainer = new LinkedList();
        for (Treasures t : bot.getHand()) {
            if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                wallet += t.getCardCost();
                tmpContainer.add(t);
                if (wallet >= 1000) {
                    for (Treasures tmpT : tmpContainer) {
                        bot.removeCardFromHand(tmpT);
                        deck.usedTreasure(t);
                        bot.addLevel(1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void tryToPutOnEquipment(AI bot) {
        for (int i = 0; i < bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard")) {
                if (bot.getEquipmentByName(t.getCardName()) == null) {
                    bot.putOnEquipment(t);
                    i = 0;
                } else {
                    if (bot.getEquipmentByName(t.getCardName()).getCardBonus() < t.getCardBonus()) {
                        Treasures tmp = bot.putOnEquipment(t);
                        deck.usedTreasure(tmp);
                        i = 0;
                    }
                }
            }
        }
    }

    private static void botStartBattle(AI bot) {
        bot.setIsInBattle(true);
        Doors door = deck.takeDoor();
        int maxbonus = 0;
        int currentPower = bot.getPower();

        int countMaxPower = currentPower;
        for (Treasures t : bot.getHand()) {
            if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                countMaxPower += t.getCardBonus();
            }
        }

        if (countMaxPower > door.getCard().getPower()) {
            while (true) {
                maxbonus = 0;
                if (currentPower > door.getCard().getPower()) {
                    bot.addLevel(door.getCard().getLevel());
                    for (int i = 0; i < door.getCard().getTreasures(); i++) {
                        if (bot.getHand().size() <= 6) {
                            bot.addToHand(deck.takeTreasure());
                        }
                    }
                    deck.usedDoor(door);
                    bot.setIsInBattle(false);
                    return;
                }
                for (Treasures t : bot.getHand()) {
                    if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                        if (t.getCardBonus() > maxbonus) {
                            maxbonus = t.getCardBonus();
                        }
                    }
                }
                for (Treasures t : bot.getHand()) {
                    if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                        if (t.getCardBonus() == maxbonus) {
                            currentPower += t.getCardBonus();
                            bot.removeCardFromHand(t);
                            deck.usedTreasure(t);
                            break;
                        }
                    }
                }
            }
        }

        bot.addLevel(-2);
        deck.usedDoor(door);
        bot.setIsInBattle(false);
    }


    //////bot equipment

    private static Boolean equipmentBotTurn(AI bot) {
        tryToPutOnEquipmentSmart(bot);
        if (!tryToSellEquipmentCards(bot)) {
            tryToUseLvlUpCard(bot);
        }
        botStartBattle(bot);
        if (bot.getLevel() >= 10) {
            return true;
        }
        return false;
    }

    private static void tryToPutOnEquipmentSmart(AI bot) {
        int maxBonus = 0;
        Treasures tmp = null;
        for (int i = 0; i < bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard") && t.getCardName().equals("hand")) {
                if (t.getCardBonus() > maxBonus) {
                    tmp = t;
                    maxBonus = t.getCardBonus();
                }
            }
        }
        if (tmp != null) {
            if (bot.getEquipmentByName(tmp.getCardName()) == null) {
                bot.putOnEquipment(tmp);
            } else if (bot.getEquipmentByName(tmp.getCardName()).getCardBonus() < tmp.getCardBonus()) {
                Treasures tmp1 = bot.putOnEquipment(tmp);
                deck.usedTreasure(tmp1);
            }
            tmp = null;
        }

        for (int i = 0; i < bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard") && t.getCardName().equals("head")) {
                if (t.getCardBonus() > maxBonus) {
                    tmp = t;
                    maxBonus = t.getCardBonus();
                }
            }
        }
        if (tmp != null) {
            if (bot.getEquipmentByName(tmp.getCardName()) == null) {
                bot.putOnEquipment(tmp);
            } else if (bot.getEquipmentByName(tmp.getCardName()).getCardBonus() < tmp.getCardBonus()) {
                Treasures tmp1 = bot.putOnEquipment(tmp);
                deck.usedTreasure(tmp1);
            }
            tmp = null;
        }

        for (int i = 0; i < bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard") && t.getCardName().equals("body")) {
                if (t.getCardBonus() > maxBonus) {
                    tmp = t;
                    maxBonus = t.getCardBonus();
                }
            }
        }
        if (tmp != null) {
            if (bot.getEquipmentByName(tmp.getCardName()) == null) {
                bot.putOnEquipment(tmp);
            } else if (bot.getEquipmentByName(tmp.getCardName()).getCardBonus() < tmp.getCardBonus()) {
                Treasures tmp1 = bot.putOnEquipment(tmp);
                deck.usedTreasure(tmp1);
            }
            tmp = null;
        }

        for (int i = 0; i < bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("EquipmentCard") && t.getCardName().equals("legs")) {
                if (t.getCardBonus() > maxBonus) {
                    tmp = t;
                    maxBonus = t.getCardBonus();
                }
            }
        }
        if (tmp != null) {
            if (bot.getEquipmentByName(tmp.getCardName()) == null) {
                bot.putOnEquipment(tmp);
            } else if (bot.getEquipmentByName(tmp.getCardName()).getCardBonus() < tmp.getCardBonus()) {
                Treasures tmp1 = bot.putOnEquipment(tmp);
                deck.usedTreasure(tmp1);
            }
            tmp = null;
        }
    }

    //////battle bot

    private static Boolean battleBotTurn(AI bot) {
        tryToUseAllLvlUpCards(bot);
        tryToPutOnEquipmentSmart(bot);
        tryToSellEquipmentCards(bot);
        BotStartBattleSmart(bot);
        if (bot.getLevel() >= 10) {
            return true;
        }
        return false;
    }

    private static void BotStartBattleSmart(AI bot) {
        LinkedList<Treasures> tmpContainer = new LinkedList();

        bot.setIsInBattle(true);
        Doors door = deck.takeDoor();
        int minbonus = 0;
        int residual = 0;
        int currentPower = bot.getPower();

        int count = currentPower;
        for (Treasures t : bot.getHand()) {
            if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                count += t.getCardBonus();
            }
        }

        if (count > door.getCard().getPower()) {

            minbonus = 7;
            int tmpPower = 0;

            while (true) {
                for (Treasures t : bot.getHand()) {
                    if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                        if (t.getCardBonus() < minbonus) {
                            minbonus = t.getCardBonus();
                        }
                    }
                }

                for (Treasures t : bot.getHand()) {
                    if (t.getCard().getClass().getSimpleName().equals("BattleCard")) {
                        if (t.getCardBonus() == minbonus) {
                            currentPower += t.getCardBonus();
                            tmpPower = currentPower;
                            tmpContainer.add(t);
                            break;
                        }
                    }
                }

                if (currentPower > door.getCard().getPower()) {
                    residual = currentPower - door.getCard().getPower() - 1;
                    if (residual != 0) {
                        for (Treasures t : tmpContainer) {
                            if (t.getCardBonus() == residual) {
                                currentPower -= t.getCardBonus();
                                tmpContainer.remove(t);
                                break;
                            }
                        }
                        if (currentPower == tmpPower && residual != 1) {
                            int counter = 0;
                            while (residual>counter) {
                            residual--;
                            counter++;
                                for (Treasures tMax : tmpContainer) {
                                    if (tMax.getCardBonus() == residual) {
                                        for (Treasures tMin : tmpContainer) {
                                            if (tMin.getCardBonus() == counter) {
                                                currentPower-=(tMax.getCardBonus()+tMin.getCardBonus());
                                                tmpContainer.remove(tMin);
                                                tmpContainer.remove(tMax);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                if(currentPower!=tmpPower) {
                                    break;
                                }
                            }
                            if(currentPower != tmpPower) {
                                Treasures tmpT = null;
                                int maxBonus = 0;
                                residual = tmpPower - door.getCard().getPower() - 1;
                                for(Treasures t: tmpContainer) {
                                    if(t.getCardBonus() < residual && t.getCardBonus() > maxBonus) {
                                        maxBonus = t.getCardBonus();
                                        tmpT = t;
                                    }
                                }
                                if(tmpT != null) {
                                    tmpContainer.remove(tmpT);
                                }
                            }
                        }
                    }
                    for(Treasures t: tmpContainer){
                        bot.removeCardFromHand(t);
                    }

                    bot.addLevel(door.getCard().getLevel());
                    for (int i = 0; i < door.getCard().getTreasures(); i++) {
                        if (bot.getHand().size() <= 6) {
                            bot.addToHand(deck.takeTreasure());
                        }
                    }
                    deck.usedDoor(door);
                    bot.setIsInBattle(false);
                    return;
                }
            }
        }

        bot.addLevel(-2);
        deck.usedDoor(door);
        bot.setIsInBattle(false);
    }

    private static void tryToUseAllLvlUpCards(AI bot) {
        for (int i=0; i<bot.getHand().size(); i++) {
            Treasures t = bot.getCardFromHandByID(i);
            if (t.getCard().getClass().getSimpleName().equals("LevelUpCard")) {
                bot.useLvlUpCard(t);
                deck.usedTreasure(t);
            }
        }
    }
}
