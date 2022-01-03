package pl.clashroyale.clanstructureobjects;

import pl.clashroyale.tools.ApiRequest;
import pl.clashroyale.tools.FileOperations;
import pl.clashroyale.tools.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private final String tag;
    private final String name;
    private final int expLevel;
    private final int trophies;
    private final int wins;
    private final int losses;
    private final int totalDonations;
    private final int expPoints;
    private final Map <String, Card> playersCards = new HashMap<>();

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public int getExpLevel() {
        return expLevel;
    }

    public int getTrophies() {
        return trophies;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotalDonations() {
        return totalDonations;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public Map<String, Card> getPlayersCards() {
        return playersCards;
    }


    public Player(List<Pair> basicInfo, List<Card> playerCards) {
        this.tag = basicInfo.get(0).getSecond();
        this.name = basicInfo.get(1).getSecond();
        this.expLevel = Integer.valueOf(basicInfo.get(2).getSecond());
        this.trophies = Integer.valueOf(basicInfo.get(3).getSecond());
        this.wins = Integer.valueOf(basicInfo.get(4).getSecond());
        this.losses = Integer.valueOf(basicInfo.get(5).getSecond());
        this.totalDonations = Integer.valueOf(basicInfo.get(6).getSecond());
        this.expPoints = Integer.valueOf(basicInfo.get(7).getSecond());

        for (Card card : playerCards){
            this.playersCards.put(card.getName(), card);
        }

    }


    public static String getPlayerResponse(String playerTag, String apiUrl, String token){
        String playerUrl = apiUrl + "players/%23" + playerTag;
        return ApiRequest.getSingleResponse(playerUrl, token);
    }


    public static Player getPlayer(String playerTag, String apiUrl, String token){
        String response = getPlayerResponse(playerTag, apiUrl, token);
        var basicInfo = Parser.parseDictionaryLine(response, Player.createListOfParameters());
        var cardsInfo = Parser.getCards(response);

        return new Player(basicInfo, cardsInfo);
    }


    public static List<String> createListOfParameters(){
        List <String> parameters = new ArrayList<>();
        parameters.add("tag");
        parameters.add("name");
        parameters.add("expLevel");
        parameters.add("trophies");
        parameters.add("wins");
        parameters.add("losses");
        parameters.add("totalDonations");
        parameters.add("expPoints");

        return parameters;
    }


    public static List<String> createShortListOfParameters(){
        List <String> parameters = new ArrayList<String>();
        parameters.add("name");
        parameters.add("tag");
        return parameters;
    }


    public static void saveToFile(String playerResponse, String playerTag, String currentDate, String clanDirectoryPath){
        String fileContent = "-log-\n" + playerResponse + "\n" + currentDate;
        String playerFilePath = clanDirectoryPath + playerTag;

        FileOperations.appendToFile(fileContent, playerFilePath);

    }


    public static List<Player> createPlayerHistoryFromFile(String clanDirectoryPath, String playerTag){
        String playerFileString = clanDirectoryPath + playerTag;
        String fileContent = FileOperations.readFullFile(playerFileString);
        List<Player> playerHistory = new ArrayList<>();

        var temp = fileContent.split("-log-");

        for(int i = 1; i < temp.length; i++){
            String playerLog = temp[i].split("\n")[1];
            var basicInfo = Parser.parseDictionaryLine(playerLog, Player.createListOfParameters());
            var cardsInfo = Parser.getCards(playerLog);

            Player player = new Player(basicInfo, cardsInfo);
            playerHistory.add(player);
        }

        return playerHistory;
    }
}

