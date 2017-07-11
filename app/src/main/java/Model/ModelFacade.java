package Model;

import java.util.List;

/**
 * Created by bwatson35 on 2/21/17.
 */

public class ModelFacade {
    private static ModelFacade INSTANCE = new ModelFacade();
    public static ModelFacade getInstance() { return INSTANCE; }

    private ReportManager _reportManager;
    private UserManager _userManager;

    private ModelFacade() {
        _reportManager = new ReportManager();
        _userManager = new UserManager();
    }

    public void addReport(String title, String desc, Location loc) {
        _reportManager.addReport(new Report(title, desc, loc));
    }

    public List<Report> getReports() { return _reportManager.getReportList(); }

    public Report getLastReport() {
        return _reportManager.getLastReport();
    }

    public boolean doLogin(String uid, String pass) {
        return _userManager.tryLogin(uid, pass);
    }
}
