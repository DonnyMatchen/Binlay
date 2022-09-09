package net.donny.binlay.landmark;

import net.donny.binlay.rooms.Direction;

public class Lock extends Landmark {
    private final Direction EXIT;
    private final String KEY;
    private boolean active;

    /**
     * default constructor
     * @param exit exit it's attached to
     */
    public Lock(Direction exit, String key){
        EXIT = exit;
        active = true;
        KEY = key;
    }

    /**
     * getter
     * @return returns exit the lock is attached to
     */
    public Direction getDirection() {
        return EXIT;
    }

    /**
     * prints to inform the player the door is locked
     */
    @Override
    public String speak(){
        return "the " + EXIT + " exit is locked.\nIt requires a " + KEY + " key to open it!";
    }

    /**
     * returns the key color of the lock
     * @return color of matching key
     */
    public String getKey(){
        return KEY;
    }

    /**
     * lock the lock
     */
    public void relock(){
        active = true;
    }

    /**
     * unlock the lock
     */
    public void unlock(){
        active = false;
    }

    /**
     * tester
     * @return
     * true: locked
     * false: unlocked
     */
    public boolean isLocked() {
        return active;
    }

    /**
     * returns a new lock for the matching door in the other room
     * @return new lock for opposite side of door
     */
    public Lock reverse(){
        return new Lock(EXIT.reverse(),getKey());
    }
}
