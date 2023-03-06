package SearchingEngine;

import SearchingEngine.utils.FilesUtil;

import java.util.ArrayList;
import java.util.List;

import static SearchingEngine.strategy.impl.StrategySelect.selectingSearchingStrategy;
import static SearchingEngine.utils.ConsoleUtil.*;

public class Main {
    static List<String> allPeopleList = new ArrayList<>();
    public static void main(String[] args) {

        allPeopleList = FilesUtil.getListFromFile(args);
        int option = -1;
        if (allPeopleList.isEmpty()) option = 0;
        while(option != 0) {
            showMenu();
            option = consoleScan().nextInt();
            choseOption(option);
        }
    }
    public static void choseOption(int option) {
        switch (option) {
            case 1 -> {
                printMessage("Select a matching strategy: ALL, ANY, NONE");
                String strategy = consoleScan().nextLine();
                printMessage("Enter a name or email to search all suitable people.");
                String queryWord = consoleScan().nextLine();
                selectingSearchingStrategy(allPeopleList, strategy, queryWord);
            }
            case 2 -> {
                printMessage("=== List of people==");
                allPeopleList.forEach(System.out::println);
            }
            case 0 -> System.out.println("Bye!");
            default -> System.out.println("Incorrect option! Try again.");
        }
    }
}
