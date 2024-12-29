package services.smartfeatures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.smartfeatures.stubs.UnbondedBTSignalStub;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

class UnbondedBTSignalStubTest {

    private UnbondedBTSignalStub stub;

    @BeforeEach
    void setUp() {
        stub = new UnbondedBTSignalStub();
    }

    @Test
    void testBTbroadcastSuccess() throws ConnectException {
        stub.setConnectionState(true);
        assertDoesNotThrow(() -> stub.BTbroadcast());
    }

    @Test
    void testBTbroadcastThrowsExceptionWhenNotConnected() {
        stub.setConnectionState(false);
        assertThrows(ConnectException.class, () -> stub.BTbroadcast());
    }

    @Test
    void testSetConnectionState() {
        stub.setConnectionState(true);
        assertTrue(stub.isConnected());

        stub.setConnectionState(false);
        assertFalse(stub.isConnected());
    }
}
