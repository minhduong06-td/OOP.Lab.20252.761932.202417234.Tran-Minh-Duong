import java.util.Scanner;

public class MatrixAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Rows: "); int rows = Integer.parseInt(scanner.nextLine());
        System.out.print("Cols: "); int cols = Integer.parseInt(scanner.nextLine());

        int[][][] m = new int[2][rows][cols];
        for (int k = 0; k < 2; k++) {
            System.out.println("Matrix " + (k == 0 ? "A" : "B") + ":");
            for (int i = 0; i < rows; i++) {
                String[] t = scanner.nextLine().trim().split("\\s+");
                for (int j = 0; j < cols; j++) m[k][i][j] = Integer.parseInt(t[j]);
            }
        }

        System.out.println("A + B =");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) System.out.printf("%5d", m[0][i][j] + m[1][i][j]);
            System.out.println();
        }

        scanner.close();
    }
}