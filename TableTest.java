package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table;
    Table table1;
    Table table2;
    Table table3;


    @BeforeEach
    void setUp() {
        table = new Table(100, 200, 1);
        table1 = new Table(100, 200, 1);
        table2 = new Table(100, 200, 1);
        table3 = new Table(100, 200, 1);
    }

    @Test
    void testAppetizerOrders() {

        /**
         * Test for when not all guests order
         */
        ArrayList<Guest> guests = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Guest guest = new Guest(0, 0, i);
            if (i == 0) {
                guest.orderAppetizer();
            }
            guests.add(guest);
        }
        assertFalse(table.appetizerOrders(guests));
        assertFalse(table.getOrderReady());
        assertEquals(0, table.getTableOrders().size());

        /**
         * Test for when all guests order
         */
        ArrayList<Guest> guests2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Guest guest = new Guest(0, 0, i);
            guest.getWholeOrder().add("Salad");
            guests2.add(guest);
        }
        assertTrue(table1.appetizerOrders(guests2));
        assertTrue(table1.getOrderReady());
        assertEquals(1, table1.getTableOrders().size());
        assertEquals(4, table1.getTableOrders().get(0).size());

        /**
         * Test if the method short circuits and avoids adding another order when called twice
         */
        ArrayList<Guest> guests3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Guest guest = new Guest(0, 0, i);
            guest.getWholeOrder().add("Fries");
            guests3.add(guest);
        }
        assertTrue(table1.appetizerOrders(guests3));
        assertTrue(table1.appetizerOrders(guests3));
        assertEquals(1, table1.getTableOrders().size());

        /**
         * Test for when an empty list is passed
         */
        ArrayList<Guest> emptyGuestList = new ArrayList<>();
        assertFalse(table2.appetizerOrders(emptyGuestList));
        assertFalse(table2.getOrderReady());
        assertEquals(0, table2.getTableOrders().size());

        /**
         * Test for when null is passed as a list
         */
        boolean result = table3.appetizerOrders(null);
        assertFalse(result);
        assertFalse(table3.getOrderReady());
        assertEquals(0, table3.getTableOrders().size());
    }
}
