package services.stub;

import micromobility.JourneyService;
import Data.*;
import exceptions.*;
import services.Server;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.math.BigDecimal;
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
        // Initialize with some example data for testing purposes
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
    public void registerPairing(UserAccount user, VehicleID veh, StationID st,
                                GeographicPoint loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException {
        if (!availableVehicles.remove(veh)) {
            throw new InvalidPairingArgsException("Vehicle not available for pairing: " + veh.getId());
        }
        pairings.put(veh, new PairingData(user, st, loc, date));
    }

    @Override
    public void stopPairing(UserAccount user, VehicleID veh, StationID st,
                            GeographicPoint loc, LocalDateTime date, float avSp, float dist,
                            int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException {
        if (!pairings.containsKey(veh)) {
            throw new InvalidPairingArgsException("No pairing found for vehicle: " + veh.getId());
        }
        pairings.remove(veh);
        availableVehicles.add(veh); // Make the vehicle available again
        System.out.printf("Pairing for vehicle %s stopped successfully. Distance: %.2f km, Duration: %d mins, Cost: $%.2f%n",
                veh.getId(), dist, dur, imp);
    }

    @Override
    public void setPairing(UserAccount user, VehicleID veh, StationID st,
                           GeographicPoint loc, LocalDateTime date) {
        pairings.put(veh, new PairingData(user, st, loc, date));
        System.out.printf("Pairing set for vehicle %s at station %s.%n", veh.getId(), st.getId());
    }

    @Override
    public void unPairRegisterService(JourneyService s) throws PairingNotFoundException {
        VehicleID veh = s.getVehicle();
        if (!pairings.containsKey(veh)) {
            throw new PairingNotFoundException("No pairing found for vehicle: " + veh.getId());
        }
        pairings.remove(veh);
        availableVehicles.add(veh); // Make the vehicle available again
        System.out.printf("Service for vehicle %s unpaired successfully.%n", veh.getId());
    }

    @Override
    public void registerLocation(VehicleID veh, StationID st) {
        System.out.printf("Vehicle %s is now registered at station %s.%n", veh.getId(), st.getId());
    }

    /**
     * Retrieves all currently available vehicles for pairing.
     *
     * @return a set of available VehicleID objects.
     */
    public Set<VehicleID> getAvailableVehicles() {
        return new HashSet<>(availableVehicles);
    }

    /**
     * Retrieves all active pairings.
     *
     * @return a map of VehicleID to PairingData representing active pairings.
     */
    public Map<VehicleID, PairingData> getPairings() {
        return new HashMap<>(pairings);
    }

    /**
     * Inner class to represent pairing data.
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

        @Override
        public String toString() {
            return String.format("User: %s, Station: %s, Location: (%.4f, %.4f), Date: %s",
                    user.getId(), station.getId(), location.getLatitude(), location.getLongitude(), date);
        }
    }
}