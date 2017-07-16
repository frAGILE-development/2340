package Model.Maps;
import java.util.List;

/**
 * Created by bwatson35 on 2/21/17.
 */

public class ModelFacade {
    private static ModelFacade INSTANCE = new ModelFacade();
    public static ModelFacade getInstance() { return INSTANCE; }

    private ReportManager _reportManager;

    private ModelFacade() {
        _reportManager = new ReportManager();
    }
    /**
     * adds a report
     * @param title the title
     * @param desc description
     * @param loc location
     */
    public void addReport(String title, String desc, Location loc) {
        _reportManager.addReport(new Report(title, desc, loc));
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
