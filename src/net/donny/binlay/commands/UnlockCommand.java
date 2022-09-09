package net.donny.binlay.commands;

import net.donny.binlay.Binlay;

public class UnlockCommand extends Command {
    /**
     * default constructor
     */
    UnlockCommand(){
        super("unlock","used to unlock a locked door.\n" +
                "BE SURE YOU ARE TYPING KEY COLOR!\nunlock [key color]");
    }

    /**
     * unlock a lock
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String lock;
        if(phrase != null && phrase.hasMoreTokens()){
            lock = phrase.nextToken();
        }else{
            System.out.println("Which lock?");
            lock = Binlay.getInput();
        }
        if(Binlay.player.hasKey(lock)) {
            return Binlay.player.getCurrentRoom().unlock(lock);
        }else{
            System.out.println("You don't have that key!");
            Binlay.fail();
            return false;
        }
    }
}
