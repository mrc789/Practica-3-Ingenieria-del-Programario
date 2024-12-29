package Data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleIDTest {

    @Test
    void testValidVehicleID() {
        VehicleID vehicleID = new VehicleID("VH-1234");
        assertEquals("VH-1234", vehicleID.getId());
    }

    @Test
    void testInvalidVehicleIDFormat() {
        assertThrows(IllegalArgumentException.class, () -> new VehicleID(null));
        assertThrows(IllegalArgumentException.class, () -> new VehicleID("INVALID-123"));
        assertThrows(IllegalArgumentException.class, () -> new VehicleID("VH-123")); // Too short
        assertThrows(IllegalArgumentException.class, () -> new VehicleID("VH-12345")); // Too long
    }

    @Test
    void testEqualsAndHashCode() {
        VehicleID id1 = new VehicleID("VH-1234");
        VehicleID id2 = new VehicleID("VH-1234");
        VehicleID id3 = new VehicleID("VH-5678");

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void testToString() {
        VehicleID vehicleID = new VehicleID("VH-1234");
        assertEquals("VehicleID{id='VH-1234'}", vehicleID.toString());
    }
}