package pl.coderslab;


import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        displayOptions();
    }
    public static String[][] listOfTasks() {
        Path tasks = Paths.get("tasks.csv");
        String[][] listOfTasks = new String[0][];
        String line;
        try (Scanner scanner = new Scanner(tasks)) {
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                listOfTasks = Arrays.copyOf(listOfTasks, listOfTasks.length + 1);
                listOfTasks[listOfTasks.length - 1] = line.split(",");
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
        return listOfTasks;
    }
    public static void displayOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        String[] options = {"add", "remove", "list", "exit"};
        System.out.print(ConsoleColors.RESET);
        for(String option : options) {
            System.out.println(option);
        }
        selectOption(options);
    }
    public static void selectOption(String[] options) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        switch (input) {
            case "add":
//                addTask();
                break;
            case "remove":
//                removeTask();
                break;
            case "list":
                displayTasks(input);
                break;
            case "exit":
//                exitApp();
                break;
            default:
                System.err.println("Please select a correct option");
        }
    }
    public static void displayTasks(String input) {
        String[][] tasks = listOfTasks();
        StringBuilder builder = new StringBuilder();
        builder.append(input).append("\n");
        int counter = 0;
        for(String[] task : tasks) {
            builder.append(counter).append(" : ").append(StringUtils.join(task)).append("\n");
        }
        System.out.println(builder.toString());
        displayOptions();
    }
}
