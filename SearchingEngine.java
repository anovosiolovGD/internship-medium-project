package SearchingEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Description of how to use it you can find in README.md
 * */

public class SearchingEngine {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner textFile = new Scanner(new File(findPath(args)));
        mainMenu(allList(textFile));

    }

    public static List<String> allList(Scanner textFile) {
        List<String> allList = new ArrayList<>();
        String[] temp;
        while (textFile.hasNext()) {
            temp = textFile.nextLine().split("\n");
            Collections.addAll(allList, temp);
        }
        return allList;
    }

    public static String findPath(String[] args) {
        String path = "";
        for (int i = 0; i < args.length - 1; i++) {
            if ("--data".equals(args[i])) {
                path = args[i + 1];
            }
        }
        return path;
    }

    public static void mainMenu(List<String> allList) {

        int option = -1;
        while (option != 0) {
            System.out.println("""
                    === Menu ===\s
                    1. Find a person
                    2. Print all people
                    0. Exit""");
            option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    searchEngine(allList, enterStringData("Select a matching strategy: ALL, ANY, NONE"), enterStringData("Enter a name or email to search all suitable people."));
                    break;
                case 2:
                    printAll(allList);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
            }
            if (option > 2 || option < 0) {
                System.out.println("Incorrect option! Try again.");
            }
        }

    }

    public static void searchEngine(List<String> allList, String STRATEGY, String searchingWord) {
        //mapping inverted indexes
        Map<String, List<Integer>> peopleMap = new HashMap<>();
        String[] query = searchingWord.toLowerCase().split(" ");
        Set<String> searchResult = new HashSet<>();
        List<Integer> index = new ArrayList<>();
        int i = 0;
        for (String s : allList) {
            String[] s1 = s.trim().split(" ");
            for (String str : s1) {
                List<Integer> currentWordIndexesList = peopleMap.get(str.toLowerCase());
                if (currentWordIndexesList == null) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(i);
                    peopleMap.put(str.toLowerCase(), integerList);
                } else {
                    currentWordIndexesList.add(i);
                    peopleMap.put(str.toLowerCase(), currentWordIndexesList);
                }
            }
            i++;
        }


        switch (STRATEGY) {
            case "ALL":

                for (String value : query) {
                    if (peopleMap.containsKey(value)) {
                        index.addAll(peopleMap.get(value));
                    }
                }
                Set<String> sR = new HashSet<>();
                for (Integer integer : index) {
                    sR.add(allList.get(integer));
                }
                for (String s : sR) {
                    if ((Arrays.asList(s.split(" "))).containsAll(Arrays.asList(query))) {
                        searchResult.add(s);
                    }
                }

                if (!searchResult.isEmpty()) {
                    System.out.printf("%d persons found: \n", searchResult.size());
                    searchResult.forEach(System.out::println);
                } else {
                    System.out.println("No matching people found!");
                }
                break;

            case "ANY":

                for (String value : query) {
                    if (peopleMap.containsKey(value)) {
                        index.addAll(peopleMap.get(value));
                    }
                }
                for (Integer integer : index) {
                    searchResult.add(allList.get(integer));
                }
                if (!searchResult.isEmpty()) {
                    System.out.printf("%d persons found: \n", searchResult.size());
                    searchResult.forEach(System.out::println);
                } else {
                    System.out.println("No matching people found!");
                }
                break;

            case "NONE":

                for (String value : query) {
                    if (peopleMap.containsKey(value)) {
                        index.addAll(peopleMap.get(value));
                    }
                }
                for (Integer integer : index) {
                    searchResult.add(allList.get(integer));
                }
                allList.removeAll(searchResult);
                if (!allList.isEmpty()) {
                    System.out.printf("%d persons found: \n", allList.size());
                    allList.forEach(System.out::println);
                } else {
                    System.out.println("No matching people found!");
                }
                break;
        }
    }

    public static void printAll(List<String> allList) {
        System.out.println("=== List of people==");
        allList.forEach(System.out::println);
    }

    public static String enterStringData(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

}


