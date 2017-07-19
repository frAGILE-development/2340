package Model.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwatson35 on 7/11/17.
 */

class ReportManager {
    private final List<Report> _reports;
    /**
     * constructor for report manager
     */
    public ReportManager() {
        _reports = new ArrayList<>();
        makeSomeReports();
    }
    /**
     * creates reports
     */
    private void makeSomeReports() {
        addReport(new Report("Coke Zero", "Sam's Deli", new Location(33.749, -84.388)));
        addReport(new Report("Pepsi", "Grandma Garage", new Location(33.8, -84.5)));
    }
    /**
     * getter for report list
     * @return reports
     */
    public List<Report> getReportList() { return _reports; }
    /**
     * adds reports
     */
    public void addReport(Report r) {_reports.add(r);}

    /**
     * getter for last report to string
     * @return the last report in string format
     */
    public String getLastReportString() {
        return _reports.get(_reports.size() - 1).toString();
    }
    /**
     * getter for last report
     * @return last report
     */
    public Report getLastReport() { return _reports.get(_reports.size() - 1);}
}
