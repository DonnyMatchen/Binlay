package net.donny.binlay.rooms;

import net.donny.binlay.Binlay;
import net.donny.binlay.singletons.Player;
import net.donny.binlay.events.*;
import net.donny.binlay.items.ItemStack;
import net.donny.binlay.landmark.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes modified by Rodrigo A. Obando (2018)
 * @version 1.0 (February 2002)
 */

public class Room {
    private String description;
    private HashMap<Direction, Room> exits;
    private HashMap<Direction, Lock> locks;
    private ArrayList<ExitTrap> roomTraps;
    private ArrayList<Landmark> landmarks;

    /**
     * default constructor
     * @param description short description of the room
     */
    Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        locks = new HashMap<>();
        roomTraps = new ArrayList<>();
        landmarks = new ArrayList<>();
    }

    /**
     * adds an exit
     * @param direction the direction of the exit
     * @param neighbor the destination of the exit
     */
    public void addExit(String direction, Room neighbor) {
        addExit(Direction.fromString(direction),neighbor);
    }

    /**
     * adds an exit
     * @param direction the direction of the exit
     * @param neighbor the destination of the exit
     */
    private void addExit(Direction direction, Room neighbor) {
        exits.put(direction,neighbor);
    }

    /**
     * links two rooms
     * @param direction the direction of the exit
     * @param neighbor the destination of the exit
     */
    void link(Direction direction, Room neighbor) {
        addExit(direction,neighbor);
        neighbor.addExit(direction.reverse(),this);
    }

    /**
     * links two rooms
     * @param direction the direction of the exit
     * @param neighbor the destination of the exit
     */
    void link(String direction, Room neighbor) {
        link(Direction.fromString(direction),neighbor);
    }

    /**
     * add a landmark to the room
     * @param lm landmark to be added
     */
    public void addLandmark(Landmark lm){
        landmarks.add(lm);
    }

    /**
     * adds a lock to the room
     * @param lock lock to be added
     */
    void addLock(Lock lock) {
        locks.put(lock.getDirection(),lock);
        getExit(lock.getDirection()).locks.put(lock.getDirection().reverse(),lock.reverse());
        getExit(lock.getDirection()).landmarks.add(lock.reverse());
        addLandmark(lock);
    }

    /**
     * add a trap to the room
     * @param trap trap to be added
     */
    void addTrap(ExitTrap trap) {
        roomTraps.add(trap);
    }

    /**
     * prints out every landmark's speak()
     */
    private String scanRoom(){
        StringBuilder sb = new StringBuilder("you see");
        if(Landmark.getVisibleCount(landmarks) > 0) {
            sb.append(":\n");
            for (Landmark l : landmarks) {
                if (l.isVisible()) {
                    sb.append(l.speak()).append(",\n");
                }
            }
        }else{
            sb.append(" nothing.\n");
        }
        return sb.toString();
    }

    /**
     * attempts to use an exit
     * @param d direction of exit
     * @param player player singleton
     * @return
     * true: exit successful
     * false: exit failed
     */
    public boolean testExit(Direction d, Player player){
        for(Lock l : locks.values()){
            if(l.getDirection() == d && l.isLocked()){
                System.out.println(l.speak());
                Binlay.fail();
                return false;
            }
        }
        for(Trap t : roomTraps){
            if(t.isActive()) {
                if (t instanceof ExitTrap) {
                    if (((ExitTrap) t).getExit() == d) {
                        t.activate(player);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * disarm an exit trap
     * @param dir exit to disarm
     */
    public void disarm(Direction dir){
        boolean flag = true;
        for(ExitTrap t : roomTraps){
            if(t.isActive() && t.getExit().equals(dir)){
                t.disarm();
                System.out.println("Trap disarmed!");
                flag = false;
            }
        }
        if(flag){
            System.out.println("No Trap Found!");
            Binlay.fail();
        }
    }

    /**
     * returns all FreeItemStacks in the room
     * @return an ArrayList of FreeItemStacks
     */
    public ArrayList<FreeItemStack> getItems(){
        ArrayList<FreeItemStack> results = new ArrayList<>();
        for(Landmark l : landmarks){
            if(l instanceof FreeItemStack){
                results.add((FreeItemStack)l);
            }
        }
        return results;
    }

    /**
     * move items from world to inventory
     * @param items item to interact with
     */
    public int pickup(FreeItemStack items){
        if(!items.isTrapped()) {
            int rem = Binlay.player.addToInventory(items.getItems());
            if (rem == 0) {
                landmarks.remove(items);
            } else {
                items.getItems().setCount(rem);
            }
            return rem;
        } else {
            System.out.println("This item was trapped!");
            items.getTrap().activate(Binlay.player);
            return -1;
        }
    }

    /**
     * getter
     * @return description and exits + print landmarks
     */
    public String getLongDescription() {
        return new StringBuilder("You are in ").append(description)
                .append(".\n").append(scanRoom())
                .append(getExitString()).toString();
    }

    /**
     * getter
     * @return get exits as a string
     */
    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        if(exits.isEmpty()){
            returnString.append(" NONE");
        }else {
            for (Direction exitName : exits.keySet()) {
                returnString.append(" ").append(exitName);
            }
        }
        return returnString.toString();
    }

    /**
     * print out all furniture items
     */
    public void printFurniture(){
        for(Landmark l : landmarks){
            if(l instanceof Furniture){
                System.out.print(l.speak() + " ");
            }
        }
        System.out.println();
    }

    /**
     * inspects a piece of furniture and adds any hidden items to the player's inventory
     * @param name the name of the furniture piece
     * @return true for existing furniture, false for no furniture matching the name
     */
    public boolean inspect(String name){
        for(Landmark l : landmarks){
            if(l instanceof Furniture &&
                    l.speak().contains(name)){
                ItemStack stack = ((Furniture)l).getItem();
                if(stack == null) {
                    System.out.println("No item was found!");
                } else {
                    Binlay.player.addToInventory(stack);
                    System.out.println(new StringBuilder("" + stack.getCount())
                            .append(" X ").append(stack.getTypeName())
                            .append(" were picked up!"));
                }
                return true;
            }
        }
        System.out.println("Furniture doesn't exist!");
        Binlay.fail();
        return false;
    }

    /**
     * get room from an exit
     * @param direction exit direction
     * @return linked room
     */
    public Room getExit(String direction) {
        return getExit(Direction.fromString(direction));
    }

    /**
     * get room from an exit
     * @param direction exit direction
     * @return linked room
     */
    private Room getExit(Direction direction){
        return exits.get(direction);
    }

    /**
     * unlock a specified lock
     * @param lock name of the lock
     * @return
     * true: could unlock
     * false: could not unlock
     */
    public boolean unlock(String lock){
        for(Lock l : locks.values()){
            if(l.getKey().equals(lock)){
                l.unlock();
                l.hide();
                System.out.println(lock + " lock unlocked!");
                getExit(l.getDirection()).silentUnlock(lock);
                return true;
            }
        }
        System.out.println("No matching lock!");
        Binlay.fail();
        return false;
    }

    /**
     * defuses a trap attached to an FreeItemStack
     * @param itemName name of item
     */
    public void defuse(String itemName){
        for(Landmark l : landmarks){
            if(l instanceof FreeItemStack &&
                    ((FreeItemStack)l).isTrapped() &&
                    ((FreeItemStack)l).getItems().getTypeName().toLowerCase().contains(itemName)){
                ((FreeItemStack)l).defuse();
                System.out.println(new StringBuffer("A trap on a stack of ")
                        .append(((FreeItemStack)l).getItems().getCount()).append(" ")
                        .append(((FreeItemStack)l).getItems().getTypeName()).append("(s) has been defused!"));
            }
        }
    }

    /**
     * print out all traps in the room
     */
    public void printTraps(){
        boolean flag = true;
        if(hasExitTraps()) {
            for (ExitTrap t : roomTraps) {
                t.print();
            }
            flag = false;
        }
        if(hasItemTraps()) {
            for (Landmark l : landmarks) {
                if (l instanceof FreeItemStack && ((FreeItemStack) l).isTrapped()) {
                    ((FreeItemStack) l).getTrap().print();
                }
            }
            flag = false;
        }
        if(flag){
            System.out.println("There are no traps in this room.");
        }
    }

    /**
     * used to test if there are any exit traps
     * @return
     * true: there are traps
     * false: there are no traps
     */
    private boolean hasExitTraps(){
        return roomTraps.size() > 0;
    }

    /**
     * used to test if there are any item traps
     * @return
     * true: there are traps
     * false: there are no traps
     */
    private boolean hasItemTraps(){
        for(Landmark l : landmarks){
            if(l instanceof FreeItemStack && ((FreeItemStack)l).isTrapped()){
                return true;
            }
        }
        return false;
    }

    private boolean silentUnlock(String lock){
        for(Lock l : locks.values()){
            if(l.getKey().equals(lock)){
                l.unlock();
                l.hide();
                return true;
            }
        }
        return false;
    }
}