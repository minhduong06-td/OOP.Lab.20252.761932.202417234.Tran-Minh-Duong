//Bài 2.2.5
import java.util.Scanner;

public class CalculateTWoNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("First number: ");
        double num1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Second number: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        System.out.println("Sum: " + (num1 + num2));
        System.out.println("Difference: " + (num1 - num2));
        System.out.println("Product: " + (num1 * num2));

        if (num2 == 0)
            System.out.println("Cannot divide by 0");
        else
            System.out.println("Quotient: " + (num1 / num2));

        scanner.close();
    }
}