package SearchingEngine.strategy.impl.impl;

import SearchingEngine.InvertIndexes;
import SearchingEngine.strategy.impl.SearchingStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class AllSearchingStrategy implements SearchingStrategy {

    @Override
    public void search(List<String> allPeopleList, String searchingWord) {
        Map<String, List<Integer>> peopleMap = InvertIndexes.invertedIndexesMap(allPeopleList);
        String[] splitSearchingWord = searchingWord.toLowerCase().split(" ");

        Set<Integer> indexes = Arrays.stream(splitSearchingWord).filter(peopleMap::containsKey).flatMap(mapValue -> peopleMap.get(mapValue).stream()).collect(Collectors.toSet());

        List<String> searchResult = indexes.stream().filter(index -> allPeopleList.get(index).contains(searchingWord)).map(allPeopleList::get).toList();

        if (searchResult.isEmpty()) {
            System.out.println("No matching people found!");
        } else {
            System.out.printf("%d person found: \n" + searchResult.get(0) + "\n", searchResult.size());
        }
    }
}
