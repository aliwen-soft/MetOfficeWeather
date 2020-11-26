package training.metofficeweather;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class WeatherCLI {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        if (MetAPIReader.DEFAULT_LOCATIONS == null)
            return;
        Scanner scanner = new Scanner(System.in);

        printWelcome();
        printHorizontalRule();
        while (true) {
            printHighlightedWeatherStations();
            String commandInput = scanner.nextLine();
            String command = removeExcessSpace(commandInput);

            if (isNumberCommand(command)) {
                runNumberCommand(command);
            } else if (!isExitCommand(command)) {
                runStationNameCommand(command);
            }
            if (isExitCommand(command) || shouldDiscontinueCommandRequests(scanner))
                break;
        }
        System.out.println("Goodbye!");
    }

    private static void printWelcome() {
        System.out.println("\nWelcome to MetOfficeWeather v1.0!");
    }

    private static void printHorizontalRule() {
        System.out.println("--------------------");
    }

    private static void printHighlightedWeatherStations() {
        System.out.println("Enter the name of a weather station you would like the forecast for!");
        System.out.println("Here are some weather stations to choose from if you need inspiration:");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%9s %s\n", "[" + MetAPIReader.DEFAULT_LOCATIONS.get(i).getId() + "]",
                    MetAPIReader.DEFAULT_LOCATIONS.get(i).getName());
        }
        System.out.println();
        System.out.println("Enter an ID or name (from this list or not), or choose \"exit\":");
    }

    private static String removeExcessSpace(String string) {
        if (string == null)
            return null;
        return string.trim().replaceAll("\\s+", " ");
    }

    private static boolean isNumberCommand(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isExitCommand(String command) {
        return command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit");
    }

    private static boolean shouldDiscontinueCommandRequests(Scanner scanner) {
        printHorizontalRule();
        System.out.println("Would you like to run another command? (y/n)");
        String response = scanner.nextLine();
        System.out.println();
        return removeExcessSpace(response).toLowerCase().startsWith("n");
    }

    private static void runNumberCommand(String command) {
        try {
            MetAPIReader.printWeatherFromId(command);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON information.");
        } catch (InvalidIDException invalidIDException) {
            System.out.println(invalidIDException.getMessage());
        }
    }

    private static void runStationNameCommand(String command) {
        try {
            MetAPIReader.printWeatherFromName(command);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON information.");
        } catch (InvalidIDException | InvalidLocNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
