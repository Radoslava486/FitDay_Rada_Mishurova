package tests;


import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActivityLogTest extends BaseTest {
    String activity = "fast ballroom dancing";

    @Test(groups = {"Smoke"})
    @Description("activity log test")
    public void activityLogPositiveTest() {
        String testTime = "35";
        int caloriesBurning = 5;
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.searchActivity(activity);
        logPage.setActivityTime(testTime);
        int expectedResult = caloriesBurning * Integer.parseInt(testTime) + 1;
        Assert.assertEquals(Integer.parseInt(logPage.getCaloriesFromTable(activity)), expectedResult,
                "Calories values don't match");
        logPage.clickAdd();
        logPage.waitForCalculationLoaded();
        logPage.addToActivityLog();
        logPage.waitForSearchInputLoaded();
        Assert.assertEquals(activity, String.valueOf(logPage.getFinalActivityName()),
                "Activity name values don't match");
        Assert.assertEquals(expectedResult, Integer.parseInt(logPage.getFinalCalories()),
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

    @Test(groups = {"Regression"})
    @Description("edit activity log test")
    public void editActivityLogTest() throws InterruptedException {
        String newTime = "40";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.editActivityLog();
        logPage.editTimeInActivityLog(newTime);
        Thread.sleep(3000);//какой написать wait
        Assert.assertEquals(newTime,logPage.getNewTime());

    }
    @Test(groups = {"Smoke"})
    @Description("activity log test")
    public void removeActivityFromLogTest(){
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("ACTIVITY");
        logPage.waitForSearchInputLoaded();
        logPage.removeActivityFromLog();
        Assert.assertTrue(logPage.isTableEmpty());
    }

}
