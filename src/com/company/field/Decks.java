package com.company.field;

import com.company.cards.Doors;
import com.company.cards.Treasures;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Decks {
    private LinkedList<Treasures> treasures = new LinkedList<Treasures>();
    private LinkedList<Doors> doors = new LinkedList<Doors>();
    private LinkedList<Treasures> dischangeTreasures = new LinkedList<Treasures>();
    private LinkedList<Doors> dischangeDoors = new LinkedList<Doors>();

    public Decks () {
        creatingTreasures();
        creatingDoors();
        Collections.shuffle(treasures);
        Collections.shuffle(doors);
    }

    public Treasures takeTreasure () {
        if(treasures.size() == 0) {
            resizeTreasures();
        }
        return treasures.pop();
    }

    public Doors takeDoor () {
        if(doors.size() == 0) {
            resizeDoors();
        }
        return doors.pop();
    }

    private void creatingTreasures () {

        for (int i=0; i < 25; i++) {
            treasures.add(new Treasures(1, treasures.size()));
        }
        for (int i=0; i < 5; i++) {
            treasures.add(new Treasures(2, treasures.size()));
        }
        for (int i=0; i < 20; i++) {
            treasures.add(new Treasures(3, treasures.size()));
        }
    }

    private void creatingDoors () {
        for (int i=0; i<50; i++) {
            doors.add(new Doors());
        }
    }

    public void resizeTreasures () {
        System.out.println("Resizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Collections.shuffle(dischangeTreasures);
        treasures = new LinkedList<Treasures>(dischangeTreasures);
        dischangeTreasures.clear();
    }

    public void resizeDoors () {
        System.out.println("Resizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        Collections.shuffle(dischangeDoors);
        doors = new LinkedList<Doors>(dischangeDoors);
        dischangeDoors.clear();
    }

    public void usedTreasure (Treasures t) {
        dischangeTreasures.add(t);
        /*//treasures.remove(t);
        if(treasures.isEmpty() ) {
            resizeTreasures();
        }*/
    }

    public void usedDoor (Doors t) {
        dischangeDoors.add(t);
        /*//doors.remove(t);
        if(doors.isEmpty() ) {
            resizeDoors();
        }*/
    }
/*тест
    @Override
    public String toString() {
        return "treasure`s size: " + doors.size() + " dischange treasures`s size: " + dischangeDoors.size();
    }
    */
}
