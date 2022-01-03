package pl.clashroyale.menus;

import pl.clashroyale.chart.Chart;

public class ParametersMenu extends Menu {
    @Override
    protected void displayMenu() {
        System.out.println("Give the number corresponding to the attribute you want to check");
        System.out.println("1. experience");
        System.out.println("2. trophies");
        System.out.println("3. wins");
        System.out.println("4. donations");
    }


    @Override
    protected void menu(Chart chart) {
        displayMenu();
        chart.attribute = chooseOption(4);
        MainMenu mainMenu = new MainMenu();
        mainMenu.menu(chart);
    }
}
