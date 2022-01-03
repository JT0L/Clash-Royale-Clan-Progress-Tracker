package pl.clashroyale.progresstracker;

import pl.clashroyale.clanstructureobjects.Clan;

public class Main {
    private static final String apiUrl = "https://api.clashroyale.com/v1/";
    private static final String token = "";
    private static final String clanTag = "";
    private static final String clanDirectoryPath = "";
    private static final boolean initializeClan = true;

    public static void main(String[] args) {
        if(initializeClan){
            String clanMemberResponse = Clan.getClanMembersResponse(clanTag, apiUrl, token);
            Clan.saveClanMembersTags(clanMemberResponse, clanDirectoryPath);
        }

        Clan.updateClanInfo(clanDirectoryPath, apiUrl, token);
    }
}
