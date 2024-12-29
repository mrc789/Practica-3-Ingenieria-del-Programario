package Data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StationIDTest {

    @Test
    void testValidStationID() {
        StationID stationID = new StationID("ST-ABC");
        assertEquals("ST-ABC", stationID.getId());
    }

    @Test
    void testInvalidStationIDFormat() {
        assertThrows(IllegalArgumentException.class, () -> new StationID(null));
        assertThrows(IllegalArgumentException.class, () -> new StationID("INVALID"));
        assertThrows(IllegalArgumentException.class, () -> new StationID("ST-AB")); // Too short
        assertThrows(IllegalArgumentException.class, () -> new StationID("ST-ABCD")); // Too long
    }

    @Test
    void testEqualsAndHashCode() {
        StationID id1 = new StationID("ST-ABC");
        StationID id2 = new StationID("ST-ABC");
        StationID id3 = new StationID("ST-XYZ");

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void testToString() {
        StationID stationID = new StationID("ST-ABC");
        assertEquals("StationID{id='ST-ABC'}", stationID.toString());
    }
}
