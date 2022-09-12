package tests;


import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ActivityLogTest extends BaseTest {
    String activity = "fast ballroom dancing";
    String testTime = "35";

    @AfterMethod(onlyForGroups = {"TestWithDeletion"})
    public void deleteData() {
        logPage.removeActivityFromLog();
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }

    @Test(groups = {"Smoke", "TestWithDeletion"})
    @Description("activity log test: add activity to log")
    public void activityLogPositiveTest() {
        int caloriesBurning = 5;
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        logPage.setActivityTime(testTime);
        int expectedResult = caloriesBurning * Integer.parseInt(testTime) + 1;
        logPage.clickAdd();
        logPage.waitForCalculationLoaded();
        logPage.addToActivityLog();
        logPage.waitForSearchInputLoaded();
        Assert.assertEquals(activity, String.valueOf(logPage.getFinalActivityName()),
                "Actual and expected activity name values do not match");
        Assert.assertEquals(expectedResult, Integer.parseInt(logPage.getFinalCalories()),
                "Actual and expected calories values do not match");
    }

    @Test(groups = {"Negative"})
    @Description("activity log negative test: add activity to log")
    public void activityLogNegativeTest() {
        String activity = "123";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        Assert.assertTrue(logPage.isNoResultsDisplayed(),
                "Search is successful");
    }

    @Test(groups = {"Regression", "TestWithDeletion"})
    @Description("edit activity log test")
    public void editActivityLogTest() throws InterruptedException {
        String newTime = "40";
        String newCalories = "201";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        logPage.setActivityTime(testTime);
        logPage.clickAdd();
        logPage.waitForCalculationLoaded();
        logPage.addToActivityLog();
        logPage.waitForSearchInputLoaded();
        logPage.clickEditActivityLog();
        logPage.editTimeInActivityLog(newTime, newCalories);
        StringBuffer expectedResult = new StringBuffer("00:");
        expectedResult.append(newTime);
        Assert.assertEquals(expectedResult.toString(), logPage.getNewTime(),
                "Actual and expected time values do not match");
        Assert.assertEquals(logPage.getFinalCalories(), newCalories,
                "Actual and expected calories values do not match");

    }

    @Test(groups = {"Smoke"})
    @Description("activity log test: remove activity from log")
    public void removeActivityFromLogTest() {
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        logPage.setActivityTime(testTime);
        logPage.clickAdd();
        logPage.waitForCalculationLoaded();
        logPage.addToActivityLog();
        logPage.waitForSearchInputLoaded();
        logPage.removeActivityFromLog();
        Assert.assertTrue(logPage.isTableEmpty(),
                "Activity table is not empty");
    }

}
