import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    private App app = new App();

    @Test
    public void testPart1_1() {
        assertEquals("1122 should produce 3", 3, app.part1("1122"));
    }

    @Test
    public void testPart1_2() {
        assertEquals("1111 should produce 4", 4, app.part1("1111"));
    }

    @Test
    public void testPart1_3() {
        assertEquals("1234 should produce 0", 0, app.part1("1234"));
    }

    @Test
    public void testPart1_4() {
        assertEquals("91212129 should produce 9", 9, app.part1("91212129"));
    }

    @Test
    public void testPart2_1() {
        assertEquals("1212 should produce 6", 6, app.part2("1212"));
    }

    @Test
    public void testPart2_2() {
        assertEquals("1221 should produce 0", 0, app.part2("1221"));
    }

    @Test
    public void testPart2_3() {
        assertEquals("123425 should produce 4", 4, app.part2("123425"));
    }

    @Test
    public void testPart2_4() {
        assertEquals("123123 should produce 12", 12, app.part2("123123"));
    }

    @Test
    public void testPart2_5() {
        assertEquals("12131415 should produce 4", 4, app.part2("12131415"));
    }
}
