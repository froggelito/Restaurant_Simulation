package org.example;

import java.util.ArrayList;

public abstract class Chef extends Person implements Observer{
    protected final MasterChef masterChef;
    protected final PrepChef prepChef;
    protected enum chefStates {IDLE, STATION, COOKING, PREPCHEF, MASTERCHEF}
    protected chefStates chefState = chefStates.IDLE;
    protected int startX, startY;
    protected String chefName;
    protected int cookingOrder;

    public Chef (int x, int y, MasterChef masterChef, PrepChef prepChef, String chefName) {
        super(x, y);
        startX = x;
        startY = y;
        this.masterChef = masterChef;
        this.prepChef = prepChef;
        this.chefName = chefName;
    }

    public void setChefState (chefStates state) {
        chefState = state;
    }

    public chefStates getChefState() {
        return chefState;
    }

    public String getChefName () {
        return chefName;
    }

    /**
     * Moves the Chef towards the MasterChef's position.
     */
    public void walkToMasterChef () {
        destinationX = masterChef.getX() - masterChef.getDiameter();
        destinationY = masterChef.getY();
        this.walk();
    }

    /**
     * Moves the Chef towards the PrepChef to get ingredients.
     */
    public void getIngredients () {
        destinationX = prepChef.getX() - prepChef.getDiameter();
        destinationY = prepChef.getY();
        this.walk();
    }

    /**
     * Moves the Chef back to their original cooking station position after a delay.
     */
    public void walkToStation () {
        startTimer();

        long timer = System.currentTimeMillis();
        if (timer - startTime > 2000) {
            destinationX = startX;
            destinationY = startY;
            this.walk();
        }
    }

    public void setCookingOrder (int orderReference) {
        cookingOrder = orderReference;
    }

    public int getCookingOrder () {
        return cookingOrder;
    }

    /**
     * Simulates the cooking process with a timer
     * @return true if cooking is finished, false otherwise
     */
    public boolean cookFood () {
        startTimer();
        long timer = System.currentTimeMillis();
        return timer - startTime > 8000;
    }

    /**
     * Called when the observed subject notifies this Chef and
     * updates the Chef's behavior based on the current chefState:
     */
    @Override
    public void update () {
        if (this.chefState == chefStates.PREPCHEF) {
            this.getIngredients();
            if (arrived){
                this.setChefState(chefStates.STATION);
                arrived = false;
            }
        }

        else if (this.chefState == chefStates.STATION) {
            this.walkToStation();
            if (arrived){
                this.resetTimer();
                this.setChefState(chefStates.COOKING);
                arrived = false;
            }
        }

        else if (this.chefState == chefStates.COOKING) {
            if (this.cookFood()) {
                this.setChefState(chefStates.MASTERCHEF);
                masterChef.plateFood(cookingOrder);
            }
        }

        else if (this.chefState == chefStates.MASTERCHEF) {
            this.walkToMasterChef();
            if (arrived){
                this.setChefState(chefStates.IDLE);
                arrived = false;
            }
        }
    }
}
