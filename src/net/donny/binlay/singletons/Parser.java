package net.donny.binlay.singletons;

import net.donny.binlay.Binlay;
import net.donny.binlay.commands.Command;
import net.donny.binlay.commands.CommandRegistry;

import java.util.StringTokenizer;

/**
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes modified by Rodrigo A. Obando (2018)
 * @version 1.1 (December 2002)
 */

public class Parser{
    private static boolean exists;

    /**
     * the command registry singleton
     */
    private CommandRegistry commands;

    /**
     * default constructor
     */
    Parser() throws SingletonException {
        if(exists){
            throw new SingletonException();
        } else {
            this.commands = new CommandRegistry();
            exists = true;
        }
    }

    /**
     * checks the command registry singleton for the command
     * returns the object if it is there
     * @return the command object matching the name
     * null if no command object matches
     */
    public Command getCommand(){
        String inputLine = Binlay.getInput();
        String word;

        if(inputLine != null) {
            StringTokenizer tokenizer = new StringTokenizer(inputLine);

            if (tokenizer.hasMoreTokens()) {
                word = tokenizer.nextToken().toLowerCase();
            } else {
                word = null;
            }

            Command command = commands.get(word);
            if(command != null) {
                command.addPhrase(tokenizer);
            }
            return command;
        }else{
            return null;
        }
    }
}
