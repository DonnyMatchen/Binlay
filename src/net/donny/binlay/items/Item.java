package net.donny.binlay.items;

public abstract class Item {
    protected String name;
    int maxStackSize;
    int weight;

    /**
     * getter
     * @return name of the item type
     */
    public String getName(){
        return name;
    }

    /**
     * getter
     * @return maximum stack size for item type
     */
    int getMaxStackSize(){
        return maxStackSize;
    }

    int getWeight(){
        return weight;
    }
}
