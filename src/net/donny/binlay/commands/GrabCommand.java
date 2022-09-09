package net.donny.binlay.commands;

import net.donny.binlay.Binlay;
import net.donny.binlay.landmark.FreeItemStack;
import java.util.ArrayList;

public class GrabCommand extends Command {

    /**
     * default constructor
     */
    GrabCommand(){
        super("grab","used to pickup an item on the ground in the current room" +
                "\nfor keys and the font of wisdom, only 'key' and 'font' respectively need be typed" +
                "\ngrab [item name]");
    }

    /**
     * prints out all visible items in the room
     * then accepts input to pick which one to add to the inventory
     * @return
     * true: command successful
     * false: command failure
     */
    @Override
    public boolean execute() {
        String choose;
        ArrayList<FreeItemStack> items = Binlay.player.getCurrentRoom().getItems();
        ArrayList<String> names = new ArrayList<>();
        for(FreeItemStack stack : items){
            if(stack.getItems().getTypeName().contains("Font")){
                names.add("font");
            } else if (stack.getItems().getTypeName().contains("Key")){
                names.add("key");
            } else {
                names.add(stack.getItems().getTypeName().toLowerCase());
            }
        }
        if(phrase != null && phrase.hasMoreTokens()){
            choose = phrase.nextToken();
        } else {
            System.out.println("Which Item?");
            StringBuilder sb = new StringBuilder("\t");
            for(String str : names){
                sb.append(str).append(" ");
            }
            System.out.println(sb);
            choose = Binlay.getInput();
        }
        if(names.contains(choose)) {
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(choose.toLowerCase())) {
                    int q = Binlay.player.getCurrentRoom().pickup(items.get(i));
                    if(q > -1) {
                        System.out.println(new StringBuilder("You grab ")
                                .append(items.get(i).getItems().getCount() - q).append(" ")
                                .append(items.get(i).getItems().getTypeName())
                                .append("(s)"));
                    }
                }
            }
            return true;
        }else if (choose.equals("all")) {
            for (FreeItemStack stack : items){
                int q = Binlay.player.getCurrentRoom().pickup(stack);
                if(q > -1) {
                    System.out.println(new StringBuilder("You grab ")
                            .append(stack.getItems().getCount() - q).append(" ")
                            .append(stack.getItems().getTypeName())
                            .append("(s)"));
                }
            }
            return true;
        }else{
            System.out.println("That item doesn't even exist!");
            Binlay.fail();
            return false;
        }
    }
}
