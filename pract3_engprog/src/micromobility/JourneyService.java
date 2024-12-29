package micromobility;

import Data.*;
import java.math.BigDecimal;

public class JourneyService {
    // Class members
    private final UserAccount user;
    private final VehicleID vehicle;
    private final StationID startStation;
    private GeographicPoint startLocation;
    private StationID endStation;
    private GeographicPoint endLocation;
    private int duration; // in minutes
    private float distance; // in kilometers
    private float averageSpeed; // in km/h
    private BigDecimal cost;
    private ServiceState state;

    // Enum for service state
    public enum ServiceState {
        INITIATED, IN_PROGRESS, COMPLETED
    }

    // Constructor
    public JourneyService(UserAccount user, VehicleID vehicle, StationID startStation, GeographicPoint startLocation) {
        this.user = user;
        this.vehicle = vehicle;
        this.startStation = startStation;
        this.startLocation = startLocation;
        this.state = ServiceState.INITIATED;
    }

    // Getters
    public UserAccount getUser() {
        return user;
    }

    public VehicleID getVehicle() {
        return vehicle;
    }

    public StationID getStartStation() {
        return startStation;
    }

    public GeographicPoint getStartLocation() {
        return startLocation;
    }

    public StationID getEndStation() {
        return endStation;
    }

    public GeographicPoint getEndLocation() {
        return endLocation;
    }

    public int getDuration() {
        return duration;
    }

    public float getDistance() {
        return distance;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public ServiceState getState() {
        return state;
    }

    // Setters
    public void setServiceInit() {
        this.state = ServiceState.IN_PROGRESS;
    }

    public void setServiceFinish(StationID endStation, GeographicPoint endLocation, int duration, float distance,
                                 float averageSpeed, BigDecimal cost) {
        this.endStation = endStation;
        this.endLocation = endLocation;
        this.duration = duration;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.cost = cost;
        this.state = ServiceState.COMPLETED;
    }
}
