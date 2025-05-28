package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantMain extends JPanel {

    static ArrayList<Waiter> waiters = new ArrayList<>();
    static ArrayList<Table> tables = new ArrayList<>();
    static  ArrayList<ArrayList<Guest>> guests = new ArrayList<>();
    static MasterChef masterChef;
    static SousChef sousChef;
    static GardeManager gardeManager;
    static PastryChef pastryChef;
    static PrepChef prepChef;
    static ArrayList<Chef> chefs;

    // In here all objects that are needed for operating the restaurant should be created.
    // This is initialisation and determines the initial state of the program.
    static void setupRestaurant(){
        masterChef = new MasterChef(400, 300);
        prepChef = new PrepChef(300, 75);
        pastryChef = new PastryChef(400, 500, masterChef, prepChef, "Pastry Chef");
        gardeManager = new GardeManager(50, 50, masterChef, prepChef, "Garde Manager");
        sousChef = new SousChef(125, 375, masterChef, prepChef, "Sous Chef");

        chefs = new ArrayList<>();

        chefs.add(pastryChef);
        chefs.add(gardeManager);
        chefs.add(sousChef);

        masterChef.addObserver(pastryChef);
        masterChef.addObserver(gardeManager);
        masterChef.addObserver(sousChef);

        prepChef.addObserver(pastryChef);
        prepChef.addObserver(gardeManager);
        prepChef.addObserver(sousChef);


        int x = 600;
        for (int t = 0 ; t != 2 ; t++) {
            Table tableRow1 = new Table(x, 40, t);
            x += 350;
            tables.add(tableRow1);
        }

        for (int t = 2 ; t != 4 ; t++) {
            x -= 350;
            Table tableRow2 = new Table(x, 500, t);
            tables.add(tableRow2);
        }

        for (Table t: tables) {
            ArrayList<Guest> tableGuests = new ArrayList<>();
            for (int g = 0 ; g < t.getGuests() ; g++) {
                //hovmästare assign table number in fours
                tableGuests.add(new Guest(1, 1, t.getTableNumber()));
            }
            guests.add(tableGuests);
        }

        for (Table t: tables) {
            Waiter waiter = new Waiter(600, 300, t, masterChef);
            waiters.add(waiter);

        }

    }

    // Contains the simulation logic, should probably be broken into smaller pieces as the program expands
    static void update() {
        for (ArrayList<Guest> tableGuests : guests) {
            for (Guest g : tableGuests) {
                g.update();
            }
        }

        for (Table t : tables) {
            if (!t.getOrderReady()) {
                t.appetizerOrders(guests.get(t.tableNumber));
                if (t.getOrderReady()) {
                    Waiter waiter = waiters.get(t.tableNumber);
                    if (waiter.getState() == Person.States.IDLE)
                        waiter.setState (Person.States.WALKING);
                }
            }
        }

        // what should happen with the waiter each time the simulation loops
        for (Waiter w : waiters) {
            w.update();
        }

        masterChef.update();

        for (Chef chef: chefs) {
            chef.update();
        }

        prepChef.update();
        // ... similar updates for all other agents in the simulation.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(255, 245, 158, 184)); //  // Set the background color to light yellow

        g.setColor(Color.DARK_GRAY); // Set the color for the border lines
        g.drawRect(500, 0, 600, getHeight() - 5);
        //g.drawRect(800, 0, getWidth() - 5, getHeight() - 5);
        g.setColor(Color.BLACK);
        g.drawRect(500, 0, 695, getHeight() - 5);

        // Draw kitchen door
        g.setColor(Color.DARK_GRAY);
        g.fillRect(490, 270, 20, 100);
        g.fillRect(1090, 270, 20, 100);

        //Draw master chef office
        g.setColor(new Color(93, 191, 73, 255));
        g.fillRect(410, 200, 60, 200);

// Draw the master chef
        g.setColor(Color.WHITE);
        g.fillOval(masterChef.getX(), masterChef.getY(), masterChef.getDiameter(), masterChef.getDiameter());


        g.setColor(Color.GRAY);
        g.fillOval(prepChef.getX(), prepChef.getY(), prepChef.getDiameter(), prepChef.getDiameter()); // Draw circle with diameter of 50 pixels
        g.setColor(Color.WHITE);
        g.fillOval(prepChef.getX()+3, prepChef.getY()+3, prepChef.getDiameter()-6, prepChef.getDiameter()-6); // Draw circle with diameter of 50 pixels


        // Draw the chefs' workplaces
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 50, 150); // Prepchef
        g.fillRect(50, 0, 100, 50);
        g.fillRect(350, 50, 100, 100); // Garde Manger
        g.fillRect(450, 445, 50, 150); // Patissier
        g.fillRect(50, 300, 75, 200); // Sous chef

        // Draw tables
        drawTables(g);

        // Draw the waiters
        drawWaiters(g);

        // MORE CODE HERE
        drawChefs(g);
    }

    static void drawChefs(Graphics g){
        for (Chef chef : chefs) {
            g.setColor(Color.GRAY);
            g.fillOval(chef.getX(), chef.getY(), chef.getDiameter(), chef.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(chef.getX()+3, chef.getY()+3, chef.getDiameter()-6, chef.getDiameter()-6); // Draw circle with diameter of 50 pixels
        }
    }

    static void drawTables(Graphics g) {
        for (Table table : tables) {
            g.setColor(Color.RED);
            g.fillOval(table.getX(), table.getY(), table.getDiameter(), table.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(table.getX()+3, table.getY()+3, table.getDiameter()-6, table.getDiameter()-6);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(table.getTableNumber()), table.getX()+30,  table.getY()+35);
        }
    }

    static  void drawWaiters(Graphics g){
        for (Waiter waiter : waiters) {
            g.setColor(Color.BLACK);
            g.fillOval(waiter.getX(), waiter.getY(), waiter.getDiameter(), waiter.getDiameter()); // Draw circle with diameter of 50 pixels
            g.setColor(Color.WHITE);
            g.fillOval(waiter.getX()+7, waiter.getY()+7, waiter.getDiameter()-14, waiter.getDiameter()-14); // Draw circle with diameter of 50 pixels
        }
    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Restuarant Simulation");
        frame.setSize(1200, 640); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Add the custom panel (with circles) to the frame
        RestaurantMain panel = new RestaurantMain();
        frame.add(panel);

        // Display the window
        frame.setVisible(true);

        // Setup for the restaurant
        setupRestaurant();

        while (true) {
            try {
                Thread.sleep(33); // With the goal of having about 30 fps.
            } catch (Exception threadException) {
                System.out.println("Sleep exception: " + threadException.getMessage());
            }
            update();
            panel.repaint();
        }
    }
}
