package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class MasterChef extends Person implements Subject {
    private ArrayList<Chef> masterChefListeners;
    private static MasterChef masterChefInstance;
    private HashMap<Integer, ArrayList<String>> incomingOrders;
    private ArrayList<String> outgoingOrders;
    private int lastFoodOrdersSize = 0;

    public MasterChef (int x, int y) {
        super(x, y);
        incomingOrders = new HashMap<>();
        outgoingOrders = null;
        this.masterChefListeners = new ArrayList<>();
    }

    /**
     * Returns the instance of MasterChef and creates if not existing.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return the singleton instance of MasterChef.
     */
    public static MasterChef getInstance(int x, int y) {
        if (masterChefInstance == null) {
            masterChefInstance = new MasterChef(x, y);
        }
        return masterChefInstance;
    }

    public ArrayList<Chef> getMasterChefListeners () {
        return masterChefListeners;
    }

    /**
     * Receives an order from the waiter and stores it using the table number as key.
     * @param w the waiter submitting the order.
     */
    public void receiveOrder (Waiter w) {
        incomingOrders.put(w.getAssignedTable().getTableNumber(), w.getTableOrder(w.getVisits()));
    }

    public ArrayList<String> giveOutgoingOrders () {
        return outgoingOrders;
    }

    /**
     * Retrieves the list of items to plate for a given table number.
     * @param orderNumber table number to serve.
     * @return list of food items.
     */
    public void plateFood (int orderNumber) {
        outgoingOrders = incomingOrders.get(orderNumber);
    }

    @Override
    public void addObserver (Chef chef) {
        masterChefListeners.add(chef);
    }

    @Override
    public void removeObserver (Chef chef) {
        masterChefListeners.remove(chef);
    }

    /**
     * Notifies one available chef to transition to PREPCHEF state.
     */
    @Override
    public void notifyObserver () {
        for (int i = 0; i < masterChefListeners.size(); i++) {
            Chef currentChef = masterChefListeners.get(i);

            if (currentChef.chefState == Chef.chefStates.IDLE) {
                if (i == 0 || masterChefListeners.get(i - 1).chefState == Chef.chefStates.STATION || masterChefListeners.get(i - 1).chefState == Chef.chefStates.IDLE || masterChefListeners.get(i - 1).chefState == Chef.chefStates.COOKING) {
                    currentChef.setChefState(Chef.chefStates.PREPCHEF);
                    //currentChef.setCookingOrder();
                }
                break;
            }
        }
    }

    /**
     * Triggers chef notification when a new order is added.
     */
    public void update () {
        if (incomingOrders.size() > lastFoodOrdersSize) {
            this.notifyObserver();
            lastFoodOrdersSize = incomingOrders.size();
        }
        //State för att skicka beställning till waiter
    }
}
