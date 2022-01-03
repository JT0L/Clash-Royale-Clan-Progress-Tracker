package pl.clashroyale.tools;


import pl.clashroyale.clanstructureobjects.Card;
import pl.clashroyale.clanstructureobjects.Pair;
import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    public static List<Pair> parseDictionaryLine(String responseRaw, List<String> parameters){
        String response = removeStringContainer(responseRaw);
        String[] responseList = response.split(",");

        List <Pair> output = new ArrayList<>();

        for (String parameter : parameters){
            for (String s : responseList) {
                String key = removeStringContainer(s
                        .split(":")[0]);
                String value = removeStringContainer(s
                        .split(":")[1]);

                if (parameter.equals(key)) {
                    Pair singleFeature = new Pair(key, value);
                    output.add(singleFeature);
                    break;
                }
            }
        }

        return output;
    }


    public static List<Card> getCards(String responseRaw){
        String response = removeStringContainer(responseRaw);
        String[] responseList = response.split("\\[");
        String cardsString = responseList[responseList.length-2].split("\\]")[0]; // this part is hardcoded but it makes it much faster but we will provide at least control check here:
        assert controlCheck(responseList[responseList.length-3]);

        String[] rawListOfCards = cardsString.split("},");
        List <Card> listOfCards = new ArrayList<>();

        for (String cardString : rawListOfCards){
            var cardAttributes= parseDictionaryLine(cardString, Card.createListOfParameters());
            Card card = new Card(cardAttributes);
            listOfCards.add(card);
        }

        return listOfCards;
    }


    public static String removeStringContainer(String element){
        if (String.valueOf(element.charAt(0)).equals("{") || String.valueOf(element.charAt(0)).equals("\"")) {
            return element.substring(1, element.length() - 1);
        }
        return element;
    }


    public static boolean controlCheck(String segmentBeforeCards){
        String lastPart = segmentBeforeCards.substring(segmentBeforeCards.length()-8);
        if (lastPart.equals("\"cards\":")){
            return true;
        }
        System.out.println("Design of the response has changed, this hardcoded part no longer works");
        return false;
    }


    public static List<Pair> parseMembers(String clanMembersRaw){
        List<Pair> members = new ArrayList<>();
        var clanMembers = clanMembersRaw.split("\\[\\{")[1]
                .split("],\"paging")[0]
                .split("},\\{");

        for (String memberString : clanMembers){
            var memberAttributes= parseDictionaryLine(String.join("", "{", memberString), Player.createShortListOfParameters());
            Pair member = new Pair(memberAttributes.get(0).getSecond(), memberAttributes.get(1).getSecond());
            members.add(member);
        }

        return members;
    }

    public static List<String> parseTagFile(String fileContent){
        List<String> listofTags = new ArrayList<>();
        String [] members = fileContent.split(";");
        for (String member : members){
            listofTags.add(member.split(",")[1].substring(1));
        }

        return listofTags;
    }
}
