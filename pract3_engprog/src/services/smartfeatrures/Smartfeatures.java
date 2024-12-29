package services.smartfeatrures;

import java.net.ConnectException;
import Data.*;

/**
 * External services involved in the functioning of some features
 */
public interface UnbondedBTSignal { // Broadcasts the station ID by BT
    void BTbroadcast () throws ConnectException;
}

public interface QRDecoder { // Decodes QR codes from an image
    VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}

public interface ArduinoMicroController { // Software for microcontrollers
    public void setBTconnection () throws ConnectException;
    public void startDriving () throws PMVPhisicalException, ConnectException,
            ProceduralException;
    public void stopDriving () throws PMVPhisicalException, ConnectException,
            ProceduralException;
    public void undoBTconnection ();
}