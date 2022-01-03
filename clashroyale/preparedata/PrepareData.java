package pl.clashroyale.preparedata;

import pl.clashroyale.clanstructureobjects.Player;
import javafx.scene.chart.XYChart;

import java.util.List;

public abstract class PrepareData {
    public XYChart.Series makeSeries(List<Player> playerHistory, List<Long> xList) {
        XYChart.Series series = new XYChart.Series();
        series.setName(playerHistory.get(0).getName());
        List<Integer> yList = makeYList(playerHistory);

        for (int i=0; i<playerHistory.size(); i++) {
            series.getData().add(new XYChart.Data(xList.get(i), yList.get(i)));
        }

        return series;
    }


    abstract List<Integer> makeYList(List<Player> playerHistory);
}
