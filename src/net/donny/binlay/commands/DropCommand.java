package net.donny.binlay.commands;

import net.donny.binlay.Binlay;
import net.donny.binlay.items.ItemStack;
import net.donny.binlay.landmark.FreeItemStack;

public class DropCommand extends Command {
    /**
     * default constructor
     */
    DropCommand(){
        super("drop","used to remove an item from the inventory and leave it on the floor of the current room\ndrop [item name]");
    }

    /**
     * drop an item on the floor
     * @return
     * true: second word is not null
     * false: second word is null
     */
    @Override
    public boolean execute() {
        String itemName;
        if(phrase != null && phrase.hasMoreTokens()){
            itemName = phrase.nextToken();
        }else{
            System.out.println("Which Item would you like to drop?");
            Binlay.player.showInventory();
            itemName = Binlay.getInput();
        }
        ItemStack  item = Binlay.player.drop(itemName);
        if(item != null){
            Binlay.player.getCurrentRoom().addLandmark(new FreeItemStack(item));
            System.out.println(item.getCount() + " " + item.getTypeName() + "(s) dropped!");
            return true;
        }else{
            return false;
        }
    }
}
