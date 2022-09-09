package net.donny.binlay.commands;

import java.util.StringTokenizer;

/**
 * This class is an abstract superclass for all command classes in the game.
 * Each user command is implemented by a specific command subclass.
 *
 * Objects of class Command can store an optional argument word (a second
 * word entered on the command line). If the command had only one word, 
 * then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes modified by Rodrigo A. Obando (2018)
 * @version 2.0 (December 2002)
 */

public abstract class Command {
    private String name;
    private String usage;
    StringTokenizer phrase;

    /**
     * Default Constructor
     * @param name the first word of the command
     */
    public Command(String name, String usage){
        this.name = name;
        this.usage = usage;
        phrase = null;
    }

    /**
     * getter
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return the usage of the command.
     */
    String getUsage(){
        return usage;
    }

    /**
     * the actual execution of the command
     * @return
     * true: command successful
     * false: command failure
     */
    public abstract boolean execute();

    /**
     * sets phrase
     * @param phrase StringTokenizer to be used for the command
     */
    public void addPhrase(StringTokenizer phrase){
        this.phrase = phrase;
    }
}