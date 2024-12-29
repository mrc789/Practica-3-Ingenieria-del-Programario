package services.smartfeatures;

import java.net.ConnectException;

/**
 * External service for broadcasting the station ID by Bluetooth.
 */
public interface UnbondedBTSignal {
    void BTbroadcast() throws ConnectException;
}
