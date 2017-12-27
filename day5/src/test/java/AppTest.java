import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppTest {
    private final App app = new App();

    @Test public void testPart1() {
        assertEquals(5, app.part1(List.of(0, 3, 0, 1, -3)));
    }

    @Test public void testPart2() {
        assertEquals(10, app.part2(List.of(0, 3, 0, 1, -3)));
    }
}
