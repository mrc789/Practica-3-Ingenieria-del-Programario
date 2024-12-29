package Data;

import java.util.Objects;

public final class UserAccount {
    private final String id;

    private static final String FORMAT = "USER-[A-Z0-9]{5}";

    public UserAccount(String id) {
        if (id == null) {
            throw new IllegalArgumentException("UserAccount ID cannot be null.");
        }
        if (!id.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid UserAccount format: '" + id + "'. Expected format: 'USER-XXXXX'");
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
        UserAccount that = (UserAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserAccount{" + "id='" + id + '\'' + '}';
    }

    public static boolean isValid(String id) {
        return id != null && id.matches(FORMAT);
    }

}
