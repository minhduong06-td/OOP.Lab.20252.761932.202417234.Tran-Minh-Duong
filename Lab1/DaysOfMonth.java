import java.util.Scanner;

public class DaysOfMonth {
    static Scanner scanner = new Scanner(System.in);

    static int getMonth() {
        while (true) {
            System.out.print("Enter month: ");
            String input = scanner.nextLine().trim().toLowerCase().replace(".", "");
            String[] names = {"january","february","march","april","may","june","july","august","september","october","november","december"};

            try { int m = Integer.parseInt(input); if (m >= 1 && m <= 12) return m; }
            catch (NumberFormatException ignored) {}

            for (int i = 0; i < 12; i++)
                if (names[i].startsWith(input) || names[i].equals(input)) return i + 1;

            System.out.println("Invalid month.");
        }
    }

    static int getYear() {
        while (true) {
            System.out.print("Enter year: ");
            try { int y = Integer.parseInt(scanner.nextLine().trim()); if (y >= 0) return y; }
            catch (NumberFormatException ignored) {}
            System.out.println("Invalid year. Try again.");
        }
    }

    static int getDays(int month, int year) {
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> leap ? 29 : 28;
            default -> -1;
        };
    }

    public static void main(String[] args) {
        System.out.println("Days: " + getDays(getMonth(), getYear()));
        scanner.close();
    }
}