package net.donny.binlay.rooms;

import net.donny.binlay.Binlay;

public enum Direction {
    /**
     * values
     */
    N,S,W,E;

    /**
     * reverses the inverse of the direction
     * @return new reversed direction
     */
    public Direction reverse() {
        switch(this) {
            case N: return S;
            case S: return N;
            case E: return W;
            case W: return E;
            default: return N;
        }
    }

    /**
     * takes a string and returns a direction
     * @param str input string
     * @return direction, N as default
     */
    public static Direction fromString(String str) {
        switch(str.toLowerCase()) {
            case "n":
            case "north": return N;
            case "e":
            case "east": return E;
            case "w":
            case "west": return W;
            case "s":
            case "south": return S;
            default:
                System.out.println("Invalid direction!");
                Binlay.fail();
                return null;
        }
    }

    /**
     * returns a string version of the object
     * @return string
     */
    @Override
    public String toString(){
        switch(this){
            case N: return "North";
            case E: return "East";
            case W: return "West";
            case S: return "South";
            default: return "North";
        }
    }
}
