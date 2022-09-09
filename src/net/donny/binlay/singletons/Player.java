package net.donny.binlay.singletons;

import net.donny.binlay.Binlay;
import net.donny.binlay.inventory.Inventory;
import net.donny.binlay.items.ItemStack;
import net.donny.binlay.rooms.Direction;
import net.donny.binlay.rooms.Room;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents players in the game. Each player has 
 * a current location.
 * 
 * @author Michael Kolling modified by Rodrigo A. Obando (2018)
 * @version 1.0 (December 2002)
 */
public class Player {
    private Room currentRoom;
    private final Room START;
    private Inventory inventory;
    private static boolean exists;
    private Stack<Room> history;

    /**
     * default constructor
     * @param startingRoom starting room for the map
     */
    Player(Room startingRoom) throws SingletonException {
        if(exists){
            throw new SingletonException();
        } else {
            currentRoom = startingRoom;
            START = startingRoom;
            inventory = new Inventory();
            exists = true;
            history = new Stack<>();
            history.push(startingRoom);
        }
    }

    /**
     * getter
     * @return current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * setter
     * @param room new current room
     */
    private void setCurrentRoom(Room room) {
        history.push(room);
        currentRoom = room;
    }

    public void regress(){
        Room room;
        try {
            room = history.pop();
        }catch(EmptyStackException err){
            room = START;
        }
        currentRoom = room;
    }

    /**
     * prints out inventory contents
     */
    public void showInventory(){
        inventory.printInventory();
    }

    /**
     * steals one item at random
     */
    public void steal(){
        Random rand = new Random();
        ArrayList<Integer> slots = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++) {
            if (!inventory.get(i).isEmpty()) {
                slots.add(i);
            }
        }
        if(slots.isEmpty()){
            System.out.println("A trap has tried to steal an item from your inventory!" +
                    "  however you have no items, so nothing happened");
        }else {
            inventory.get(rand.nextInt(slots.size())).drop();
            System.out.println("A trap has stolen an item from your inventory!");
        }
    }

    /**
     * empties inventory
     */
    public void clearInventory(){
        for(int i = 0; i < inventory.size(); i++){
            inventory.get(i).drop();
        }
        System.out.println("A trap has stolen your whole inventory!");
    }

    /**
     * sends the player to the starting room
     */
    public void reset(){
        System.out.println("A trap has sent you back to the beginning!");
        setCurrentRoom(START);
    }

    /**
     * prints the total weight in the player's inventory
     */
    public void printWeight(){
        System.out.println("you are carrying " + inventory.getCarry() + " units of weight.");
    }

    /**
     * moves from one room to another, barring locks or traps
     * @param direction direction of new room
     */
    public void walk(String direction) {
        if(currentRoom.testExit(Direction.fromString(direction),this)) {
            Room nextRoom = currentRoom.getExit(direction);
            if (nextRoom == null) {
                System.out.println("There is no door!");
                Binlay.fail();
            }else {
                setCurrentRoom(nextRoom);
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }

    /**
     * adds an itemstack to your inventory
     * @param items item stack to add
     * @return number of items that could not be added
     */
    public int addToInventory(ItemStack items){
        return inventory.add(items);
    }

    /**
     * drops an item from the inventory
     * @param name item name
     * @return the ItemStack removed from the inventory.
     */
    public ItemStack drop(String name){
        for(int i = 0; i < inventory.size(); i++){
            if (!inventory.get(i).isEmpty()) {
                System.out.println(inventory.get(i).getContents().getTypeName());
                if (inventory.get(i).getContents().getTypeName()
                        .toLowerCase().equals(name)) {
                    return inventory.get(i).drop();
                }
            }
        }
        return null;
    }

    /**
     * tests if the player has a specified key
     * @param key key to be checked
     * @return
     * true: has key
     * false: doesn't have key
     */
    public boolean hasKey(String key){
        for(int i = 0; i < inventory.size(); i++){
            if(!inventory.get(i).isEmpty()) {
                if (inventory.get(i).getContents().getTypeName().toLowerCase().contains("key") &&
                        inventory.get(i).getContents().key().toLowerCase().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns the number of coins in the inventory
     * @return number of coins
     */
    String getScore(){
        int count = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(!(inventory.get(i).isEmpty()) &&
                    inventory.get(i).getContents().getTypeName().contains("Coin")){
                count += inventory.get(i).getContents().getCount();
            }
        }
        return "" + count;
    }
}