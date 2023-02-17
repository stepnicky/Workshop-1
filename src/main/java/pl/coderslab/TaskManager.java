package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;
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
        String[][] tasks = listOfTasks();
        selectOption(tasks);
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
    }
    public static void selectOption(String[][] tasks) {
        displayOptions();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        switch (input) {
            case "add":
                addTask(tasks, input);
                break;
            case "remove":
                removeTask(tasks, input);
                break;
            case "list":
                displayTasks(tasks, input);
                break;
            case "exit":
//                exitApp(tasks, input);
                break;
            default:
                System.err.println("Please select a correct option");
        }
    }
    public static void displayTasks(String[][] tasks, String input) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append(input).append("\n");
        int counter = 0;
        for(String[] task : tasks) {
            builder.append(counter).append(" : ").append(StringUtils.join(task)).append("\n");
            counter++;
        }
        System.out.println(builder.toString());
        selectOption(tasks);
    }
    public static void addTask(String[][] tasks, String input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + input + "\n");
        System.out.println("Please add task description");
        String description = " " + scanner.nextLine();
        System.out.println("Please add task due date");
        String date = " " + scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String importance = " " + scanner.nextLine();
        String[] newTask = {description, date, importance};
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
        System.out.println("Task was successfully added");
        selectOption(tasks);
    }
    public static void removeTask(String[][] tasks, String input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + input + "\n");

        int indexOfTask;
        do {
            System.out.println("Please select number to remove");
            while(!scanner.hasNextInt()) {
                System.err.println("Please enter correct value");
                scanner.next();
            }
            indexOfTask = scanner.nextInt();
            if(indexOfTask < 0) {
                System.err.println("Please enter non-negative number");
            }
        } while (indexOfTask < 0);

        tasks = ArrayUtils.remove(tasks, indexOfTask);
        System.out.println("Value was successfully deleted");
        selectOption(tasks);
    }

}
