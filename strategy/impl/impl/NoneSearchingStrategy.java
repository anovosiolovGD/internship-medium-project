package SearchingEngine.strategy.impl.impl;

import SearchingEngine.InvertIndexes;
import SearchingEngine.strategy.impl.SearchingStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NoneSearchingStrategy implements SearchingStrategy {
    @Override
    public void search(List<String> allPeopleList, String searchingWord) {
        Map<String, List<Integer>> peopleMap = InvertIndexes.invertedIndexesMap(allPeopleList);
        String[] splitSearchingWord = searchingWord.toLowerCase().split(" ");
        List<Integer> index = Arrays.stream(splitSearchingWord).filter(peopleMap::containsKey).flatMap(mapValue -> peopleMap.get(mapValue).stream()).toList();
        List<String> searchResult = index.stream().map(allPeopleList::get).toList();
        allPeopleList.removeAll(searchResult);
        if (allPeopleList.isEmpty()) {
            System.out.println("No matching people found!");
        } else {
            System.out.printf("%d persons found: \n", allPeopleList.size());
            allPeopleList.forEach(System.out::println);

        }
    }
}
