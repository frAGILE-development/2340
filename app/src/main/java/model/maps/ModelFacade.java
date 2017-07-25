package model.maps;
import java.util.List;

/**
 * Created by Bryce Watson on 2/21/17.
 * Facade for the google maps model
 */

public class ModelFacade {
    private static final ModelFacade INSTANCE = new ModelFacade();
    public static ModelFacade getInstance() { return INSTANCE; }

    private final ReportManager _reportManager;

    private ModelFacade() {
        _reportManager = new ReportManager();
    }
    /**
     * adds a report
     * @param loc location
     */
    public void addReport(Location loc) {
        _reportManager.addReport(new Report("New Item", "Platform ", loc));
    }

    /**
     * getter for reports
     * @return reports
     */
    public List<Report> getReports() { return _reportManager.getReportList(); }
    /**
     * getter for last report
     * @return last report
     */
    public Report getLastReport() {
        return _reportManager.getLastReport();
    }

}
