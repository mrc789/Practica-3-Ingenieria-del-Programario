package Data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    @Test
    void testValidUserAccount() {
        UserAccount userAccount = new UserAccount("USER-12345");
        assertEquals("USER-12345", userAccount.getId());
    }

    @Test
    void testInvalidUserAccountFormat() {
        assertThrows(IllegalArgumentException.class, () -> new UserAccount(null));
        assertThrows(IllegalArgumentException.class, () -> new UserAccount("INVALID-123"));
        assertThrows(IllegalArgumentException.class, () -> new UserAccount("USER-123"));
        assertThrows(IllegalArgumentException.class, () -> new UserAccount("USER-123456")); // Too long
    }

    @Test
    void testEqualsAndHashCode() {
        UserAccount user1 = new UserAccount("USER-12345");
        UserAccount user2 = new UserAccount("USER-12345");
        UserAccount user3 = new UserAccount("USER-67890");

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToString() {
        UserAccount userAccount = new UserAccount("USER-12345");
        assertEquals("UserAccount{id='USER-12345'}", userAccount.toString());
    }
}
