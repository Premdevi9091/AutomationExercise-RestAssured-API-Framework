package api.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class TestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentManager.getInstance();

    private static final ThreadLocal<ExtentTest>
            extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extent.createTest(
                        splitCamelCase(
                                result.getMethod()
                                        .getMethodName()
                        )
                );

        addMetadata(test, result);

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        long duration =
                result.getEndMillis()
                        - result.getStartMillis();

        extentTest.get().pass(
                "✅ Test Passed | Duration: "
                        + duration + " ms"
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(
                "❌ " + result.getThrowable()
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip(
                "⚠️ Test Skipped"
        );
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }

    private void addMetadata(
            ExtentTest test,
            ITestResult result) {

        Method method =
                result.getMethod()
                        .getConstructorOrMethod()
                        .getMethod();

        Class<?> testClass =
                result.getTestClass()
                        .getRealClass();

        Epic epic =
                testClass.getAnnotation(Epic.class);

        Feature feature =
                testClass.getAnnotation(Feature.class);

        Story story =
                method.getAnnotation(Story.class);

        Owner owner =
                method.getAnnotation(Owner.class);

        Severity severity =
                method.getAnnotation(Severity.class);

        Description description =
                method.getAnnotation(Description.class);

        // TAGS
        if (feature != null) {

            test.assignCategory(
                    splitCamelCase(
                            feature.value()
                    )
            );
        }

        // AUTHOR
        if (owner != null) {

            test.assignAuthor(
                    splitCamelCase(
                            owner.value()
                    )
            );
        }

        // SEVERITY
        if (severity != null) {

            test.assignDevice(
                    severity.value()
                            .value()
                            .toUpperCase()
            );
        }

        StringBuilder info =
                new StringBuilder();

        if (epic != null)
            info.append("<b>Epic:</b> ")
                    .append(epic.value())
                    .append("<br>");

        if (feature != null)
            info.append("<b>Feature:</b> ")
                    .append(splitCamelCase(feature.value()))
                    .append("<br>");

        if (story != null)
            info.append("<b>Story:</b> ")
                    .append(story.value())
                    .append("<br>");

        if (description != null)
            info.append("<b>Description:</b> ")
                    .append(description.value())
                    .append("<br>");

        if (severity != null)
            info.append("<b>Severity:</b> ")
                    .append(severity.value().value())
                    .append("<br>");

        if (owner != null)
            info.append("<b>Owner:</b> ")
                    .append(splitCamelCase(owner.value()))
                    .append("<br>");

        if (!info.isEmpty()) {
            test.info(info.toString());
        }
    }

    private String splitCamelCase(String text) {

        String spaced =
                text.replaceAll(
                        "([a-z])([A-Z])",
                        "$1 $2"
                );

        return Character.toUpperCase(
                spaced.charAt(0)
        ) + spaced.substring(1);
    }
}