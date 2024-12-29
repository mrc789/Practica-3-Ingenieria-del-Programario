package services.smartfeatures;

import Data.VehicleID;
import exceptions.CorruptedImgException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.smartfeatures.stubs.QRDecoderStub;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class QRDecoderStubTest {

    private QRDecoderStub qrDecoderStub;

    @BeforeEach
    void setUp() {
        qrDecoderStub = new QRDecoderStub();
    }

    @Test
    void testGetVehicleIDSuccess() throws Exception {
        // Create a real BufferedImage
        BufferedImage realQRImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        // Set the expected VehicleID
        VehicleID expectedVehicleID = new VehicleID("VH-5678");
        qrDecoderStub.setVehicleIDToReturn(expectedVehicleID);

        // Call the method and assert
        VehicleID actualVehicleID = qrDecoderStub.getVehicleID(realQRImage);
        assertEquals(expectedVehicleID, actualVehicleID);
    }

    @Test
    void testGetVehicleIDThrowsCorruptedImgException() {
        // Create a real BufferedImage
        BufferedImage realQRImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        // Configure the stub to throw an exception
        qrDecoderStub.setShouldThrowException(true);

        // Call the method and assert the exception
        assertThrows(CorruptedImgException.class, () -> qrDecoderStub.getVehicleID(realQRImage));
    }

    @Test
    void testGetVehicleIDNullImage() {
        // Call the method with null and assert the exception
        assertThrows(IllegalArgumentException.class, () -> qrDecoderStub.getVehicleID(null));
    }
}