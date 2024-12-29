package services.smartfeatures.stubs;

import exceptions.*;
import services.smartfeatures.ArduinoMicroController;

import java.net.ConnectException;

/**
 * Stub implementation for ArduinoMicroController interface.
 */
public class ArduinoMicroControllerStub implements ArduinoMicroController {

    private boolean isConnected;
    private boolean isDriving;

    public ArduinoMicroControllerStub() {
        this.isConnected = false;
        this.isDriving = false;
    }

    @Override
    public void setBTconnection() throws ConnectException {
        if (isConnected) {
            throw new ConnectException("Bluetooth is already connected.");
        }
        isConnected = true;
    }

    @Override
    public void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!isConnected) {
            throw new ConnectException("Bluetooth is not connected.");
        }
        if (isDriving) {
            throw new ProceduralException("Already driving.");
        }
        isDriving = true;
    }

    @Override
    public void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!isConnected) {
            throw new ConnectException("Bluetooth is not connected.");
        }
        if (!isDriving) {
            throw new ProceduralException("Vehicle is not driving.");
        }
        isDriving = false;
    }

    @Override
    public void undoBTconnection() {
        isConnected = false;
        isDriving = false;
    }

    // Debugging helpers
    public boolean isConnected() {
        return isConnected;
    }

    public boolean isDriving() {
        return isDriving;
    }
}