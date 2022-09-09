package net.donny.binlay.landmark;

import java.util.Collection;

public abstract class Landmark {
    private boolean visible = true;

    /**
     * prints a brief description of the landmark
     */
    public abstract String speak();

    /**
     * hide the landmark
     */
    public void hide(){
        visible = false;
    }

    /**
     * unhide the landmark
     */
    public void unHide(){
        visible = true;
    }

    /**
     * tester
     * @return
     * true: visible
     * false: invisible
     */
    public boolean isVisible(){
        return visible;
    }

    /**
     * gets the number of landmarks visible in a collection
     * @param list input collection to be searched through
     * @return number of visible landmarks
     */
    public static int getVisibleCount(Collection<Landmark> list){
        int count = 0;
        for(Landmark l : list){
            if(l.isVisible()){
                count++;
            }
        }
        return count;
    }
}
