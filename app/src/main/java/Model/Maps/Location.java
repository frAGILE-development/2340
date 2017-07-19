package Model.Maps;

import Model.Model;

/**
 * Created by bwatson35 on 7/11/17.
 */

public class Location {
    private double _latitude;
    private double _longitude;
    /**
     * constructor for location
     * @param lat latitude
     * @param longit longitude
     */
    public Location(double lat, double longit) {
        _latitude = lat;
        _longitude = longit;
    }
    /**
     * getter for latitude
     * @return latitude
     */
    public double getLatitude() { return _latitude; }
    /**
     * getter for longitude
     * @return longitude
     */
    public double get_longitude() { return _longitude; }
}
