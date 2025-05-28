package org.example;

import java.util.ArrayList;

public class PrepChef extends Person implements Subject {
    private ArrayList<Chef> prepChefListeners;

    public PrepChef (int x, int y) {
        super(x, y);
        prepChefListeners = new ArrayList<>();
    }

    public boolean giveIngredients () {
        return true;
    }

    /**
     * Simulates giving ingredients to a named chef.
     * @param name name of the chef.
     * @return message indicating ingredients were given to a certain chef.
     */
    public String giveIngredientsTo (String name) {
        return "giving ingredients to " + name;
    }

    public ArrayList<Chef> getPrepChefListeners () {
        return prepChefListeners;
    }

    @Override
    public void addObserver (Chef chef) {
        prepChefListeners.add(chef);
    }

    @Override
    public void removeObserver (Chef chef) {
        prepChefListeners.remove(chef);
    }

    /**
     * Notifies all registered chefs to receive ingredients.
     */
    @Override
    public void notifyObserver () {
        for (Chef chef : prepChefListeners) {
            chef.getIngredients();
        }
    }

    /**
     * Checks each chef in PREPCHEF state and calls giveIngredientsTo.
     */
    public void update () {
        for (Chef chef : prepChefListeners) {
            if (chef.getChefState() == Chef.chefStates.PREPCHEF) {
                this.giveIngredientsTo(chef.getChefName());
            }
        }
    }
}
