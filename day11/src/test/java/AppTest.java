import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public final class AppTest {

    private final App app = new App();

    @Test
    public void test1() {
        assertEquals(3, app.part1(List.of("ne", "ne", "ne")));
    }

    @Test
    public void test2() {
        assertEquals(0, app.part1(List.of("ne", "ne", "sw", "sw")));
    }

    @Test
    public void test3() {
        assertEquals(2, app.part1(List.of("ne", "ne", "s", "s")));
    }

    @Test
    public void test4() {
        assertEquals(3, app.part1(List.of("se", "sw", "se", "sw", "sw")));
    }
}
