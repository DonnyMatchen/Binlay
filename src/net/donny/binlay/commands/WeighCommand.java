package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class WeighCommand extends Command {
    /**
     * default constructor
     */
    WeighCommand(){
        super("weigh","used to get total carry weight\nweigh");
    }

    /**
     * print out player carry weight
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        Binlay.player.printWeight();
        return true;
    }
}
