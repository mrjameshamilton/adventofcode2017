import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class AppTest {

    private final App app = new App();
    private static int[] input;

    @BeforeClass
    public static void setup() throws IOException, URISyntaxException {
       input = App.fileToList("input.txt");
    }

    @Test
    public void test_part1() {
        Assert.assertEquals(24, app.part1(input));
    }

    @Test
    public void test_part2() {
        Assert.assertEquals(10, app.part2(input));
    }

    @Test
    public void testInput() {
        Assert.assertArrayEquals(new int[] { 3, 2, -1, -1, 4, -1, 4 }, input);
    }
}
