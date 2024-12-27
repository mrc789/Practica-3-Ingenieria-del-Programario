package micromobility;

package micromobility;
/**
 * Internal classes involved in in the use of the service
 */
public class PMVehicle {
??? // The class members
    private PMVState state;
??? // The constructor/s
    // All the getter methods
// The setter methods to be used are only the following ones
    public void setNotAvailb () { . . . } throws ProceduralException;
    public void setUnderWay () { . . . } throws ProceduralException;
    public void setAvailb () { . . . } throws ProceduralException;
    public void setLocation (GeographicPoint gP) { . . . }

    public enum PMVState { Availbale, NotAvailable, UnderWay, TemporaryParking;}
}


