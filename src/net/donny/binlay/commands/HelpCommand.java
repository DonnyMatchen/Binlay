package net.donny.binlay.commands;

import net.donny.binlay.Binlay;
import java.util.HashMap;

/**
 * Implementation of the 'help' user command.
 * 
 * @author Michael Kolling modified by Rodrigo A. Obando (2018)
 * @version 1.0 (December 2002)
 */
public class HelpCommand extends Command {
    private CommandRegistry commandWords;
    private HashMap<String, String> codex;

    /**
     * default constructor
     * @param words the command registry singleton
     */
    HelpCommand(CommandRegistry words) {
        super("help","used to get information about commands\nhelp {command}");
        commandWords = words;
        codex = new HashMap<>();
    }

    /**
     * used to initialize the codex of usage strings
     */
    void activate(){
        for(Command c : commandWords.getCommands()){
            codex.put(c.getName(),c.getUsage());
        }
    }

    /**
     * prints out all commands, and get details on specific commands
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        if(phrase != null && phrase.hasMoreTokens()){
            String command = phrase.nextToken();
            if(codex.keySet().contains(command)){
                System.out.println(codex.get(command));
                return true;
            }else{
                System.out.println("That is not a command!");
                Binlay.fail();
                return false;
            }
        } else {
            System.out.println("Available commands:");
            commandWords.showAll();
            System.out.println("You can also type help followed by a command to get usage!");
            return true;
        }
    }
}
