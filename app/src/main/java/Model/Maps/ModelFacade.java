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

    public void addReport(String title, String desc, Location loc) {
        _reportManager.addReport(new Report(title, desc, loc));
    }

    public List<Report> getReports() { return _reportManager.getReportList(); }

    public Report getLastReport() {
        return _reportManager.getLastReport();
    }

}
