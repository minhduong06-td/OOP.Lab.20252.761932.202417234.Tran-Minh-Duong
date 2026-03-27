import java.util.Arrays;
import java.util.Scanner;

public class ArraySort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number: ");
        String[] tokens = scanner.nextLine().trim().split("\\s+");
        int n = tokens.length;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(tokens[i]);

        Arrays.sort(arr);

        int sum = 0;
        for (int x : arr) sum += x;

        System.out.println("Sorted : " + Arrays.toString(arr));
        System.out.println("Sum : " + sum);
        System.out.println("Average: " + (double) sum / n);

        scanner.close();
    }
}