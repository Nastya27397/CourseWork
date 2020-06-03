package com.company;

import com.company.field.Battle;
import com.company.field.Decks;
import com.company.field.Values;
import com.company.players.AI;
import com.company.players.HumanPlayer;

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        Values botLevelUpValues = new Values();
        Values botEquipmentValues = new Values();
        Values botBattleValues = new Values();

        startConsoleGame();
/*
        for(int i=0; i<10000; i++) {
            startConsoleGameBotVSBot(botLevelUpValues, botEquipmentValues, botBattleValues);
        }
        printBotsResults(botLevelUpValues, botEquipmentValues, botBattleValues);*/
        input.close();
    }

    public static void startConsoleGame() {
        System.out.println("Enter you name: ");
        String name = input.next();
        System.out.println("Welcome, " + name);

        HumanPlayer humanPlayer = new HumanPlayer(name);

        System.out.println("\nChoose your competitor: \n1 - level up bot\n2 - equipment bot\n3 - batlle bot");
        int botIndex = input.nextInt();

        AI bot1;
        switch (botIndex) {
            case 1: {
                bot1 = new AI("level up bot");
                break;
            }
            case 2: {
                bot1 = new AI("equipment bot");
                break;
            }
            case 3: {
                bot1 = new AI("battle bot");
                break;
            }
            default: {
                return;
            }
        }

        Decks deck = new Decks();

        for (int i = 0; i < 6; i++) {
            humanPlayer.addToHand(deck.takeTreasure());
            bot1.addToHand(deck.takeTreasure());
        }

        Battle.input=input;
        Battle.startBattle(humanPlayer, bot1, deck, botIndex);
    }

    public static void startConsoleGameBotVSBot(Values botLevelUpValues, Values botEquipmentValues, Values botBattleValues) {

        Decks deck = new Decks();

        AI bot1 = new AI("level up bot");
        AI bot2 = new AI("equipment bot");
        AI bot3 = new AI("battle bot");
        for (int i = 0; i < 6; i++) {
            bot1.addToHand(deck.takeTreasure());
            bot2.addToHand(deck.takeTreasure());
            bot3.addToHand(deck.takeTreasure());
        }

        Battle.botLevelUpValues = botLevelUpValues;
        Battle.botEquipmentValues = botEquipmentValues;
        Battle.botBattleValues = botBattleValues;
        Battle.startBattleBotVSBot(bot1, bot2, bot3, deck);

    }

    public static void printBotsResults(Values botLevelUpValues, Values botEquipmentValues, Values botBattleValues) {
        double average;
        average = botLevelUpValues.steps/botLevelUpValues.wins;
        System.out.println("Bot level up: wins - " + (int)botLevelUpValues.wins + ", steps - " + (int)botLevelUpValues.steps + ", average - " + average);
        average = botEquipmentValues.steps/botEquipmentValues.wins;
        System.out.println("Bot equipment: wins - " + (int)botEquipmentValues.wins + ", steps - " + (int)botEquipmentValues.steps + ", average - " + average);
        average = botBattleValues.steps/botBattleValues.wins;
        System.out.println("Bot battle: wins - " + (int)botBattleValues.wins + ", steps - " + (int)botBattleValues.steps + ", average - " + average);
    }
}
