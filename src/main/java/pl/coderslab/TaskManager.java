package pl.coderslab;


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
        try {
            listOfTasks();
        } catch (FileNotFoundException e) {
            System.err.println("Error while loading list of tasks from the file");
        }
    }
    public static String[][] listOfTasks() throws FileNotFoundException {
        Path tasks = Paths.get("tasks.csv");
        if(!Files.exists(tasks)) {
            throw new FileNotFoundException("File " + tasks + " is missing");
        }
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
}
