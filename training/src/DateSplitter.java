import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateSplitter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a date
        System.out.print("Enter a date (YYYY-MM-DD): ");
        String inputDate = scanner.nextLine();

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Parse the input date string into a LocalDate object
            LocalDate date = LocalDate.parse(inputDate, formatter);

            // Extract day, month, and year
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();

            // Get full date-time string
            String fullDateTime = date.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Print the results
            System.out.println("Day: " + day);
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
            System.out.println("Full Date-Time: " + fullDateTime);
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            System.out.println("Error: Invalid date format. Please enter date in YYYY-MM-DD format.");
        }

        scanner.close();
    }
}