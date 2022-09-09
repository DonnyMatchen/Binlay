package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class LookCommand extends Command {
    /**
     * default constructor
     */
    LookCommand(){
        super("look","used to describe the current room\nlook");
    }

    /**
     * give details of the room
     * @return
     * true: second word is not null
     * false: second word is null
     */
    @Override
    public boolean execute() {
        System.out.println(Binlay.player.getCurrentRoom().getLongDescription());
        return true;
    }
}
