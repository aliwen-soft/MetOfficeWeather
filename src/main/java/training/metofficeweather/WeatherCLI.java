package training.metofficeweather;

import java.util.Scanner;

public class WeatherCLI {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        printWelcome();
        printHorizontalRule();
        while (true) {
            printHighlightedWeatherStations();
            String commandInput = scanner.nextLine();
            String command = removeExcessSpace(commandInput);

            if (isNumberCommand(command))
                runNumberCommand(command);
            else if (!isPaginateCommand(command) && !isExitCommand(command))
                runStationNameCommand(command);
            else
                while (isPaginateCommand(command))
                    runPaginateCommand(command);
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
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isPaginateCommand(String command) {
        return command.equalsIgnoreCase("next") ||
                command.equalsIgnoreCase("prev") ||
                command.equalsIgnoreCase("previous") ||
                command.equalsIgnoreCase("back");
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
        System.out.println("get weather by station index on printed list");
    }

    private static void runStationNameCommand(String command) {
        System.out.println("get weather for station name anywhere in list");
    }

    private static void runPaginateCommand(String command) {
        System.out.println("get previous/next list of 10 stations");
    }
}
