package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling modified by Rodrigo A. Obando (2018)
 * @version 1.0 (December 2002)
 */
public class GoCommand extends Command {
    /**
     * default constructor
     */
    GoCommand() {
        super("go", "used to move to another room\ngo [north, south, east, west]");
    }

    /**
     * attempt to change rooms
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String direction;
        if(phrase != null && phrase.hasMoreTokens()) {
                direction = phrase.nextToken();
        }else{
            System.out.println("Which direction?");
            direction = Binlay.getInput();
        }
        Binlay.player.walk(direction);
        return true;
    }
}
