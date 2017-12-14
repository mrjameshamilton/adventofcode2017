import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        InputStream inputStream = App.class.getResourceAsStream("input.txt");
        String input = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(""));
        App app = new App();
        System.out.println(app.part1(input));
        System.out.println(app.part2(input));
    }

    public int solution(String s, int lookAhead) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int current = Character.getNumericValue(s.charAt(i));
            int next = Character.getNumericValue(s.charAt((i + lookAhead) % s.length()));
            if (current == next) sum += current;
        }
        return sum;
    }

    /**
     * Find the sum of all digits that match the next digit in the list.
     * The list is circular, so the digit after the last digit is the first digit in the list.
     *
     * @param s
     * @return
     */
    public int part1(String s) {
        return this.solution(s, 1);
    }

    /**
     * Now, instead of considering the next digit, it wants you to consider the digit halfway
     * around the circular list. That is, if your list contains 10 items, only include a digit
     * in your sum if the digit 10/2 = 5 steps forward matches it.
     *
     * Fortunately, your list has an even number of elements.
     *
     * @param s
     * @return
     */
    public int part2(String s) {
        if (s.length() % 2 != 0) {
            throw new InvalidParameterException("Input string must contain an even number of integers");
        }
        return this.solution(s, s.length() / 2);
    }
}
