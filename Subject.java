package org.example;

public interface Subject {

    /**
     * Adds a chef as an observer.
     * @param chef the chef to observe.
     */
    void addObserver(Chef chef);

    /**
     * Removes a chef from observing.
     * @param chef the chef to remove.
     */
    void removeObserver(Chef chef);

    /**
     * Notifies observers.
     */
    void notifyObserver();
}
