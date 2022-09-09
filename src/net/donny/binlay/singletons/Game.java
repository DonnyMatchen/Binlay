package net.donny.binlay.singletons;

import net.donny.binlay.Binlay;
import net.donny.binlay.commands.*;
import net.donny.binlay.rooms.Room;
import net.donny.binlay.rooms.RoomFactory;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the PARSE and starts the game.
 * 
 * @author  Michael Kolling and David J. Barnes modified by Rodrigo A. Obando (2018)
 * @version 1.1 (December 2002)
 */

public class Game {
    public static boolean win;
    private static boolean exists = false;

    /**
     * default constructor
     */
    public Game() throws SingletonException {
        if(exists){
            throw new SingletonException();
        }else {
            Binlay.player = new Player(createWorld());
            Binlay.parse = new Parser();
            exists = true;
        }
    }

    /**
     * initializes world creation
     * @return starting room
     */
    private Room createWorld() {
        return RoomFactory.start();
    }

    /**
     * main method for the game
     */
    public void play() {
        printWelcome();
        boolean finished = false;
        while(!finished) {
            if(!win) {
                Command command = Binlay.parse.getCommand();
                if (command == null) {
                    System.out.println("Invalid Command!");
                    Binlay.fail();
                } else {
                    if (command instanceof QuitCommand) {
                        finished = command.execute();
                    } else {
                        command.execute();
                    }
                }
            }else{
                finished = true;
                System.out.println("Congradulations, you won!");
            }
        }
        printScore();
        System.out.println("Thank you for playing!");
    }

    /**
     * print out welcome
     */
    private void printWelcome() {
        System.out.println("\nWelcome to the World of Binlay!\n" +
                " We hope you enjoy your stay!\n" +
                " you're objective is to avoid failures and find the Font of Wisdom!\n" +
                "\nType 'help' to review available commands!\ngood luck!\n\n" +
                Binlay.player.getCurrentRoom().getLongDescription());
    }

    /**
     * prints out final score
     */
    private void printScore(){
        System.out.println(new StringBuilder("you found ")
                .append(Binlay.player.getScore()).append(" coins!"));
    }
}
