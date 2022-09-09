package net.donny.binlay.events;

import net.donny.binlay.landmark.FreeItemStack;

public class ItemTrap extends Trap {
    private FreeItemStack host;

    /**
     * default constructor
     * @param stack host for the trap
     * @param type type of the trap
     */
    public ItemTrap(FreeItemStack stack, int type){
        super();
        host = stack;
        this.type = type;
        host.setTrap(this);
    }

    /**
     * prints out the trap
     */
    @Override
    public void print() {
        System.out.println("the " + host.getItems().getTypeName() + " (" + host.getItems().getCount() + ") has a "
                + getType() + " trap on it.");
    }
}
