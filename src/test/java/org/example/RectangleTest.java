package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RectangleTest {
    private final Rectangle rectangle = new Rectangle();
    @BeforeAll
    public void setup() { System.out.println("@BeforeAll executed");}
    @AfterAll
    public void tear() {
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }

    // ✅ Позитивні тести
    @Test
    @DisplayName("Default Constructor should initialize width and height to 0, angle to 90")
    void testDefaultConstructor() {
        Rectangle rect = new Rectangle();
        assertEquals(1.0, rect.getWidth());
        assertEquals(1.0, rect.getHeight());
        assertEquals(90.0, rect.getAngle());
    }

    @Test
    @DisplayName("Parameterized Constructor should initialize width and height to provided values")
    void testParameterizedConstructor() {
        Rectangle rect = new Rectangle(5.0, 10.0);
        assertEquals(5.0, rect.getWidth());
        assertEquals(10.0, rect.getHeight());
    }

    @Test
    @DisplayName("setWidth() should correctly update the width")
    void testSetWidth() {
        Rectangle rect = new Rectangle();
        rect.setWidth(7.5);
        assertEquals(7.5, rect.getWidth());
    }

    @Test
    @DisplayName("setHeight() should correctly update the height")
    void testSetHeight() {
        Rectangle rect = new Rectangle();
        rect.setHeight(4.2);
        assertEquals(4.2, rect.getHeight());
    }

    @Test
    @DisplayName("calculateArea() should return correct area for valid width and height")
    void testCalculateArea() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rect.calculateArea());
    }
    @Test
    @DisplayName("calculateArea() should correctly handle floating-point calculations")
    void testFloatingPointCalculations() {
        Rectangle rect = new Rectangle(3.333, 4.444);
        double expectedArea = 3.333 * 4.444;
        assertEquals(expectedArea, rect.calculateArea(), 1e-3);
    }
    @Test
    @DisplayName("calculateArea() should correctly handle large dimensions")
    void testLargeDimensions() {
        Rectangle rect = new Rectangle(1e8, 1e8);
        double expectedArea = 1e8 * 1e8;
        assertEquals(expectedArea, rect.calculateArea());
    }
    @Test
    @DisplayName("calculateArea() should correctly handle small dimensions")
    void testSmallDimensions() {
        Rectangle rect = new Rectangle(0.0001, 0.0002);
        double expectedArea = 0.0001 * 0.0002;
        assertEquals(expectedArea, rect.calculateArea(), 1e-8);
    }
    @Test
    @DisplayName("calculatePerimeter() should return correct perimeter")
    void testCalculatePerimeter() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(14.0, rect.calculatePerimeter());
    }
    @Test
    @DisplayName("getDiagonal() should return the correct diagonal length")
    void testGetDiagonal() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(5.0, rect.getDiagonal(), 0.0001); // Точність 4 знаки після коми
    }
    @ParameterizedTest
    @DisplayName("getDiagonal() should return the correct diagonal length for different width and height values")
    @CsvSource({
            "3.0, 4.0, 5.0",
            "5.0, 12.0, 13.0",
            "8.0, 15.0, 17.0",
            "7.0, 24.0, 25.0"
    })
    void testGetDiagonal(double width, double height, double expectedDiagonal) {
        Rectangle rect = new Rectangle(width, height);
        assertEquals(expectedDiagonal, rect.getDiagonal(), 0.0001);
    }
    @ParameterizedTest
    @DisplayName("calculateArea() should return correct area for different width and height values")
    @CsvSource({
            "5.0, 10.0, 50.0",
            "7.0, 3.0, 21.0",
            "1.0, 10.0, 10.0",
            "6.0, 6.0, 36.0"
    })
    public void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculateArea());
    }
    @ParameterizedTest
    @DisplayName("calculatePerimeter() should return correct perimeter for different width and height values")
    @CsvSource({
            "5.0, 10.0, 30.0",
            "7.0, 3.0, 20.0",
            "1.0, 10.0, 22.0",
            "6.0, 6.0, 24.0"
    })
    public void testCalculatePerimeterParameterized(double width, double height, double expectedPerimeter) {
        Rectangle rect = new Rectangle(width, height);
        assertEquals(expectedPerimeter, rect.calculatePerimeter());
    }

        // ❌ Негативні тести
        @Test
        @DisplayName("Constructor should throw IllegalArgumentException when width is negative")
        void testNegativeWidthInConstructor() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(-5.0, 10.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("Constructor should throw IllegalArgumentException when height is negative")
        void testNegativeHeightInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, -10.0));
            assertEquals("Height must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("Constructor should throw IllegalArgumentException when width is zero")
        void testZeroWidthInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 10.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("Constructor should throw IllegalArgumentException when height is zero")
        void testZeroHeightInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, 0));
            assertEquals("Height must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("setWidth() should throw IllegalArgumentException when width is negative")
        void testSetNegativeWidth () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-3.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("setWidth() should throw IllegalArgumentException when height is negative")
        void testSetNegativeHeight () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-3.0));
            assertEquals("Height must be positive", exception.getMessage());
        }
}