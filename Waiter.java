package org.example;

import java.util.ArrayList;

public class Waiter extends Person {

    private ArrayList<ArrayList<String>> tableOrder;
    private Table assignedTable;
    private MasterChef masterChef;
    private int visits;

    public Waiter(int x, int y, Table assignedTable, MasterChef masterChef) {
        super(x, y);
        tableOrder = new ArrayList<>();
        this.assignedTable = assignedTable;
        this.masterChef = masterChef;
        visits = 0;
    }

    public ArrayList<String> getTableOrder (int course) {
        return tableOrder.get(course);
    }

    public Table getAssignedTable () {
        return assignedTable;
    }

    /**
     * Retrieves orders from the assigned table and adds to the waiter's list.
     */
    public void takeOrder() {
        //visits++;
        tableOrder.add(assignedTable.getTableOrders().get(visits));
    }

    public ArrayList<String> serveOrder () {
        return masterChef.giveOutgoingOrders();
    }

    public int getVisits () {
        return visits;
    }

    /**
     * Sets destination to the assigned table's coordinates and begins walking.
     */
    public void walkToTable() {
        destinationX = assignedTable.getX() - assignedTable.getDiameter()/2;
        destinationY = assignedTable.getY();
        this.walk();
    }

    /**
     * Sets destination to the MasterChef and walks after a delay.
     */
    public void walkToMasterChef() {
        startTimer();

        long timer = System.currentTimeMillis();
        if (timer - startTime > 2000) {
            destinationX = masterChef.getX() + masterChef.getDiameter();
            destinationY = masterChef.getY();
            this.walk();
        }
    }

    /**
     * Updates the waiter behavior based on current state:
     */
    public void update () {
        if (this.state == States.WALKING) {
            this.walkToTable();
            if (arrived){
                this.setState(States.COMMUNICATING);
                this.takeOrder();
                arrived = false;
            }
        }

        else if (this.state == States.COMMUNICATING) {
            this.walkToMasterChef();
            if (arrived){
                this.setState(States.IDLE);
                masterChef.receiveOrder(this);
                arrived = false;
            }
        }
        //Masterchef state för att få beställningen
    }
}
