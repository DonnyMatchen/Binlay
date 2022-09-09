package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class DefuseCommand extends Command {

    /**
     * default constructor
     */
    DefuseCommand(){
        super("defuse","used to render harmless a trap on a stack of items" +
                "\ndefuse [item name]");
    }

    /**
     * defuses an ItemTrap
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String item;
        if(phrase != null && phrase.hasMoreTokens()){
            item = phrase.nextToken();
        }else{
            System.out.println("Which item?");
            item = Binlay.getInput();
        }
        Binlay.player.getCurrentRoom().defuse(item);
        return true;
    }
}
