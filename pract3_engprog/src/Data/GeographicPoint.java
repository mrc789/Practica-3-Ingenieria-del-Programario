package Data;

/**
 * Essential data classes
 */
final public class GeographicPoint {
    // The geographical coordinates expressed as decimal degrees
    private final float latitude;
    private final float longitude;
    public GeographicPoint (float lat, float lon) {
        this.latitude = lat;
        this.longitude = lon;
    }
    public float getLatitude () { return latitude; }
    public float getLongitude () { return longitude; }
    @Override
    public boolean equals (Object o) {
        boolean eq;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeographicPoint gP = (GeographicPoint) o;
        eq = ((latitude == gP.latitude) && (longitude == gP.longitude));
        return eq;
    }
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(latitude);
        result = prime * result + Float.floatToIntBits(longitude);
        return result;
    }
    @Override
    public String toString () {
        return "Geographic point {" + "latitude='" + latitude + '\'' +
                "longitude='" + longitude + '}';
    }

    public void StationId (Object o){

        if (o == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (!(o instanceof String)) {
            throw new IllegalArgumentException("Input must be a String representing a StationID");
        }
    String id = (String) o;

    // Attempt to create a new StationID instance using the given string
    StationID stationID = new StationID(id);
    System.out.println("StationID successfully created: " + stationID);
    }

    public void VehicleId (Object o){

        if (o == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (!(o instanceof String)) {
            throw new IllegalArgumentException("Input must be a String representing a VehicleID");
        }
        String id = (String) o;

        // Assuming VehicleID follows a specific format like "VH-XXX"
        String vehicleIdFormat = "VH-[A-Z0-9]{3}";
        if (!id.matches(vehicleIdFormat)) {
            throw new IllegalArgumentException("Invalid VehicleID format. Expected format: 'VH-XXX'");
        }

       // If valid, print confirmation
       System.out.println("VehicleID successfully created: " + id);
       }

    public void UserAccount (Object o){
        if (o == null) {
        throw new IllegalArgumentException("Input cannot be null");
    }
    if (!(o instanceof String)) {
        throw new IllegalArgumentException("Input must be a String representing a UserAccount ID");
    }
    String userId = (String) o;

    // Define a format for UserAccount, e.g., "UA-XXXXXX" (UA followed by 6 alphanumeric characters)
    String userAccountFormat = "UA-[A-Z0-9]{6}";
    if (!userId.matches(userAccountFormat)) {
        throw new IllegalArgumentException("Invalid UserAccount format. Expected format: 'UA-XXXXXX'");
    }

    // If valid, print confirmation
    System.out.println("UserAccount successfully created: " + userId);
    }
}

