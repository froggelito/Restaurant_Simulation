package org.example;

import java.util.ArrayList;

public class Table {
    public int tableNumber;
    private int x;
    private int y;
    private int diameter = 100;
    private int guests = 4;
    private Boolean orderReady = false;
    private ArrayList<ArrayList<String>> tableOrders = new ArrayList<>();

    public Table (int x, int y, int tableNumber) {
        this.x = x;
        this.y = y;
        this.tableNumber = tableNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getGuests () {
        return guests;
    }

    public boolean getOrderReady () {
        return orderReady;
    }

    public ArrayList<ArrayList<String>> getTableOrders () {
        return tableOrders;
    }

    /**
     * Checks if all guests at the table have ordered their appetizers.
     * If all orders are received they get added to the tableOrders list and marks as ready.
     * Can only run one per table
     * @param restaurantGuestList list of all guests in the restaurant.
     * @return true if all guests at the table have ordered and the order is ready.
     * @return false if the list is size is smaller than 4 or if table hasn't ordered
     */
    public Boolean appetizerOrders (ArrayList<Guest> restaurantGuestList) {
        if (orderReady) {
            return true;
        }

        if (restaurantGuestList == null ) {
            return false;
        }

        else {
            int orders = 0;
            ArrayList<String> firstOrders = new ArrayList<>();
            for (Guest guest : restaurantGuestList) {
                if (!guest.getWholeOrder().isEmpty()) {
                    firstOrders.add(guest.getOrder(0));
                    orders++;
                }
            }

            if (orders == this.guests) {
                tableOrders.add(firstOrders);
                orderReady = true;
            }
            return orderReady;
        }
    }
}
