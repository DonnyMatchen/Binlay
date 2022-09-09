package net.donny.binlay.inventory;

import net.donny.binlay.singletons.Game;
import net.donny.binlay.items.ItemStack;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<InventorySlot> REGISTRY;
    private static final int MAX_WEIGHT = 200;

    /**
     * default constructor
     */
    public Inventory(){
        this(12);
    }

    /**
     * used to define different numbers of inventory slots
     * @param numSlots number of slots
     */
    public Inventory(int numSlots) {
        REGISTRY = new ArrayList<>();
        for(int i = 0; i < numSlots; i++) {
            REGISTRY.add(new InventorySlot());
        }
    }

    /**
     * tester
     * @return
     * true: inventory is empty
     * false: inventory is not empty
     */
    private boolean isEmpty() {
        for(InventorySlot slot : REGISTRY) {
            if(!slot.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * tester
     * @return
     * true: inventory is full
     * false: inventory is not full
     */
    private boolean isFull() {
        for(InventorySlot slot : REGISTRY) {
            if(slot.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * prints out inventory contents
     */
    public void printInventory(){
        System.out.println("Your Inventory:");
        for(InventorySlot slot : REGISTRY){
            if(slot.isEmpty()){
                System.out.println("\t[EMPTY]");
            }else{
                System.out.println("\t[" + slot.getContents().getCount()
                        + " X " + slot.getContents().getTypeName() + "]");
            }
        }
    }

    /**
     * @return the number of InventorySlots in the Inventory
     */
    public int size(){
        return REGISTRY.size();
    }

    /**
     * gets a specified InventorySlot from the inventory
     * @param index selected index
     * @return the InventorySlot at that index
     */
    public InventorySlot get(int index){
        return REGISTRY.get(index);
    }

    /**
     * attempts to add an ItemStack to the inventory
     * @param stack ItemStack to be added
     * @return the number of items that could not be added
     */
    public int add(ItemStack stack) {
        int rem = stack.getCount();
        for(InventorySlot slot : REGISTRY) {
            if(slot.matches(stack) && getCarry() + stack.getWeight() < MAX_WEIGHT) {
                rem = slot.add(stack);
            }
        }
        if(rem != 0 && !isFull()) {
            for (InventorySlot slot : REGISTRY) {
                if (slot.isEmpty()) {
                    slot.place(stack);
                    if(stack.getTypeName().contains("Font")){
                        Game.win = true;
                    }
                    return 0;
                }
            }
        }
        return rem;
    }

    /**
     * returns the total weight in the inventory
     * @return number of weight units in inventory
     */
    public int getCarry(){
        int out = 0;
        for(InventorySlot slot : REGISTRY){
            if(!slot.isEmpty()){
                out += slot.getContents().getWeight();
            }
        }
        return out;
    }
}
