package pl.clashroyale.clanstructureobjects;


import pl.clashroyale.tools.ApiRequest;
import pl.clashroyale.tools.FileOperations;
import pl.clashroyale.tools.Parser;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Clan {
    private Map<String, List<Player>> playersHistory; // Each element of this map is a list in which every element is historical player state(represented as Player object)
    private List<String> dates;

    public Map<String, List<Player>> getPlayersHistory() {
        return playersHistory;
    }

    public List<String> getDates() {
        return dates;
    }

    public Clan(String clanDirectoryPath, List<String> playersTags) {
        this.playersHistory = Clan.getClan(clanDirectoryPath, playersTags);
        this.dates = Clan.getDates(clanDirectoryPath);
    }


    public static String getClanMembersResponse(String clanTag, String apiUrl, String token){
        String clanUrl = apiUrl + "clans/%23" + clanTag + "/members";
        return ApiRequest.getSingleResponse(clanUrl, token);
    }


    public static void saveClanMembersTags(String clanMembersResponse, String clanDirectoryPath) {
        List<Pair> clanMembers = Parser.parseMembers(clanMembersResponse);
        String fileContent = toFile(clanMembers);
        String filePath = clanDirectoryPath + "listOfPlayersTags";
        FileOperations.writeToFile(fileContent, filePath);
    }


    public static String saveDate(String clanDirectoryPath){
        String listOfLogsDatesPath = clanDirectoryPath + "logs";
        LocalDateTime currentDate = LocalDateTime.now();
        String currentDateString = currentDate + "\n";
        FileOperations.appendToFile(currentDateString, listOfLogsDatesPath);

        return currentDateString;
    }


    public static String toFile(List<Pair> clanMembers){
        String fileContent = "";
        for (Pair member : clanMembers){
            fileContent = fileContent + member.getFirst() + "," + member.getSecond() + "\n";
        }

        return fileContent;
    }


    public static void updateClanInfo(String clanDirectoryPath, String apiUrl, String token){
        String currentDateString = saveDate(clanDirectoryPath);

        List<String> listOfTags = getPlayersTags(clanDirectoryPath);

        for (String tag : listOfTags){
            String playerResponse = Player.getPlayerResponse(tag, apiUrl, token);
            Player.saveToFile(playerResponse, tag, currentDateString, clanDirectoryPath);
        }

    }


    public static List<String> getPlayersTags(String clanDirectoryPath){
        String listOfTagsFilePath = clanDirectoryPath + "listOfPlayersTags";
        String fileContent = FileOperations.readFile(listOfTagsFilePath);

        return Parser.parseTagFile(fileContent);
    }


    public static List<String> getDates(String clanDirectoryPath){
        String listOfTagsFilePath = clanDirectoryPath + "logs";
        String fileContent = FileOperations.readFullFile(listOfTagsFilePath);

        return List.of(fileContent.split("\n"));
    }


    public static Map<String, List<Player>> getClan(String clanDirectoryPath, List<String> playersTags){
        Map <String, List<Player>> clanHistory = new HashMap<>();
        for (String tag : playersTags){
            clanHistory.put(tag, Player.createPlayerHistoryFromFile(clanDirectoryPath, tag));
        }

        return clanHistory;
    }
}

