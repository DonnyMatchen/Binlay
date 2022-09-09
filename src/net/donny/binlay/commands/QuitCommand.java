package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling modified by Rodrigo A. Obando (2018)
 * @version 1.0 (December 2002)
 */
public class QuitCommand extends Command {
    /**
     * default constructor
     */
    QuitCommand()
    {
        super("quit","used to exit the game\nquit");
    }

    /**
     * quit the game
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        System.out.println("Are you sure?");
        return "yes".equals(Binlay.getInput().toLowerCase());
    }
}
