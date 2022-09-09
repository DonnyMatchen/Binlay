package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class DetectCommand extends Command {
    /**
     * default constructor
     */
    DetectCommand(){
        super("detect","used to detect all traps in the current room\ndetect");
    }

    /**
     * prints all traps in the current room
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        Binlay.player.getCurrentRoom().printTraps();
        return true;
    }
}
