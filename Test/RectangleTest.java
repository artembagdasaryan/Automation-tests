package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    // ✅ Позитивні тести
    @Test
    void testDefaultConstructor() {
        Rectangle rect = new Rectangle();
        assertEquals(1.0, rect.getWidth());
        assertEquals(1.0, rect.getHeight());
        assertEquals(90.0, rect.getAngle());
    }

    @Test
    void testParameterizedConstructor() {
        Rectangle rect = new Rectangle(5.0, 10.0);
        assertEquals(5.0, rect.getWidth());
        assertEquals(10.0, rect.getHeight());
    }

    @Test
    void testSetWidth() {
        Rectangle rect = new Rectangle();
        rect.setWidth(7.5);
        assertEquals(7.5, rect.getWidth());
    }

    @Test
    void testSetHeight() {
        Rectangle rect = new Rectangle();
        rect.setHeight(4.2);
        assertEquals(4.2, rect.getHeight());
    }

    @Test
    void testCalculateArea() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rect.calculateArea());
    }
    @Test
    void testFloatingPointCalculations() {
        Rectangle rect = new Rectangle(3.333, 4.444);
        double expectedArea = 3.333 * 4.444;
        assertEquals(expectedArea, rect.calculateArea(), 1e-3);
    }
    @Test
    void testLargeDimensions() {
        Rectangle rect = new Rectangle(1e8, 1e8);
        double expectedArea = 1e8 * 1e8;
        assertEquals(expectedArea, rect.calculateArea());
    }
    @Test
    void testSmallDimensions() {
        Rectangle rect = new Rectangle(0.0001, 0.0002);
        double expectedArea = 0.0001 * 0.0002;
        assertEquals(expectedArea, rect.calculateArea(), 1e-8);
    }
    @Test
    void testCalculatePerimeter() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(14.0, rect.calculatePerimeter());
    }
    @Test
    void specifictest(){
        Rectangle rect = new Rectangle(4,5)
    }
    @Test
    void testGetDiagonal() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(5.0, rect.getDiagonal(), 0.0001); // Точність 4 знаки після коми
    }

    @ParameterizedTest
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
        // ❌ Негативні тести
        @Test
        void testNegativeWidthInConstructor() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(-5.0, 10.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        void testNegativeHeightInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, -10.0));
            assertEquals("Height must be positive", exception.getMessage());
        }

        @Test
        void testZeroWidthInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 10.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        void testZeroHeightInConstructor () {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Rectangle(5.0, 0));
            assertEquals("Height must be positive", exception.getMessage());
        }

        @Test
        void testSetNegativeWidth () {
            Rectangle rect = new Rectangle();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> rect.setWidth(-3.0));
            assertEquals("Width must be positive", exception.getMessage());
        }

        @Test
        void testSetNegativeHeight () {
            Rectangle rect = new Rectangle();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> rect.setHeight(-3.0));
            assertEquals("Height must be positive", exception.getMessage());
        }
}