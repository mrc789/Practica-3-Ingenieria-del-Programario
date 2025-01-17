package services;

import micromobility.JourneyService;
import Data.*;
import exceptions.*;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * External services involved in the shared micromobility system
 */
public interface Server { // External service for the persistent storage
    // To be invoked by the use case controller
    void checkPMVAvail(VehicleID vhID)
            throws PMVNotAvailException, ConnectException;
    void registerPairing(UserAccount user, VehicleID veh, StationID st,
                         GeographicPoint loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException;
    void stopPairing(UserAccount user, VehicleID veh, StationID st,
                     GeographicPoint loc, LocalDateTime date, float avSp, float dist,
                     int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException;
    // Internal operations
    void setPairing(UserAccount user, VehicleID veh, StationID st,
                    GeographicPoint loc, LocalDateTime date);
    void unPairRegisterService(JourneyService s)
            throws PairingNotFoundException;
    void registerLocation(VehicleID veh, StationID st);
}