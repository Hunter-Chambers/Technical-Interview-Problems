import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/*
 ************************************************************
 * Name: Hunter Chambers
 * ID:   hc998658
 *
 * Version: 11/11/2019
 ************************************************************ 
*/

public class DroneQuestion {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------");

        // create Scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        int rows, cols;

        try {
            System.out.printf("Input number of columns: ");

            // first input from user will be the
            // number of columns in the matrix
            cols = sc.nextInt();

            if (cols <= 0) throw new IllegalArgumentException("Input must be positive!");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Must enter a whole number!");
        }

        try {
            System.out.printf("Input number of rows:    ");

            // second input from user will be
            // the number of rows in the matrix
            rows = sc.nextInt();

            if (rows <= 0) throw new IllegalArgumentException("Input must be positive!");
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Must enter a whole number!");
        }

        System.out.println();

        // make sure to close scanner object
        sc.close();

        // matrix will be filled with random values
        Random rand = new Random();

        int[][] mat = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                // generate a random value between 0 and 9 (inclusive)
                // and insert it into the matrix
                mat[i][j] = rand.nextInt(10);
        }

        // we want to ensure that the starting and ending
        // points are not small enough to throw off our
        // calculations later
        mat[0][0] = (rand.nextInt(4) + 6); // start becomes a random number between 6 and 9, inclusive
        mat[rows - 1][cols - 1] = (rand.nextInt(4) + 6); // end becomes a random number between 6 and 9, inclusive

        // display matrix
        System.out.println("Matrix is:");
        for (int[] i : mat) {
            for (int j : i)
                System.out.printf("%d ", j);
            System.out.println();
        }
        System.out.println();

        int numOfPaths = numberOfPaths(rows, cols);
        int maxLengthOfPath = (rows + cols - 1);
        int[][] allPaths = new int[numOfPaths][maxLengthOfPath];

        // this function will fill allPaths with every path through the matrix
        getPaths(mat, rows, cols, 0, 0, new int[maxLengthOfPath], 0, allPaths, 0);

        // display all of the paths possible
        System.out.printf("Path(s) are (%d total):\n", numOfPaths);
        for (int i = 0; i < numOfPaths; i++) {
            for (int j = 0; j < (maxLengthOfPath - 1); j++)
                System.out.printf("%d -> ", allPaths[i][j]);
            System.out.println(allPaths[numOfPaths - 1][maxLengthOfPath - 1]);
        }
        System.out.println();

        // mins will hold all of the minimum values from each path
        int[] mins = new int[numOfPaths];

        // sequential search for each minimum
        for (int i = 0; i < numOfPaths; i++) {
            int currentMin = allPaths[i][0];
            for (int j = 1; j < maxLengthOfPath; j++)
                if (allPaths[i][j] < currentMin) currentMin = allPaths[i][j];
            mins[i] = currentMin;
        }

        // display the minimums of each path
        System.out.println("Minimum from each path is:");
        for (int i = 0; i < (numOfPaths - 1); i++)
            System.out.printf("%d, ", mins[i]);
        System.out.printf("%d\n\n", mins[numOfPaths - 1]);

        // sequential search to find the max within mins
        int max = mins[0];
        for (int i = 1; i < numOfPaths; i++)
            if (mins[i] > max) max = mins[i];

        // display max value from the minimums
        System.out.printf("Maximum value out of the minimum(s) is: %d\n", max);

        System.out.println("---------------------------------------------------");
    }

    private static int numberOfPaths(int m, int n) {
        // If either given row number is first or
        // given column number is first
        if (m == 1 || n == 1)
            return 1;
 
        return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
    }

    private static int getPaths(int[][] mat, int m, int n,
                                 int i, int j, int[] path, int idx,
                                 int[][] allPaths, int z) {
        path[idx] = mat[i][j];
 
        // Reached the bottom of the matrix so we are left with
        // only option to move right
        if (i == m - 1) {
            for (int k = j + 1; k < n; k++)
                path[idx + k - j] = mat[i][k];
            System.arraycopy(path, 0, allPaths[z], 0, (m + n - 1));
            return z + 1;
        }
 
        // Reached the right side of the matrix so we are left with
        // only the downward movement
        if (j == n - 1) {
            for (int k = i + 1; k < m; k++)
                path[idx + k - i] = mat[k][j];
            System.arraycopy(path, 0, allPaths[z], 0, (m + n - 1));
            return z + 1;
        }

        // Print all the paths that are possible after moving down 
        z = getPaths(mat, m, n, i + 1, j, path, idx + 1, allPaths, z);

        // Print all the paths that are possible after moving right 
        z = getPaths(mat, m, n, i, j + 1, path, idx + 1, allPaths, z);

        return z;
    }

}
