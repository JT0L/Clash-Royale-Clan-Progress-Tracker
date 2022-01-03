package pl.clashroyale.chart;

import pl.clashroyale.preparedata.*;
import pl.clashroyale.clanstructureobjects.Clan;
import pl.clashroyale.clanstructureobjects.Player;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PrepareSeries {

    static XYChart.Series prepareSeries(Clan clan, List<Long> xList, List<String> tagsOfPlayersToDisplay, int numberOfThisSeries, int attribute){
        List<Player> playerHistory = clan.getPlayersHistory().get(tagsOfPlayersToDisplay.get(numberOfThisSeries));
        XYChart.Series series = new XYChart.Series();
        switch (attribute) {
            case 1 -> {
                PrepareExperience prepareExperience = new PrepareExperience();
                series = prepareExperience.makeSeries(playerHistory, xList);
            }
            case 2 -> {
                PrepareTrophies prepareTrophies = new PrepareTrophies();
                series = prepareTrophies.makeSeries(playerHistory, xList);
            }
            case 3 -> {
                PrepareWins prepareWins = new PrepareWins();
                series = prepareWins.makeSeries(playerHistory, xList);
            }
            case 4 -> {
                PrepareDonations prepareDonations = new PrepareDonations();
                series = prepareDonations.makeSeries(playerHistory, xList);
            }
        }

        return series;
    }


    static List<Long> makeXList(List<String> stringDates) {
        List<LocalDateTime> dates = transformToDateFromString(stringDates);
        List<Long> timeDifferences = new ArrayList<>();

        for (LocalDateTime date : dates) {
            long diff = ChronoUnit.MINUTES.between(LocalDateTime.now(), date);
            timeDifferences.add(diff);
        }

        return timeDifferences;
    }


    static List<LocalDateTime> transformToDateFromString(List<String> stringDates){
        List<LocalDateTime> dates = new ArrayList<>();
        for (String stringDate : stringDates) {

            String modifiedStringDate = stringDate.substring(0, 10) +"-"+stringDate.substring(11, 13) + "-" + stringDate.substring(14, 16) + "-" + stringDate.substring(17, 19);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime dateTime = LocalDateTime.parse(modifiedStringDate, formatter);
            dates.add(dateTime);
        }

        return dates;
    }
}
