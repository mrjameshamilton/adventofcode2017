import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.abs;

public final class App {

    private final class Answer {
        final int distance;
        final int maxDistance;

        Answer(final int distance, final int maxDistance) {
            this.distance = distance;
            this.maxDistance = maxDistance;
        }

        @Override
        public String toString() {
            return "distance: " + distance + ", maxDistance: " + maxDistance;
        }
    }

    /**
     * After moving according to the list of moves, what is the distance
     * from the starting position?
     *
     * @param moves list of moves: [n|ne|se|s|sw|nw]
     * @return the distance
     */
    public int part1(final List<String> moves) {
        return solution(moves).distance;
    }

    /**
     * After moving according to the list of moves, what is the maximum
     * distance traveled away from the starting position?
     *
     * @param moves list of moves: [n|ne|se|s|sw|nw]
     * @return the maximum distance
     */
    public int part2(final List<String> moves) {
        return solution(moves).maxDistance;
    }

    private Answer solution(final List<String> moves) {
        //cube co-ordinates (each hexagon is a cube in 3D space): https://www.redblobgames.com/grids/hexagons/
        int x = 0, y = 0, z = 0, maxDistance = 0;

        for (final String move : moves) {
            switch (move) {
                case "n" : y++; z--; break;
                case "ne": x++; z--; break;
                case "se": y--; x++; break;
                case "s" : y--; z++; break;
                case "sw": x--; z++; break;
                case "nw": y++; x--; break;
                default:
                    throw new IllegalArgumentException("unknown move: " + move);
            }

            final int maxXYZ = max(abs(x), max(abs(y), abs(z)));
            if (maxXYZ > maxDistance) {
                maxDistance = maxXYZ;
            }
        }

        return new Answer(max(abs(x), max(abs(y), abs(z))), maxDistance);
    }

    public static void main(final String[] args) {
        try {
            final String input = Files.readAllLines(Paths.get(App.class.getResource("input.txt").toURI())).stream().collect(Collectors.joining());
            System.out.println((new App()).solution(List.of(input.split(","))));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Could not read input.txt file");
            System.exit(-1);
        }
    }
}
