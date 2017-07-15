package Model.Maps;

import Model.Model;

/**
 * Created by bwatson35 on 7/11/17.
 */

public class Location {
    private double _latitude;
    private double _longitude;

    public Location(double lat, double longit) {
        _latitude = lat;
        _longitude = longit;
    }

    public double getLatitude() { return _latitude; }
    public double get_longitude() { return _longitude; }
}
