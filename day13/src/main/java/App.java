import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {

    /**
     * Given the input file, create an array of integers representing the firewall layers.
     *
     * @param filename The filename in the resources folder.
     * @return the firewall array (a -1 entry means that there are no scanners in that layer)
     * @throws URISyntaxException Exception thrown if file name is invalid
     * @throws IOException Exception thrown if file could not be opened
     */
    public static int[] fileToList(final String filename) throws URISyntaxException, IOException {
        final Path path = Paths.get(App.class.getResource(filename).toURI());
        final List<String> lines = Files.readAllLines(path);
        final int size = Integer.valueOf(lines.get(lines.size() - 1).split(":")[0].trim());
        final int[] result = new int[size + 1];
        int lineNumber = 0;
        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (Integer.valueOf(line.split(":")[0].trim()) == lineNumber) {
                result[lineNumber] = Integer.valueOf(line.split(":")[1].trim());
            } else {
                i--;
                result[lineNumber] = -1;
            }
            lineNumber++;
        }
        return result;
    }

    /**
     * What is the severity of traversing it immediately?
     *
     * @param firewall the firewall
     * @return the severity
     */
    public int part1(final int[] firewall) {
        return solution(firewall, 0).severity;
    }

    /**
     * How long should you delay before entering so that
     * you are not caught by the scanners?
     *
     * @param firewall the firewall
     * @return the number of picoseconds that you should wait
     */
    public int part2(final int[] firewall) {
        int waitTime = 0;
        while (solution(firewall, waitTime).isCaught) waitTime++;
        return waitTime;
    }

    private Answer solution(final int[] firewall, final int waitTime) {
        int severity = 0;
        boolean caught = false;
        for (int level = 0; level < firewall.length; level++) {
            final int depth = firewall[level];
            if (depth != -1 && (waitTime + level) % ((depth - 1) * 2) == 0) {
                caught = true;
                severity += level * depth;
            }
        }
        return new Answer(caught, severity);
    }

    private static final class Answer {
        final boolean isCaught;
        final int severity;

        Answer(final boolean isCaught, final int severity) {
            this.isCaught = isCaught;
            this.severity = severity;
        }

        @Override
        public String toString() {
            return this.isCaught ? "Caught with severity " + severity : "Not caught with severity " + severity;
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        App app = new App();
        int[] input = App.fileToList("input.txt");
        System.out.println(app.part1(input));
        System.out.println(app.part2(input));
    }
}
