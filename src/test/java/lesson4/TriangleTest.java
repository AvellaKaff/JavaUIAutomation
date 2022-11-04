package lesson4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static lesson4.AreaOfTriangle.areaOfTriangle;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {

    @Test
    public void areaTriangleTest() throws Exception {
        assertEquals(6, areaOfTriangle(3, 4, 5), 0.001);
    }

    @ParameterizedTest
    @CsvSource({"24, 6 , 8, 10", "6, 3, 4, 5", "40.81, 10, 10, 15"})
    public void areaCsvTest(double result, int a, int b, int c) throws Exception {
        assertEquals(result, areaOfTriangle(a, b, c), 0.01);
    }

    @Test
    void groupAssertions() {
        double[] result = {0.96, 6, 40.81, 24};
        assertAll("result",
                () -> assertEquals(result[0], areaOfTriangle(1, 2, 2), 0.01),
                () -> assertEquals(result[1], areaOfTriangle(3, 4, 5), 0.01),
                () -> assertEquals(result[2], areaOfTriangle(10, 10, 15), 0.01),
                () -> assertEquals(result[3], areaOfTriangle(6, 8, 10), 0.01)
        );
    }


    @Test
    void negativeValueException() {
        try {
            areaOfTriangle(-1, 2, 2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "side of the triangle <= 0");
        }
    }

    @Test
    void triangleDoesNotExistException() {
        try {
            areaOfTriangle(5, 2, 2);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "the triangle does not exist");
        }
    }

}
