package SearchingEngine.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FilesUtil {
    public static List<String> getListFromFile(String[] args) {
        List<String> arguments = List.of(args);
        String path = arguments.contains("--data") ? arguments.get(arguments.indexOf("--data") + 1) : "";
        final File file = new File(path);
        String[] line;
        List<String> list = new ArrayList<>();
        try (final Scanner scan = new Scanner(file)) {
            while (scan.hasNext()) {
                line = scan.nextLine().split("\n");
                Collections.addAll(list, line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
