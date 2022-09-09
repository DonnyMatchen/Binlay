package net.donny.binlay.rooms;

import net.donny.binlay.events.*;
import net.donny.binlay.items.*;
import net.donny.binlay.landmark.*;

public class RoomFactory {
    /*        MAP
     *   |-----------|
     *   |----------M|
     *   |--J---F-E-D|
     *   |K-I-H-G-A-C|
     *   |--L-----B--|
     *   |-----------|
     */

    private static Room start;

    /**
     * @return room A
     */
    public static Room start(){
        start = new Room("a comfortable room");
        start.addLandmark(new Furniture("fireplace"));
        start.addLandmark(new Furniture("armchair"));
        start.addLandmark(new Furniture("window"));
        start.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 50
        )));
        start.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 25
        )));
        start.addLandmark(new FreeItemStack(new ItemStack(
                new KeyItem("red")
        ), Trap.TYPE_DUD));
        start.link(Direction.S,outside());
        start.addLock(new Lock(Direction.S,"blue"));
        start.addTrap(new ExitTrap(Direction.S,Trap.TYPE_STEAL));
        start.link(Direction.E,livingRoom());
        return start;
    }

    /**
     * @return room B
     */
    private static Room outside(){
        Room outside = new Room("a yard surrounded by a wall");
        outside.addLandmark(new Furniture("rake"));
        outside.addLandmark(new Furniture("grave"));
        outside.addLandmark(new FreeItemStack(new ItemStack(
                new FontOfWisdom()
        )));
        return outside;
    }

    /**
     * @return room C
     */
    private static Room livingRoom(){
        Room living = new Room("a comfortable room");
        living.addLandmark(new Furniture("couch"));
        living.addLandmark(new Furniture("lamp",new ItemStack(
                new KeyItem("green")
        )));
        living.addLandmark(new Furniture("television"));
        living.link(Direction.N, foyer());
        return living;
    }

    /**
     * @return room D
     */
    private static Room foyer(){
        Room foyer = new Room("a small foyer");
        foyer.addLandmark(new Furniture("coat stand"));
        foyer.addLandmark(new Furniture("umbrella stand", new ItemStack(
                new CoinItem(),20
        )));
        foyer.addLandmark(new Furniture("boarded up door to the east"));
        foyer.link(Direction.N,vaultRoom());
        foyer.addLock(new Lock(Direction.N,"yellow"));
        foyer.link(Direction.W,diningRoom());
        return foyer;
    }

    /**
     * @return room E
     */
    private static Room diningRoom(){
        Room dining = new Room("a nice dining room");
        dining.addLandmark(new Furniture("nice dining table"));
        dining.addLandmark(new Furniture("6 nice chairs"));
        dining.addLandmark(new Furniture("dish cabinet",new ItemStack(
                new CoinItem(),50
        )));
        dining.link(Direction.W, kitchen());
        return dining;
    }

    /**
     * @return door F
     */
    private static Room kitchen(){
        Room kitchen = new Room("a kitchen");
        kitchen.addLandmark(new Furniture("boarded up windows"));
        kitchen.addLandmark(new Furniture("dusty stove", new ItemStack(
                new CoinItem(),40
        )));
        kitchen.addLandmark(new Furniture("boarded up door"));
        kitchen.link(Direction.S, den());
        return kitchen;
    }

    /**
     * @return room G
     */
    private static Room den(){
        Room den = new Room("a comfortable den");
        den.addLandmark(new Furniture("red couch"));
        den.addLandmark(new Furniture("green couch"));
        den.addLandmark(new Furniture("blue couch"));
        den.addLandmark(new Furniture("coffee table"));
        den.link(Direction.E, start);
        den.addLock(new Lock(Direction.E,"green"));
        den.link(Direction.W, stairs());
        return den;
    }

    /**
     * @return room H
     */
    private static Room stairs(){
        Room staircase = new Room("a sweeping staircase");
        staircase.addLandmark(new Furniture("broken picture on the wall"));
        staircase.link(Direction.W, hallway());
        return staircase;
    }

    /**
     * @return room I
     */
    private static Room hallway(){
        Room hallway = new Room("a vacant hallway");
        hallway.link(Direction.N, masterBedroom());
        hallway.link(Direction.W, bathroom());
        hallway.link(Direction.S, guestRoom());
        hallway.addLock(new Lock(Direction.N, "red"));
        return hallway;
    }

    /**
     * @return room J
     */
    private static Room masterBedroom(){
        Room master = new Room("an opulent but destroyed master bedroom");
        master.addLandmark(new Furniture("destroyed master bed", new ItemStack(
                new CoinItem(), 60
        )));
        master.addLandmark(new Furniture("boarded up bathroom door"));
        master.addLandmark(new Furniture("nightstand",new ItemStack(
                new KeyItem("yellow")
        )));
        master.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 10
        )));
        return master;
    }

    /**
     * @return room K
     */
    private static Room bathroom(){
        Room bath = new Room("a dusty bathroom");
        bath.addLandmark(new Furniture("sink"));
        bath.addLandmark(new Furniture("bathtub shower combo"));
        bath.addLandmark(new Furniture("toilet"));
        bath.addLandmark(new Furniture("medicine cabinet", new ItemStack(
                new CoinItem(), 100
        )));
        bath.addLandmark(new Furniture("towel hanger"));
        return bath;
    }

    /**
     * @return room L
     */
    private static Room guestRoom(){
        return new Room("a completely empty guestroom");
    }

    /**
     * @return M
     */
    private static Room vaultRoom(){
        Room vault = new Room("a vault rather than the coat closet you expected.\n" +
                "there is no light.");
        vault.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 120
        )));
        vault.addLandmark(new Furniture("stack of filthy rags", new ItemStack(
                new KeyItem("blue")
        )));
        vault.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 30
        )));
        vault.addLandmark(new FreeItemStack(new ItemStack(
                new CoinItem(), 45
        )));
        vault.addLandmark(new Furniture("large gemstone, blue with streaks of gray"));
        return vault;
    }
}
