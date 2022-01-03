package pl.clashroyale.clanstructureobjects;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final String name;
    private final int level;
    private final int maxLevel;
    private final int count;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCount() {
        return count;
    }

    public Card(List<Pair> cardAttributes) {
        this.name = cardAttributes.get(0).getSecond();
        this.level = Integer.valueOf(cardAttributes.get(1).getSecond());
        this.maxLevel = Integer.valueOf(cardAttributes.get(2).getSecond());
        this.count = Integer.valueOf(cardAttributes.get(3).getSecond());
    }

    public static List<String> createListOfParameters(){
        List <String> parameters = new ArrayList<>();

        parameters.add("name");
        parameters.add("level");
        parameters.add("maxLevel");
        parameters.add("count");

        return parameters;
    }

}

