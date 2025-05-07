package org.example;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public int tableNumber;
    private int x;
    private int y;
    private int diameter;
    public List<String> appetizerOrders;
    public List<String> mainCourseOrders;

    public Table (int x, int y, int tableNumber) {
        this.x = x;
        this.y = y;
        diameter = 75;
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

    public void addWaiter () {}

    public void removeWaiter () {}

    public void notifyWaiter () {}

    public void foodOrders () {}
}
