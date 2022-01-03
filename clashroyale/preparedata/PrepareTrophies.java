package pl.clashroyale.preparedata;

import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PrepareTrophies extends PrepareData {
    @Override
    public List<Integer> makeYList(List<Player> playerHistory) {
        List<Integer> trophiesDifferences = new ArrayList<Integer>();
        for (int i = 0; i < playerHistory.size(); i++) {
            var diff = playerHistory.get(i).getTrophies() - playerHistory.get(0).getTrophies();
            trophiesDifferences.add(diff);
        }

        return trophiesDifferences;
    }

}
