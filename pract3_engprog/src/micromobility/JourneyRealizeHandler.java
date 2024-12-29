package micromobility;

import Data.*;
import exceptions.*;
import services.*;
import services.smartfeatures.*;


import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;

/**
 * Handler for the journey realization use case.
 */
public class JourneyRealizeHandler {
    // Class members
    private final Server server;
    private final QRDecoder qrDecoder;
    private PMVehicle currentVehicle;
    private JourneyService currentJourney;

    // Constructor
    public JourneyRealizeHandler(Server server, QRDecoder qrDecoder) {
        this.server = server;
        this.qrDecoder = qrDecoder;
    }

    // User interface input events
    public void scanQR(BufferedImage qrImage)
            throws ConnectException, InvalidPairingArgsException, CorruptedImgException, PMVNotAvailException, ProceduralException {
        if (currentVehicle != null) {
            throw new ProceduralException("A vehicle is already paired. Please unpair before scanning a new QR code.");
        }

        // Decode QR code to get the vehicle ID
        VehicleID vehicleID = qrDecoder.getVehicleID(qrImage);

        // Check if the vehicle is available
        server.checkPMVAvail(vehicleID);

        // Pair the vehicle
        UserAccount user = new UserAccount("USER-12345"); // Example user
        StationID station = new StationID("ST-001");
        GeographicPoint location = new GeographicPoint(40.7128f, -74.0060f); // Example location
        LocalDateTime date = LocalDateTime.now();

        server.registerPairing(user, vehicleID, station, location, date);

        // Initialize the vehicle and journey service
        currentVehicle = new PMVehicle(vehicleID, location);
        currentJourney = new JourneyService(user, vehicleID, station, location);

        currentJourney.setServiceInit();
    }

    public void unPairVehicle()
            throws ConnectException, InvalidPairingArgsException, PairingNotFoundException, ProceduralException {
        if (currentVehicle == null) {
            throw new ProceduralException("No vehicle is currently paired to unpair.");
        }

        // Unpair the vehicle
        UserAccount user = currentJourney.getUser();
        StationID station = new StationID("ST-002"); // Example destination station
        GeographicPoint location = new GeographicPoint(40.7306f, -73.9352f); // Example destination location
        LocalDateTime date = LocalDateTime.now();

        server.stopPairing(user, currentVehicle.getVehicleID(), station, location, date,
                10.0f, 5.0f, 30, BigDecimal.valueOf(15.0));

        currentJourney.setServiceFinish(station, location, 30, 5.0f, 10.0f, BigDecimal.valueOf(15.0));

        // Clean up
        currentVehicle = null;
        currentJourney = null;
    }

    // Input events from the unbonded Bluetooth channel
    public void broadcastStationID(StationID stID) throws ConnectException {
        System.out.println("Broadcasting station ID: " + stID.getId());
    }

    // Input events from the Arduino microcontroller channel
    public void startDriving() throws ConnectException, ProceduralException {
        if (currentVehicle == null) {
            throw new ProceduralException("No vehicle is currently paired to start driving.");
        }

        currentVehicle.setUnderWay();
        System.out.println("Driving started for vehicle: " + currentVehicle.getVehicleID().getId());
    }

    public void stopDriving() throws ConnectException, ProceduralException {
        if (currentVehicle == null) {
            throw new ProceduralException("No vehicle is currently paired to stop driving.");
        }

        currentVehicle.setNotAvailb();
        System.out.println("Driving stopped for vehicle: " + currentVehicle.getVehicleID().getId());
    }

    // Internal operations
    private void calculateValues(GeographicPoint gP, LocalDateTime date) {
        // Example calculation logic
        System.out.println("Calculating values...");
    }

    private void calculateImport(float dis, int dur, float avSp, LocalDateTime date) {
        // Example calculation logic
        System.out.println("Calculating import...");
    }
}
