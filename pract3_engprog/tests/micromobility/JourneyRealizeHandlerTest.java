package micromobility;

import Data.*;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Server;
import services.stubs.ServerStub;
import services.smartfeatures.stubs.QRDecoderStub;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyRealizeHandlerTest {

    private JourneyRealizeHandler handler;
    private QRDecoderStub qrDecoderStub;
    private Server server;

    @BeforeEach
    void setUp() {
        qrDecoderStub = new QRDecoderStub();
        server = new ServerStub();
        handler = new JourneyRealizeHandler(server, qrDecoderStub);
    }

    @Test
    void testScanQRSuccess() throws Exception {
        qrDecoderStub.setVehicleIDToReturn(new VehicleID("VH-1234"));
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        assertDoesNotThrow(() -> handler.scanQR(dummyImage));
    }

    @Test
    void testScanQRVehicleUnavailable() throws Exception {
        qrDecoderStub.setVehicleIDToReturn(new VehicleID("VH-9999")); // Unavailable vehicle
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        assertThrows(PMVNotAvailException.class, () -> handler.scanQR(dummyImage));
    }

    @Test
    void testUnPairVehicleSuccess() throws Exception {
        qrDecoderStub.setVehicleIDToReturn(new VehicleID("VH-1234"));
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        handler.scanQR(dummyImage); // Pair a vehicle
        assertDoesNotThrow(() -> handler.unPairVehicle());
    }

    @Test
    void testUnPairVehicleNoPairing() {
        assertThrows(ProceduralException.class, () -> handler.unPairVehicle());
    }

    @Test
    void testStartDrivingSuccess() throws Exception {
        qrDecoderStub.setVehicleIDToReturn(new VehicleID("VH-1234"));
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        handler.scanQR(dummyImage); // Pair a vehicle
        assertDoesNotThrow(() -> handler.startDriving());
    }

    @Test
    void testStopDrivingSuccess() throws Exception {
        qrDecoderStub.setVehicleIDToReturn(new VehicleID("VH-1234"));
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        handler.scanQR(dummyImage); // Pair a vehicle
        handler.startDriving();
        assertDoesNotThrow(() -> handler.stopDriving());
    }
}