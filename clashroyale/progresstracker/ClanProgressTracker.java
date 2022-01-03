package pl.clashroyale.progresstracker;

import pl.clashroyale.chart.Chart;
import pl.clashroyale.clanstructureobjects.Clan;
import pl.clashroyale.menus.MainMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;


public class ClanProgressTracker extends Application {
    private static final String clanDirectoryPath = "";


    @Override
    public void start(Stage stage){

        Thread t = new Thread(() -> {
            List<String> listOfTags = Clan.getPlayersTags(clanDirectoryPath);
            Clan clan = new Clan(clanDirectoryPath, listOfTags);

            Chart chart = new Chart(stage, clan);

            MainMenu mainMenu = new MainMenu();
            mainMenu.menu(chart);


           Platform.runLater(() -> chart.plot());
        });
        t.start();

    }

    public static void main(String[] args) {
        launch();
    }
}