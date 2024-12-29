package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeographicPointTest {

    @Test
    void testValidGeographicPoint() {
        GeographicPoint point = new GeographicPoint(45.0f, 90.0f);
        assertEquals(45.0f, point.getLatitude());
        assertEquals(90.0f, point.getLongitude());
    }

    @Test
    void testInvalidLatitudeAboveRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeographicPoint(91.0f, 0.0f);
        });
        assertEquals("Latitude must be between -90 and 90 degrees.", exception.getMessage());
    }

    @Test
    void testInvalidLatitudeBelowRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeographicPoint(-91.0f, 0.0f);
        });
        assertEquals("Latitude must be between -90 and 90 degrees.", exception.getMessage());
    }

    @Test
    void testInvalidLongitudeAboveRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeographicPoint(0.0f, 181.0f);
        });
        assertEquals("Longitude must be between -180 and 180 degrees.", exception.getMessage());
    }

    @Test
    void testInvalidLongitudeBelowRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeographicPoint(0.0f, -181.0f);
        });
        assertEquals("Longitude must be between -180 and 180 degrees.", exception.getMessage());
    }

    @Test
    void testEqualPoints() {
        GeographicPoint point1 = new GeographicPoint(45.0f, 90.0f);
        GeographicPoint point2 = new GeographicPoint(45.0f, 90.0f);
        assertEquals(point1, point2);
    }

    @Test
    void testNonEqualPoints() {
        GeographicPoint point1 = new GeographicPoint(45.0f, 90.0f);
        GeographicPoint point2 = new GeographicPoint(45.0f, -90.0f);
        assertNotEquals(point1, point2);
    }
}
