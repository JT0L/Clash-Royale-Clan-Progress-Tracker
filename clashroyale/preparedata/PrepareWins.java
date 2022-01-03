package pl.clashroyale.preparedata;

import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PrepareWins extends PrepareData {
    @Override
    public List<Integer> makeYList(List<Player> playerHistory) {
        List<Integer> winsDifferences = new ArrayList<Integer>();
        for (int i = 0; i < playerHistory.size(); i++) {
            var diff = playerHistory.get(i).getWins() - playerHistory.get(0).getWins();
            winsDifferences.add(diff);
        }

        return winsDifferences;
    }
}
