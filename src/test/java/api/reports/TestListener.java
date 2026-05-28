package api.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class TestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentManager.getInstance();

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();


    // IMPORTANT METHOD
    public static ExtentTest getTest() {

        return extentTest.get();
    }


    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extent.createTest(
                        result.getMethod().getMethodName()
                );

        addAllureMetadata(test, result);

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(
                result.getThrowable()
        );
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }

    private void addAllureMetadata(ExtentTest test, ITestResult result) {

        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        Description description = method.getAnnotation(Description.class);
        if (description != null) {
            test.info("<b>Description:</b> " + description.value());
        }

        Severity severity = method.getAnnotation(Severity.class);
        if (severity != null) {
            test.assignCategory(severity.value().value());
            test.info("<b>Severity:</b> " + severity.value().value());
        }

        Owner owner = method.getAnnotation(Owner.class);
        if (owner != null) {
            test.assignAuthor(owner.value());
            test.info("<b>Owner:</b> " + owner.value());
        }
    }
}
