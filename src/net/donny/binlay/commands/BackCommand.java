package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class BackCommand extends Command {

    /**
     * default constructor
     */
    BackCommand(){
        super("back","used to go to previous room");
    }

    /**
     *
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute(){
        Binlay.player.regress();
        System.out.println(Binlay.player.getCurrentRoom().getLongDescription());
        return true;
    }
}
