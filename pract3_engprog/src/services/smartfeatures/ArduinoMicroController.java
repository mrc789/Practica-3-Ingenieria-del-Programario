package services.smartfeatures;

import exceptions.*;
import exceptions.ProceduralException;
import java.net.ConnectException;

/**
 * External service for interacting with the Arduino microcontrollers.
 */
public interface ArduinoMicroController {
    void setBTconnection() throws ConnectException;

    void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException;

    void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException;

    void undoBTconnection();
}