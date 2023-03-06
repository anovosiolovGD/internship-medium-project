package SearchingEngine.utils;
import java.util.Scanner;
public class ConsoleUtil {
    public static Scanner consoleScan() {
        return new Scanner(System.in);
    }

    public static void showMenu() {
        System.out.println("""
                === Menu ===\s
                1. Find a person
                2. Print all people
                0. Exit""");
    }
    public static void printMessage(String message) {
        System.out.println(message);
    }
}

