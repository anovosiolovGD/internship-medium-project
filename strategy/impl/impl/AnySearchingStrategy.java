package SearchingEngine.strategy.impl.impl;

import SearchingEngine.InvertIndexes;
import SearchingEngine.strategy.impl.SearchingStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class AnySearchingStrategy implements SearchingStrategy {
    @Override
    public void search(List<String> allPeopleList, String searchingWord) {
        Map<String, List<Integer>> peopleMap = InvertIndexes.invertedIndexesMap(allPeopleList);
        String[] splitSearchingWord = searchingWord.toLowerCase().split(" ");
        List<Integer> index = Arrays.stream(splitSearchingWord).filter(peopleMap::containsKey).flatMap(mapValue -> peopleMap.get(mapValue).stream()).toList();
        Set<String> searchResult = index.stream().map(allPeopleList::get).collect(Collectors.toSet());

        if (searchResult.isEmpty()) {
            System.out.println("No matching people found!");
        } else {
            System.out.printf("%d persons found: \n", searchResult.size());
            searchResult.forEach(System.out::println);

        }
    }
}
