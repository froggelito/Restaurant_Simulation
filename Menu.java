package org.example;

import java.util.List;

public class Menu {
    public List<String> AppetizerMenu = List.of("Onion rings", "Fries", "Roham");
    private List<String> mainCourseMenu = List.of("Pizza", "Matte", "Hamburger");
    private List<String> dessertMenu = List.of("Ice cream", "Brownie", "Adam");

    public List<String> getAppetizerMenu() {
        return AppetizerMenu;
    }

    public List<String> getMainCourseMenu() {
        return mainCourseMenu;
    }

    public List<String> getDessertMenu() {
        return dessertMenu;
    }
}
