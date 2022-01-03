package pl.clashroyale.chart;


import pl.clashroyale.clanstructureobjects.Clan;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;


import java.util.List;

public class Chart {
    public Stage stage;
    public Clan clan;
    public ChartMembersList playersToDisplay;
    public int attribute;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart lineChart;


    public Chart(Stage stage, Clan clan) {
        this.stage = stage;
        this.clan = clan;
        this.playersToDisplay = new ChartMembersList();
        this.attribute = 1;
        this.xAxis = new NumberAxis();
        this.yAxis = new NumberAxis();
        setLabels();
        this.lineChart = new LineChart<Number,Number>(xAxis,yAxis);
    }


    public void plot(){
        stage.setTitle("Clan progress chart");

        lineChart = prepareLineChart();
        Scene scene  = new Scene(lineChart,800,600);

        stage.setScene(scene);
        stage.show();
    }


    public LineChart prepareLineChart(){
        lineChart.setTitle("Clan progress");

        List<Long> xList = PrepareSeries.makeXList(clan.getDates());
        for (int i = 0; i < playersToDisplay.tagsOfChosenPlayers.size(); i++){
            lineChart.getData().add(PrepareSeries.prepareSeries(clan, xList, playersToDisplay.tagsOfChosenPlayers, i, attribute));
        }

        return lineChart;
    }


    private void setLabels(){
        String attributeString = transformAttributeNumberToString();
        xAxis.setLabel("Minutes from now");
        yAxis.setLabel(attributeString);
    }


    private String transformAttributeNumberToString(){
        String attributeString = new String();
        switch (attribute){
            case 1:
                attributeString =  "Experience";
                break;
            case 2:
                attributeString = "Trophies";
                break;
            case 3:
                attributeString = "Wins";
                break;
            case 4:
                attributeString = "Donations";
        }

        return attributeString;
    }

}
