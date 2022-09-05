package tests;


import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityLogTest extends BaseTest {

    @Test(groups = {"Smoke"})
    @Description("activity log test")
    public void activityLogPositiveTest() {
        String testTime = "35";
        String activity = "fast ballroom dancing";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        logPage.setActivityTime(testTime);
        String expectedResult = String.valueOf(logPage.caloriesBurntCalculation(testTime, activity));
        Assert.assertEquals(String.valueOf(logPage.getCaloriesFromTable(activity)), expectedResult,
                "Calories values don't match");
        logPage.clickAdd();
        logPage.waitForCalculationLoaded();
        logPage.addToActivityLog();
        logPage.saveActivity();
        Assert.assertEquals(activity, String.valueOf(logPage.getFinalActivityName()),
                "Activity name values don't match");
        Assert.assertEquals(expectedResult, String.valueOf(logPage.getFinalCalories()),
                "Calories values don't match");
    }

    @Test(groups = {"Negative"})
    @Description("activity log negative test")
    public void activityLogNegativeTest() {
        String activity = "123";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        Assert.assertTrue(logPage.isNoResultsDisplayed());
    }
}
