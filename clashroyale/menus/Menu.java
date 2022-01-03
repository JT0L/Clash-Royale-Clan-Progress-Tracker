package pl.clashroyale.menus;

import pl.clashroyale.chart.Chart;

import java.util.Scanner;

public abstract class Menu {
    protected abstract void displayMenu();
    protected abstract void menu(Chart chart);


    public static Integer chooseOption(int numberOfOptions){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int input = Integer.parseInt(scanner.nextLine());
            if (input > 0 && input <= numberOfOptions) {
                return input;
            }
            System.out.println("Choose number from range 1 - " + numberOfOptions);
        }
    }

}
