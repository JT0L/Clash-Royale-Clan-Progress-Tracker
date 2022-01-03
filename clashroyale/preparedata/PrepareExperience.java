package pl.clashroyale.preparedata;

import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;


public class PrepareExperience extends PrepareData {
    @Override
    public List<Integer> makeYList(List<Player> playerHistory) {
        List<Integer> pointDifferences = new ArrayList<Integer>();
        for (int i = 0; i < playerHistory.size(); i++) {
            var diff = playerHistory.get(i).getExpPoints() - playerHistory.get(0).getExpPoints();
            pointDifferences.add(diff);
        }

        return pointDifferences;
    }

}
