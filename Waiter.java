package org.example;

import java.util.HashMap;

public class Waiter extends Staff implements TableSubscriber {

    private int diameter = 50;
    HashMap<Integer, String> appetizerMenu;
    HashMap<Integer, String> mainCourseMenu;
    HashMap<Integer, String> dessertMenu;

    public Waiter (int x, int y){
        super(x, y);
        appetizerMenu = new HashMap<>();
        mainCourseMenu = new HashMap<>();
        dessertMenu = new HashMap<>();
    }


    public void receiverOrder(int x, int y, int tableNumber, String appetizer, String mainCourse, String dessert) {
        appetizerMenu.put(tableNumber, appetizer);
        mainCourseMenu.put(tableNumber, mainCourse);
        dessertMenu.put(tableNumber, dessert);
    }

    public void walkToTable (Table table) {

    }

    public void walkToKitchenMaster () {

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

    public String getOrder () {
        return "hej";
    }

    public void update () {

    }
}