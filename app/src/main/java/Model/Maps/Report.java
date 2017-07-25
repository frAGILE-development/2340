package Model.Maps;


/**
 * Created by Bryce Watson on 7/11/17.
 * Information holder for a maps report
 */

public class Report {
    private static int Next_ID = 1000;
    private final String _name;
    private final String _description;
    private final Location _location;
    /**
     * constructor for report
     * @param name name
     * @param desc description
     * @param location location
     */
    public Report(String name, String desc, Location location) {
        _name = name;
        _description = desc;
        _location = location;
        int _id = Next_ID++;
    }
    /**
     * to string method
     * @return the report in a string format
     */
    public String toString() {
        return  _name + "\n" + _description;
    }
    /**
     *getter for name
     * @return name
     */
    public String getName() { return _name;}
    /**
     * getter for description
     * @return description
     */
    public String getDescription() {  return _description; }

    /**
     *getter for latitude
     * @return latitude
     */
    public double getLatitude() { return _location.getLatitude(); }
    /**
     * getter for longitude
     * @return longitude
     */
    public double getLongitude() { return _location.get_longitude(); }


}
