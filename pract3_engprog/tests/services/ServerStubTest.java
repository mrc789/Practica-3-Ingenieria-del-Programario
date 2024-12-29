package services;

import Data.*;
import exceptions.*;
import micromobility.JourneyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.stub.ServerStub;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ServerStubTest {

    private ServerStub serverStub;

    @BeforeEach
    void setUp() {
        serverStub = new ServerStub();
    }

    @Test
    void testCheckPMVAvail_Success() throws Exception {
        VehicleID vehicleID = new VehicleID("VH-1234");
        assertDoesNotThrow(() -> serverStub.checkPMVAvail(vehicleID));
    }

    @Test
    void testCheckPMVAvail_NotAvailable() {
        VehicleID vehicleID = new VehicleID("VH-9999");
        assertThrows(PMVNotAvailException.class, () -> serverStub.checkPMVAvail(vehicleID));
    }

    @Test
    void testRegisterPairing_Success() throws Exception {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        assertDoesNotThrow(() -> serverStub.registerPairing(user, vehicleID, station, location, date));

        assertTrue(serverStub.getPairings().containsKey(vehicleID));
        assertFalse(serverStub.getAvailableVehicles().contains(vehicleID));
    }

    @Test
    void testRegisterPairing_VehicleNotAvailable() throws Exception {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-9999"); // Not in available vehicles
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        assertThrows(InvalidPairingArgsException.class, () -> serverStub.registerPairing(user, vehicleID, station, location, date));
    }

    @Test
    void testStopPairing_Success() throws Exception {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        serverStub.registerPairing(user, vehicleID, station, location, date);

        assertDoesNotThrow(() -> serverStub.stopPairing(user, vehicleID, station, location, date, 10.0f, 5.0f, 30, BigDecimal.valueOf(15.0)));

        assertFalse(serverStub.getPairings().containsKey(vehicleID));
        assertTrue(serverStub.getAvailableVehicles().contains(vehicleID));
    }

    @Test
    void testStopPairing_NoPairingFound() {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        assertThrows(InvalidPairingArgsException.class, () -> serverStub.stopPairing(user, vehicleID, station, location, date, 10.0f, 5.0f, 30, BigDecimal.valueOf(15.0)));
    }

    @Test
    void testSetPairing_Success() {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        serverStub.setPairing(user, vehicleID, station, location, date);

        assertTrue(serverStub.getPairings().containsKey(vehicleID));
    }

    @Test
    void testUnPairRegisterService_Success() throws Exception {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);
        LocalDateTime date = LocalDateTime.now();

        serverStub.setPairing(user, vehicleID, station, location, date);

        JourneyService journeyService = new JourneyService(user, vehicleID, station, location);

        assertDoesNotThrow(() -> serverStub.unPairRegisterService(journeyService));
        assertFalse(serverStub.getPairings().containsKey(vehicleID));
    }

    @Test
    void testUnPairRegisterService_NoPairingFound() {
        UserAccount user = new UserAccount("USER-12345");
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f);

        JourneyService journeyService = new JourneyService(user, vehicleID, station, location);

        assertThrows(PairingNotFoundException.class, () -> serverStub.unPairRegisterService(journeyService));
    }

    @Test
    void testRegisterLocation() {
        VehicleID vehicleID = new VehicleID("VH-1234");
        StationID station = new StationID("ST-001");

        assertDoesNotThrow(() -> serverStub.registerLocation(vehicleID, station));
    }
}