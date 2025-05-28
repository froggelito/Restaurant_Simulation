package org.example;

import java.util.ArrayList;

public class HeadWaiter {
    private ArrayList<Table> restaurantTables;
    private ArrayList<Guest> restaurantGuests;
    private int x, y;

    public HeadWaiter (int x, int y) {
        restaurantTables = new ArrayList<>();
        restaurantGuests = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void assignTable () {
        for (Table table: restaurantTables) {
            for (Guest guest : restaurantGuests) {
                guest.walkToTable(table.getX(), table.getY());
            }
        }
    }

    public ArrayList<Table> getRestaurantTables () {
        return restaurantTables;
    }

    public ArrayList<Guest> getRestaurantGuests () {
        return restaurantGuests;
    }

    public void update () {

    }
}
