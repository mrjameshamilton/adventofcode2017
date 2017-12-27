import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class App {

    /**
     * On each move increment the current instruction by 1.
     *
     * @param instructions
     * @return
     */
    public int part1(final List<Integer> instructions) {
        return solution(instructions, current -> current + 1);
    }

    /**
     * On each move if the current instruction is >= 3 decrement by 1
     * else increment by 1.
     *
     * @param instructions
     * @return
     */
    public int part2(final List<Integer> instructions) {
        return solution(instructions, current -> current >= 3 ? current - 1 : current + 1 );
    }

    /**
     * Move among a list of instructions; each instruction in the list
     * is the offset to move from the current position to the next.
     * When moving from one location to another, the update function is
     * applied to update the value in that location. The process stops
     * when the location moves to a location outside of the list.
     *
     * @param instructions the instructions
     * @param update the update function applied when moving
     * @return the number of steps taken to move outside of the list
     */
    private int solution(final List<Integer> instructions, final Function<Integer, Integer> update) {
        final List<Integer> copy = new ArrayList<>(instructions);
        int steps = 0;
        int current = 0;
        while (true) {
            try {
                int next = current + copy.get(current);
                copy.set(current, update.apply(copy.get(current)));
                current = next;
                steps++;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return steps;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = App.class.getResourceAsStream("input.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Integer> instructions = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            instructions.add(Integer.valueOf(line));
        }
        inputStream.close();
        App app = new App();
        System.out.println(app.part1(instructions));
        System.out.println(app.part2(instructions));
    }
}
