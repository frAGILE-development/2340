package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwatson35 on 7/11/17.
 */

public class ReportManager {
    private List<Report> _reports;

    public ReportManager() {
        _reports = new ArrayList<>();
        makeSomeReports();
    }

    private void makeSomeReports() {
        addReport(new Report("Coke Zero", "Sam's Deli", new Location(33.749, -84.388)));
        addReport(new Report("Pepsi", "Grandma Garage", new Location(33.8, -84.5)));
    }

    public List<Report> getReportList() { return _reports; }
    public void addReport(Report r) {_reports.add(r);}


    public String getLastReportString() {
        return _reports.get(_reports.size() - 1).toString();
    }
    public Report getLastReport() { return _reports.get(_reports.size() - 1);}
}
