import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private App app = new App();

    @Test
    public void testPart1() {
        assertEquals(18, app.part1(new int[][]{new int[]{5, 1, 9, 5}, new int[]{7, 5, 3}, new int[]{2, 4, 6, 8}}));
    }

    @Test
    public void testPart2() {
        assertEquals(9, app.part2(new int[][] {new int[] {5, 9, 2, 8}, new int[]{9, 4, 7, 3}, new int[] {3, 8, 6, 5}}));
    }
}
