package SearchingEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertIndexes {
    public static Map<String, List<Integer>> invertedIndexesMap (List<String> list){
        final Map<String, List<Integer>> invertedIndexes = new HashMap<>();
        int i = 0;
        for (String s : list) {
            String[] s1 = s.trim().split(" ");
            for (String str : s1) {
                List<Integer> currentWordIndexesList = invertedIndexes.get(str.toLowerCase());
                if (currentWordIndexesList == null) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(i);
                    invertedIndexes.put(str.toLowerCase(), integerList);
                } else {
                    currentWordIndexesList.add(i);
                    invertedIndexes.put(str.toLowerCase(), currentWordIndexesList);
                }
            }
            i++;
        }
        return invertedIndexes;
    }
}
