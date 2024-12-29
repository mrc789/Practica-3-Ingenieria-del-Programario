package Data;

import java.util.Objects;

/**
 * Represents a unique identifier for a service.
 */
public final class ServiceID {
    private final String id;

    private static final String FORMAT = "SRV-[A-Z0-9]{4}";

    public ServiceID(String id) {
        if (id == null || !id.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid ServiceID format. Expected format: 'SRV-XXXX'.");
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
        ServiceID serviceID = (ServiceID) o;
        return id.equals(serviceID.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ServiceID{" + "id='" + id + '\'' + '}';
    }
}