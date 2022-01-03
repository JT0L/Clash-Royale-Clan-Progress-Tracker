package pl.clashroyale.preparedata;

import pl.clashroyale.clanstructureobjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PrepareDonations extends PrepareData {
    @Override
    public List<Integer> makeYList(List<Player> playerHistory) {
        List<Integer> donationsDifferences = new ArrayList<Integer>();
        for (int i = 0; i < playerHistory.size(); i++) {
            var diff = playerHistory.get(i).getTotalDonations() - playerHistory.get(0).getTotalDonations();
            donationsDifferences.add(diff);
        }

        return donationsDifferences;
    }
}
