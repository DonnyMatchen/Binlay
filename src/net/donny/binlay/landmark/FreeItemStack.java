package net.donny.binlay.landmark;

import net.donny.binlay.events.ItemTrap;
import net.donny.binlay.items.ItemStack;

public class FreeItemStack extends Landmark {
    private ItemStack items;
    private ItemTrap trap;

    /**
     * default constructor
     * @param items ItemStack piled on the floor
     */
    public FreeItemStack(ItemStack items){
        this.items = items;
        trap = null;
    }

    /**
     * create an FreeItemStack with a trap
     * @param items ItemStack piled on the floor
     * @param trapType trap type constant from the Trap class
     */
    public FreeItemStack(ItemStack items, int trapType){
        this(items);
        this.trap = new ItemTrap(this,trapType);
    }

    /**
     * getter
     * @return ItemStack in the landmark
     */
    public ItemStack getItems(){
        return items;
    }

    /**
     * add a trap to the ItemStack
     * @param trap trap to be added
     */
    public void setTrap(ItemTrap trap){
        this.trap = trap;
    }

    /**
     * tester
     * @return true if there is a trap, false if not
     */
    public boolean isTrapped(){
        if(trap != null){
            return trap.isActive();
        }else{
            return false;
        }
    }

    /**
     * getter
     * @return trap on FreeItemStack
     */
    public ItemTrap getTrap(){
        return trap;
    }

    /**
     * disarms the trap
     */
    public void defuse(){
        trap.disarm();
    }

    /**
     * prints a brief description
     */
    @Override
    public String speak() {
        return new StringBuilder(items.getCount() + "")
                .append(" ").append(items.getTypeName())
                .append("(s)").toString();
    }
}
