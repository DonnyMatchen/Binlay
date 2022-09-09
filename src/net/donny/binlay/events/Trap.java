package net.donny.binlay.events;

import net.donny.binlay.Binlay;
import net.donny.binlay.singletons.Player;

public abstract class Trap {
    private boolean active;
    protected int type;

    /**
     * constants used for trap types
     */
    public static final int TYPE_STEAL = 0;
    public static final int TYPE_CLEAR = 1;
    public static final int TYPE_START = 2;
    public static final int TYPE_DUD = 3;

    /**
     * default constructor
     */
    Trap(){
        active = true;
    }

    /**
     * tester
     * @return active
     */
    public boolean isActive(){
        return active;
    }

    /**
     * returns a string depending on the type of trap
     * @return the name of the trap type
     */
    public String getType(){
        switch(type){
            case TYPE_CLEAR : return "clear";
            case TYPE_DUD : return "dud";
            case TYPE_START : return "start";
            case TYPE_STEAL : return "Steal";
            default: return "NONE";
        }
    }

    /**
     * execution of the activated trap
     * @param player player singleton
     */
    public void activate(Player player){
        switch(type){
            case 0:
                player.steal();
                break;
            case 1:
                player.clearInventory();
                break;
            case 2:
                player.reset();
                break;
            default:
                System.out.println("Bang!");
        }
        Binlay.fail();
    }

    /**
     * disarm the trap
     */
    public void disarm(){
        active = false;
    }

    /**
     * prints out a description of the trap
     */
    public abstract void print();
}
