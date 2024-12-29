package services;

import Data.*;
import exceptions.*;
import micromobility.JourneyService;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stub implementation of the Server interface for testing purposes.
 */
public class ServerStub implements Server {

    private final Set<VehicleID> availableVehicles = new HashSet<>();
    private final Map<VehicleID, PairingData> pairings = new HashMap<>();

    public ServerStub() {
        // Initialize some vehicles as available for testing
        availableVehicles.add(new VehicleID("VH-1234"));
        availableVehicles.add(new VehicleID("VH-5678"));
    }

    @Override
    public void checkPMVAvail(VehicleID vhID) throws PMVNotAvailException, ConnectException {
        if (!availableVehicles.contains(vhID)) {
            throw new PMVNotAvailException("Vehicle not available: " + vhID.getId());
        }
    }

    @Override
    public void registerPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException {
        if (!availableVehicles.remove(veh)) {
            throw new InvalidPairingArgsException("Vehicle not available for pairing: " + veh.getId());
        }
        // Simulate successful pairing by storing the pairing data
        pairings.put(veh, new PairingData(user, st, loc, date));
    }

    @Override
    public void stopPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date,
                            float avSp, float dist, int dur, BigDecimal imp) throws InvalidPairingArgsException {
        PairingData pairingData = pairings.remove(veh);
        if (pairingData == null) {
            throw new InvalidPairingArgsException("No active pairing found for vehicle: " + veh.getId());
        }
        // Simulate making the vehicle available again
        availableVehicles.add(veh);
    }

    @Override
    public void setPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) {
        // Simulate persisting pairing data without actual storage
        pairings.put(veh, new PairingData(user, st, loc, date));
    }

    @Override
    public void unPairRegisterService(JourneyService s) throws PairingNotFoundException {
        // Simulate unregistering a service by checking if the vehicle exists in pairings
        VehicleID veh = s.getVehicleID();
        if (!pairings.containsKey(veh)) {
            throw new PairingNotFoundException("No pairing found for vehicle: " + veh.getId());
        }
        pairings.remove(veh);
    }

    @Override
    public void registerLocation(VehicleID veh, StationID st) {
        // Simulate updating the location of the vehicle
        System.out.println("Vehicle " + veh.getId() + " is now registered at station " + st.getId());
    }

    /**
     * Inner class to store pairing data.
     */
    private static class PairingData {
        private final UserAccount user;
        private final StationID station;
        private final GeographicPoint location;
        private final LocalDateTime date;

        public PairingData(UserAccount user, StationID station, GeographicPoint location, LocalDateTime date) {
            this.user = user;
            this.station = station;
            this.location = location;
            this.date = date;
        }
    }
}