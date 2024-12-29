package Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a unique identifier for a station.
 */
public final class StationID implements Serializable {
    private static final long serialVersionUID = 1L; // Useful for serialization.
    private final String id;

    // A simple regex for a default valid format.
    private static final String DEFAULT_FORMAT = "ST-[A-Z0-9]{3}";

    public StationID(String id) {
        if (id == null || !id.matches(DEFAULT_FORMAT)) {
            throw new IllegalArgumentException("Invalid StationID format. Expected format: 'ST-XXX'");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationID stationID = (StationID) o;
        return id.equals(stationID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StationID{" + "id='" + id + '\'' + '}';
    }
}
