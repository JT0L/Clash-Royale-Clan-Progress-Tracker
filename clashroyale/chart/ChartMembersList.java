package pl.clashroyale.chart;

import pl.clashroyale.clanstructureobjects.Clan;
import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;

public class ChartMembersList {
    public List<String> tagsOfChosenPlayers;

    public ChartMembersList() {
        this.tagsOfChosenPlayers = new ArrayList<>();
    }


    public boolean removePlayer(Clan clan, String name){ // returns true if there wasn't this player in the list
        boolean check = true;
        for (String tag : tagsOfChosenPlayers){
            if (name.equals(clan.getPlayersHistory().get(tag).get(0).getName())){
                tagsOfChosenPlayers.remove(tag);
                check = false;
                break;
            }
        }

        return check;
    }


    public boolean addPlayer(Clan clan, String name){  // returns true if there wasn't this player in the clan
        boolean check = true;
        for(List<Player> playerHistory : clan.getPlayersHistory().values()){
            if(name.equals(playerHistory.get(0).getName())){
                tagsOfChosenPlayers.add(playerHistory.get(0).getTag().substring(1));
                check = false;
            }
        }

        return check;
    }


    public void displayCurrentList(Clan clan){
        System.out.println("Current list of players to display: \n");
        for (String tag : tagsOfChosenPlayers){
            System.out.println(clan.getPlayersHistory().get(tag).get(0).getName());
        }
        System.out.println();

    }
}
