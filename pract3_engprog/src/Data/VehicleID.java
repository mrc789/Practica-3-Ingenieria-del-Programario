package Data;

import java.util.Objects;


public final class VehicleID {
    private final String id;

    private static final String FORMAT = "VH-[A-Z0-9]{4}";

    public VehicleID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("VehicleID cannot be null.");
        }
        if (!id.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid VehicleID format: '" + id + "'. Expected format: 'VH-XXXX'");
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
        VehicleID vehicleID = (VehicleID) o;
        return id.equals(vehicleID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VehicleID{" + "id='" + id + '\'' + '}';
    }

    public static boolean isValid(String id) {
        return id != null && id.matches(FORMAT);
    }

}
