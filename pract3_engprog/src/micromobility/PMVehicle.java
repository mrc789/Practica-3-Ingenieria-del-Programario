package micromobility;

import Data.*;
import exceptions.*;

/**
 * Internal classes involved in the use of the service
 */
public class PMVehicle {
    // Class members
    private final VehicleID vehicleID;
    private GeographicPoint location;
    private PMVState state;

    // Constructor
    public PMVehicle(VehicleID vehicleID, GeographicPoint initialLocation) {
        this.vehicleID = vehicleID;
        this.location = initialLocation;
        this.state = PMVState.Availbale;
    }

    // Getters
    public VehicleID getVehicleID() {
        return vehicleID;
    }

    public GeographicPoint getLocation() {
        return location;
    }

    public PMVState getState() {
        return state;
    }

    // Setter methods to change state and location
    public void setNotAvailb() throws ProceduralException {
        if (state != PMVState.Availbale) {
            throw new ProceduralException("Cannot set to NotAvailable unless the vehicle is Available.");
        }
        this.state = PMVState.NotAvailable;
    }

    public void setUnderWay() throws ProceduralException {
        if (state != PMVState.NotAvailable) {
            throw new ProceduralException("Cannot set to UnderWay unless the vehicle is NotAvailable.");
        }
        this.state = PMVState.UnderWay;
    }

    public void setAvailb() throws ProceduralException {
        if (state != PMVState.NotAvailable && state != PMVState.TemporaryParking) {
            throw new ProceduralException("Cannot set to Available unless the vehicle is NotAvailable or in TemporaryParking.");
        }
        this.state = PMVState.Availbale;
    }

    public void setLocation(GeographicPoint gP) {
        this.location = gP;
    }

    // Enum for PMV state
    public enum PMVState { Availbale, NotAvailable, UnderWay, TemporaryParking; }
}