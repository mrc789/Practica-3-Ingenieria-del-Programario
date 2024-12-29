package services.smartfeatures.stubs;

import Data.VehicleID;
import exceptions.CorruptedImgException;

import java.awt.image.BufferedImage;

/**
 * Stub implementation of QRDecoder for testing purposes.
 */
public class QRDecoderStub {
    private VehicleID vehicleIDToReturn;
    private boolean shouldThrowException = false;

    public void setVehicleIDToReturn(VehicleID vehicleIDToReturn) {
        this.vehicleIDToReturn = vehicleIDToReturn;
    }

    public void setShouldThrowException(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }

    public VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException {
        if (QRImg == null) {
            throw new IllegalArgumentException("QR image cannot be null.");
        }
        if (shouldThrowException) {
            throw new CorruptedImgException("Simulated corrupted image exception.");
        }
        return vehicleIDToReturn;
    }
}
