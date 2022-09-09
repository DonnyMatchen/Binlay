package net.donny.binlay;

import net.donny.binlay.singletons.Parser;
import net.donny.binlay.singletons.Game;
import net.donny.binlay.singletons.Player;
import net.donny.binlay.singletons.SingletonException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Binlay {
    private static int failures;
    public static Parser parse;
    public static Player player;

    /**
     * increment the failure counter
     */
    public static void fail(){
        failures++;
    }

    /**
     * global method for accepting inputs, mainly for commands
     * but sometimes used for secondary prompts.
     * @return the input line
     */
    public static String getInput(){
        String inputLine = null;
        System.out.print("> ");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        } catch(java.io.IOException exc) {
            System.out.println ("There was an error during reading: "
                    + exc.getMessage());
        }
        return inputLine;
    }

    /**
     * main method
     * @param args program arguments
     */
    public static void main(String[] args){
        try {
            Game game = new Game();
            failures = 0;
            game.play();
            System.out.println("You failed "+failures+" times!  congrats!");
        }catch(SingletonException err){
            System.out.println("A CRITICAL FAILURE HAS OCCURRED!");
        }
    }
}
