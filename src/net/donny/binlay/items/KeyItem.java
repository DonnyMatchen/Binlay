package net.donny.binlay.items;

public class KeyItem extends Item {
    private String type;

    /**
     * default constructor
     * @param type string matching a lock
     */
    public KeyItem(String type){
        maxStackSize = 1;
        name = "Key";
        this.type = type;
        weight = 5;
    }

    /**
     * get key type
     * @return key type
     */
    String value(){
        return type;
    }

    /**
     * getter
     * @return name of the item type
     */
    @Override
    public String getName(){
        return type + " " + name;
    }
}
