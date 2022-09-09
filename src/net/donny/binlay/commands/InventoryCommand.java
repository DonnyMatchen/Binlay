package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class InventoryCommand extends Command {

    /**
     * default constructor
     */
    InventoryCommand(){
        super("showinventory","used to print the contents of the current inventory\nshowinventory");
    }

    /**
     * print out inventory contents
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        Binlay.player.showInventory();
        return true;
    }
}
