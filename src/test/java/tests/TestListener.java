package tests;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

    @Log4j2

    public class TestListener implements ITestListener {
        @Override
        public void onTestStart(ITestResult result) {
            ITestListener.super.onTestStart(result);
            log.debug(String.format("Test %s started", result.getName()));
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            ITestListener.super.onTestSuccess(result);
            log.debug(String.format("Test %s passed", result.getName()));
        }

        @Override
        @Attachment(value = "screenshot", type = "image/png")
        public void onTestFailure(ITestResult result) {
            ITestListener.super.onTestFailure(result);
            log.debug(String.format("Test %s failed", result.getName()));
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            AllureUtils.attachScreenshot(driver);
        }
    }

