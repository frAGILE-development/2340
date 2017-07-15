package Model.Maps;


/**
 * Created by bwatson35 on 7/11/17.
 */

public class Report {
    private static int Next_ID = 1000;
    private int _id;
    private String _name;
    private String _description;
    private Location _location;

    public Report(String name, String desc, Location location) {
        _name = name;
        _description = desc;
        _location = location;
        _id = Next_ID++;
    }

    public String toString() {
        return  _name + "\n" + _description;
    }

    public String getName() { return _name;}
    public String getDescription() {  return _description; }

    public double getLatitude() { return _location.getLatitude(); }
    public double getLongitude() { return _location.get_longitude(); }


}
