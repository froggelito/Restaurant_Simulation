package org.example;

import java.util.ArrayList;
import java.util.List;

public class Guest extends Person{
    private final Menu menu = new Menu();
    private ArrayList<String> order = new ArrayList<>();
    private int tableSeat;

    public Guest (int x, int y, int tableSeat) {
        super(x, y);
        this.tableSeat = tableSeat;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getDiameter () {
        return diameter;
    }

    public String getOrder (int whichOrder) {
        return order.get(whichOrder);
    }

    public ArrayList<String> getWholeOrder() {
        return order;
    }

    /**
     * Simulates the guest ordering an appetizer from the menu after 2 seconds have passed.
     * A random appetizer is chosen from the menu and added to the guest's order.
     */
    public void orderAppetizer () {
        startTimer();

        long timer = System.currentTimeMillis();
        if (timer - startTime > 2000) {
            List<String> appetizer = menu.getAppetizerMenu();
            int randomNum = (int) (Math.random() * 3);
            order.add(appetizer.get(randomNum));
        }
    }

    public void orderMainCourse () {

    }

    public void orderDessert () {

    }

    /**
     * Moves the guest one step toward the target table coordinates.
     * @param destinationX the x-coordinate of the table.
     * @param destinationY the y-coordinate of the table.
     * @return true if the guest has reached the destination, otherwise false.
     */
    public boolean walkToTable (int destinationX, int destinationY){
        int distanceX = Integer.compare(destinationX - this.x, 0);
        int distanceY = Integer.compare(destinationY - this.y, 0);

        if (distanceX != 0) x += distanceX;

        else if (distanceY != 0) y += distanceY;

        else arrived = true;

        return arrived;
    }

    /**
     * Called each update cycle to simulate guest behavior.
     * Currently calls for the appetizer method.
     */
    public void update () {
        this.orderAppetizer();
    }
}
