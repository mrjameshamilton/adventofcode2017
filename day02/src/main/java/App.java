import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static int[][] fileToGrid(String filename) throws IOException {
        InputStream inputStream = App.class.getResourceAsStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<int[]> rows = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split("\\t");
            int[] row = new int[values.length];
            for (int i = 0; i < row.length; i++) {
                row[i] = Integer.parseInt(values[i]);
            }
            rows.add(row);
        }
        int[][] grid = new int[rows.size()][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = rows.get(i);
        }
        return grid;
    }

    public static void main(String[] args) throws IOException {
        System.out.println((new App()).part1(fileToGrid("input.txt")));
        System.out.println((new App()).part2(fileToGrid("input.txt")));
    }

    /**
     * For each row, determine the difference between the largest value and the smallest value;
     * the checksum is the sum of all of these differences.
     *
     * @param grid
     * @return
     */
    public int part1(int[][] grid) {
        int checksum = 0;
        for (int r = 0; r < grid.length; r++) {
            int[] row = grid[r];
            int min = row[0], max = row[0];
            for (int c = 0; c < row.length; c++) {
                if (row[c] > max) max = row[c];
                if (row[c] < min) min = row[c];
            }
            checksum += max - min;
        }

        return checksum;
    }

    /**
     * Find the only two numbers in each row where one evenly divides the other
     * - that is, where the result of the division operation is a whole number.
     * They would like you to find those numbers on each line, divide them,
     * and add up each line's result.
     *
     * @param grid
     * @return
     */
    public int part2(int[][] grid) {
        int checksum = 0;
        a: for (int r = 0; r < grid.length; r++) {
            int[] row = grid[r];

            for (int i = 0; i < row.length; i++) {
                for (int j = i+1; j < row.length; j++) {
                    if (row[i] > row[j] && row[i] % row[j] == 0) {
                        checksum += row[i] / row[j];
                        continue a;
                    } else if (row[j] > row[i] && row[j] % row[i] == 0) {
                        checksum += row[j] / row[i];
                        continue a;
                    }
                }
            }
        }

        return checksum;
    }
}
