package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class InspectCommand extends Command {
    /**
     * default constructor
     */
    InspectCommand(){
        super("inspect","used to inspect a piece of furniture for items\n" +
                "inspect [furniture type]");
    }

    /**
     * inspect a specified piece of furniture
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String choose;
        if(phrase != null && phrase.hasMoreTokens()){
            choose = phrase.nextToken();
        }else{
            System.out.println("Which object?");
            Binlay.player.getCurrentRoom().printFurniture();
            choose = Binlay.getInput();
        }
        return Binlay.player.getCurrentRoom().inspect(choose);
    }
}
