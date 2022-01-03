package pl.clashroyale.menus;

import pl.clashroyale.chart.Chart;

public class MainMenu extends Menu {

    @Override
    public void displayMenu() {
        System.out.println("Main menu");
        System.out.println("1. Choose members you want to check ");
        System.out.println("2. Choose parameters you want to check");
        System.out.println("3. Plot graph");
    }

    @Override
    public void menu(Chart chart) {
        displayMenu();
        int option = chooseOption(3);
        switch (option) {
            case 1:
                MembersMenu membersMenu = new MembersMenu();
                membersMenu.menu(chart);
                break;
            case 2:
                ParametersMenu parametersMenu = new ParametersMenu();
                parametersMenu.menu(chart);
                break;
            case 3:
                break;


        }
    }

}
