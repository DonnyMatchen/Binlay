package net.donny.binlay.commands;

import net.donny.binlay.Binlay;
import net.donny.binlay.rooms.Direction;

public class DisarmCommand extends Command {

    /**
     * default constructor
     */
    DisarmCommand(){
        super("disarm","used to render harmless a trap on an exit\ndisarm [north, south, east, west]");
    }

    /**
     * disarm an ExitTrap
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String dir;
        if(phrase != null && phrase.hasMoreTokens()){
            dir = phrase.nextToken();
        }else{
            System.out.println("Which door?");
            dir = Binlay.getInput();
        }
        Binlay.player.getCurrentRoom().disarm(Direction.fromString(dir));
        return true;
    }
}
