package net.donny.binlay.commands;

import java.util.ArrayList;

/**
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds a collection of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes modified by Rodrigo A. Obando (2018)
 * @version 2.0 (December 2002)
 */

public class CommandRegistry {
    private ArrayList<Command> commands;

    /**
     * Constructor used to initialize the registry of commands
     */
    public CommandRegistry(){
        commands = new ArrayList<>();
        HelpCommand help = new HelpCommand(this);
        registerAll(new Command[]{
                new BackCommand(),
                new DefuseCommand(),
                new DetectCommand(),
                new DisarmCommand(),
                new DropCommand(),
                new GoCommand(),
                new GrabCommand(),
                help,
                new InspectCommand(),
                new InventoryCommand(),
                new LookCommand(),
                new QuitCommand(),
                new UnlockCommand(),
                new WeighCommand()
        });
        help.activate();
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    /**
     * recovers a command object from the registry from a name
     * @param word name of the command
     * @return the command associated with the first word
     * null if no matching command is in the registry
     */
    public Command get(String word) {
        for(Command c : commands){
            if(c.getName().equals(word)){
                return c;
            }
        }
        return null;
    }

    /**
     * prints out all command names in the registry
     */
    void showAll() {
        for(Command c : commands) {
            System.out.print(c.getName() + "  ");
        }
        System.out.println();
    }

    /**
     * add a command to the registry
     * @param cmd new command
     */
    private void registerCommand(Command cmd){
        commands.add(cmd);
    }

    /**
     * add multiple commands to the registry
     * @param cmds array of new commands
     */
    private void registerAll(Command[] cmds){
        for(Command c : cmds){
            registerCommand(c);
        }
    }
}