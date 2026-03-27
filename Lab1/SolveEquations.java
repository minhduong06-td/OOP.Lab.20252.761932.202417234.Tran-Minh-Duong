//Bài 2.6
import java.util.Scanner;

public class SolveEquations {
    static Scanner scanner = new Scanner(System.in);

    // 1. Linear equation: ax + b = 0
    static void linearEquation(double a, double b) {
        if (a == 0) {
            if (b == 0) System.out.println("Infinite solutions.");
            else System.out.println("No solution.");
        } else {
            System.out.println("x = " + (-b / a));
        }
    }

    static void linearEquation() {
        System.out.print("a: ");
        double a = Double.parseDouble(scanner.nextLine());
        System.out.print("b: ");
        double b = Double.parseDouble(scanner.nextLine());
        linearEquation(a, b);
    }

    // 2. Linear system: a11*x1 + a12*x2 = b1 | a21*x1 + a22*x2 = b2
    static void linearSystem() {
        System.out.print("a11: "); double a11 = Double.parseDouble(scanner.nextLine());
        System.out.print("a12: "); double a12 = Double.parseDouble(scanner.nextLine());
        System.out.print("b1 : "); double b1  = Double.parseDouble(scanner.nextLine());
        System.out.print("a21: "); double a21 = Double.parseDouble(scanner.nextLine());
        System.out.print("a22: "); double a22 = Double.parseDouble(scanner.nextLine());
        System.out.print("b2 : "); double b2  = Double.parseDouble(scanner.nextLine());

        double D  = a11 * a22 - a21 * a12;
        double D1 = b1  * a22 - b2  * a12;
        double D2 = a11 * b2  - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0) System.out.println("Infinite solutions.");
            else System.out.println("No solution.");
        } else {
            System.out.println("x1 = " + (D1 / D));
            System.out.println("x2 = " + (D2 / D));
        }
    }

    // 3. Quadratic equation: ax^2 + bx + c = 0
    static void quadraticEquation() {
        System.out.print("a: ");
        double a = Double.parseDouble(scanner.nextLine());
        System.out.print("b: ");
        double b = Double.parseDouble(scanner.nextLine());
        System.out.print("c: ");
        double c = Double.parseDouble(scanner.nextLine());

        if (a == 0) {
            linearEquation(b, c);
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0)       System.out.println("No Solution.");
        else if (delta == 0) System.out.println("x = " + (-b / (2 * a)));
        else {
            System.out.println("x1 = " + ((-b + Math.sqrt(delta)) / (2 * a)));
            System.out.println("x2 = " + ((-b - Math.sqrt(delta)) / (2 * a)));
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Linear equation");
        System.out.println("2. Linear system");
        System.out.println("3. Quadratic equation");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> linearEquation();
            case 2 -> linearSystem();
            case 3 -> quadraticEquation();
        }

        scanner.close();
    }
}