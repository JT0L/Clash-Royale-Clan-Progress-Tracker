package pl.clashroyale.menus;

import pl.clashroyale.chart.Chart;
import java.util.Scanner;


public class MembersMenu extends Menu {
    @Override
    protected void displayMenu() {
        System.out.println("Members menu");
        System.out.println("1 Choose player");
        System.out.println("2 Quit");
    }


    @Override
    public void menu(Chart chart) {
        boolean doContinue = true;
        while (doContinue) {
            displayMenu();
            int option = chooseOption(2);
            switch (option) {
                case 1 -> choosePlayers(chart);
                case 2 -> doContinue = false;
            }
        }

        MainMenu mainMenu = new MainMenu();
        mainMenu.menu(chart);
    }


    private void choosePlayers(Chart chart){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            chart.playersToDisplay.displayCurrentList(chart.clan);
            System.out.println("Enter player's name. If he already is in the list he will be removed. Otherwise he will be added");
            System.out.println("If you want to add all members enter All. If you want to go back, enter Exit");

            String input = scanner.nextLine();

            if (checkSpecialWords(input, chart)){
                break;
            }

            if (chart.playersToDisplay.removePlayer(chart.clan, input)){
                if(chart.playersToDisplay.addPlayer(chart.clan, input)){
                    System.out.println("There isn't player of the name you entered in this clan \n");
                }
            }

        }

    }


    private boolean checkSpecialWords(String input, Chart chart){
        if (input.equals("Exit")){
            return true;
        }

        if (input.equals("All")){
            chart.playersToDisplay.tagsOfChosenPlayers.clear();
            for (var playerHistory : chart.clan.getPlayersHistory().values()){
                chart.playersToDisplay.tagsOfChosenPlayers.add(playerHistory.get(0).getTag().substring(1));
            }
        }

        return false;
    }
}
