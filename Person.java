package org.example;

public abstract class Person {
    protected int x;
    protected int y;
    protected int diameter;
    protected int destinationX;
    protected int destinationY;
    protected Boolean arrived;
    protected enum States {IDLE, WALKING, COMMUNICATING}
    protected States state = States.IDLE;
    protected Boolean timerRun = true;
    protected long startTime;

    public Person (int x, int y) {
        this.x = x;
        this.y = y;
        this.diameter = 45;
        arrived = false;
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

    public States getState () {
        return state;
    }

    public void setState (States personState) {
        this.state = personState;
    }

    /**
     * Starts the timer if it is not already running.
     */
    public void startTimer() {
        if (timerRun){
            startTime = System.currentTimeMillis();
            timerRun = false;
        }
    }

    /**
     * Resets the timer allowing it to be started again.
     */
    public void resetTimer () {
        timerRun = true;
    }

    /**
     * Moves the person one step towards the destination coordinates.
     * Movement occurs along the X axis first, then Y axis.
     * Once the person reaches the destination, the arrived flag is set to true.
     */
    public void walk (){
        int distanceX = Integer.compare(destinationX - this.x, 0);
        int distanceY = Integer.compare(destinationY - this.y, 0);

        if (distanceX != 0) x += distanceX;

        else if (distanceY != 0) y += distanceY;

        else arrived = true;
    }
}
