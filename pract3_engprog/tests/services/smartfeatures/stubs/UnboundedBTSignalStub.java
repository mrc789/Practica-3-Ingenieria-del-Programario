package services.smartfeatures.stubs;

import services.smartfeatures.UnbondedBTSignal;
import java.net.ConnectException;

/**
 * Stub implementation for the UnbondedBTSignal interface.
 */
public class UnbondedBTSignalStub implements UnbondedBTSignal {

    private boolean isConnected;

    public UnbondedBTSignalStub() {
        this.isConnected = false;
    }

    public void setConnectionState(boolean connected) {
        this.isConnected = connected;
    }

    @Override
    public void BTbroadcast() throws ConnectException {
        if (!isConnected) {
            throw new ConnectException("Bluetooth is not connected.");
        }
        System.out.println("Broadcasting station ID via Bluetooth...");
    }

    // Debugging helper
    public boolean isConnected() {
        return isConnected;
    }
}