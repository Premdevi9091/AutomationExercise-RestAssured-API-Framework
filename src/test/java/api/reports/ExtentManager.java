package api.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            "target/API_Report_AutomationExcercise.html"
                    );

            sparkReporter.config().setReportName(
                    "AutomationExercise API Report"
            );

            sparkReporter.config().setDocumentTitle(
                    "API Automation Results"
            );

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);
        }

        return extent;
    }
}