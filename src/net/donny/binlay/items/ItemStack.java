package net.donny.binlay.items;

public class ItemStack {
    private Item type;
    private int count;

    /**
     * constructor: multiple items
     * @param type the type of Item
     * @param count the number of items
     */
    public ItemStack(Item type, int count){
        this.type = type;
        if(count <= type.getMaxStackSize()){
            this.count = count;
        } else {
            this.count = type.getMaxStackSize();
        }
    }

    /**
     * constructor: single item
     * @param type the type of item
     */
    public ItemStack(Item type){
        this(type,1);
    }

    /**
     * determines if two ItemStacks are the same type
     * @param stack ItemStack to check
     * @return
     * true: they are the same type
     * false: they are not the same type
     */
    public boolean equals(ItemStack stack){
        return this.type.getName().equals(stack.type.getName());
    }

    /**
     * getter
     * @return number of items
     */
    public int getCount(){
        return count;
    }

    /**
     * increment the item count
     * @param value number of items to add
     * @return the number of items that wouldn't fit
     */
    public int addToCount(int value){
        int x = type.getMaxStackSize() - count;
        if(value <= x){
            count += value;
            return 0;
        }else{
            count += x;
            return value - x;
        }
    }

    /**
     * getter
     * @return the name of the item type
     */
    public String getTypeName(){
        return type.getName();
    }

    /**
     * setter
     * @param i new value of count
     */
    public void setCount(int i){
        if(type.getMaxStackSize() >= i){
            count = i;
        }else{
            count = type.getMaxStackSize();
        }
    }

    /**
     * if the itemstack is a key, return the value of the key
     * @return color of key
     */
    public String key(){
        if(type instanceof KeyItem){
            return ((KeyItem)type).value();
        }else{
            return null;
        }
    }

    /**
     * gets the total weight of the stack
     * @return total weight
     */
    public int getWeight(){
        return type.getWeight() * count;
    }
}
