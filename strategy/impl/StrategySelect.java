package SearchingEngine.strategy.impl;

import SearchingEngine.strategy.impl.impl.*;

import java.util.*;

public class StrategySelect {
    public static void selectingSearchingStrategy(List<String> allPeopleList, String strategy, String queryWord) {

        switch (strategy) {
            case "ALL" -> new AllSearchingStrategy().search(allPeopleList, queryWord);
            case "ANY" -> new AnySearchingStrategy().search(allPeopleList, queryWord);
            case "NONE" -> new NoneSearchingStrategy().search(allPeopleList, queryWord);
        }
    }
}
