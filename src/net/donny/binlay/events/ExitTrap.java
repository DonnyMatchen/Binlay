package net.donny.binlay.events;

import net.donny.binlay.rooms.Direction;

public class ExitTrap extends Trap {
    private Direction exit;

    /**
     * default constructor
     * @param exit exit the trap is attached to
     * @param type the type of trap
     */
    public ExitTrap(Direction exit, int type){
        super();
        this.exit = exit;
        this.type = type;
    }

    /**
     * getter
     * @return exit the trap is attached to
     */
    public Direction getExit(){
        return exit;
    }

    /**
     * prints out information about the trap
     */
    @Override
    public void print() {
        System.out.println("The " + exit + " door has a " + getType() + " trap on it.");
    }
}
