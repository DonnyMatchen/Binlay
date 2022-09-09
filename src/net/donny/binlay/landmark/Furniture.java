package net.donny.binlay.landmark;

import net.donny.binlay.items.ItemStack;

public class Furniture extends Landmark {
    private String type;
    private static final String VOW = "aeiou";
    private ItemStack hiddenItems;

    /**
     * default constructor
     * @param type name of item
     */
    public Furniture(String type){
        this(type, null);
    }

    /**
     * constructor used when a furniture item should include a hidden item
     * @param type item name
     * @param items hidden item
     */
    public Furniture(String type, ItemStack items){
        this.type = type;
        hiddenItems = items;
    }

    /**
     * removes any hidden items from the furniture object
     * @return the object removed
     */
    public ItemStack getItem(){
        ItemStack items = hiddenItems;
        hiddenItems = null;
        return items;
    }

    /**
     * returns a string describing the furniture
     * @return a long description
     */
    @Override
    public String speak() {
        String article = "a ";
        if(VOW.contains("" + type.toLowerCase().charAt(0))){
            article = "an ";
        }
        return article + type;
    }
}
