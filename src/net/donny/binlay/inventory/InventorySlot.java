package net.donny.binlay.inventory;

import net.donny.binlay.items.ItemStack;

public class InventorySlot {
    private ItemStack contents;
    private boolean empty;

    /**
     * constructor: filled slot
     * @param items the ItemStack in the slot
     */
    InventorySlot(ItemStack items){
        this.contents = items;
        empty = false;
    }

    /**
     * constructor: empty slot
     */
    InventorySlot(){
        this(null);
        empty = true;
    }

    /**
     * removes the ItemStack stored in the slot and sets it to empty
     * @return the ItemStack removed
     */
    public ItemStack drop(){
        ItemStack stack = contents;
        contents = null;
        empty = true;
        return stack;
    }

    /**
     * used to place an ItemStack in an empty slot
     * @param stack ItemStack to be added
     * @return
     * true: success
     * false: failure
     */
    boolean place(ItemStack stack){
        if(empty){
            contents = stack;
            empty = false;
            return true;
        }
        return false;
    }

    /**
     * checks if a slot is of the same Item type as the contents of the slot
     * @param check the ItemStack to be tested
     * @return
     * true: they match
     * false: they don't match or slot is empty
     */
    boolean matches(ItemStack check){
        if(empty){
            return false;
        }
        return check.equals(contents);
    }

    /**
     * getter
     * @return ItemStack contained in the slot
     */
    public ItemStack getContents(){
        return contents;
    }

    /**
     * tester
     * @return
     * true: slot is empty
     * false: slot is not empty
     */
    public boolean isEmpty(){
        return empty;
    }

    /**
     * add an ItemStack to an existing stack in a slot
     * @param stack ItemStack to be added
     * @return number of items that could not fit
     */
    int add(ItemStack stack){
        return contents.addToCount(stack.getCount());
    }
}
