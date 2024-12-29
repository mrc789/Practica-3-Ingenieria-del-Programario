package services.smartfeatures;

import Data.VehicleID;
import java.awt.image.BufferedImage;
import exceptions.*;

/**
 * External service for decoding QR codes from an image.
 */
public interface QRDecoder {
    VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}
