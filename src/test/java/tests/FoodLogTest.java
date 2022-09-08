package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class FoodLogTest extends BaseTest {
    String food = "Apple, baked";
    @AfterMethod(onlyForGroups = {"TestWithDeletion"})
    public void deleteData() {
        logPage.removeActivityFromLog();
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }

    @Test(groups = {"Smoke", "TestWithDeletion"})
    @Description("positive food log test: add food to log")
    public void addFoodToLogTest(){
        int calories = 180;
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        Assert.assertTrue(foodLogPage.isAlertDisplayed(),
                "Alert isn't displayed");
        Assert.assertEquals(Integer.parseInt(foodLogPage.getFinalCalories()), calories,
                "Actual and expected calories values do not match");
        Assert.assertEquals(foodLogPage.getFinalFoodName(), food,
        "Actual and expected product name values do not match");
    }

    @Test(groups = {"Smoke"})
    @Description("positive food log test: remove food from log")
    public void removeFoodFromLogTest(){
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        foodLogPage.removeFoodFromLog();
        Assert.assertTrue(foodLogPage.isTableEmpty(),
                "Food table is not empty");
    }

    @Test(groups = {"Regression", "TestWithDeletion"})
    @Description("edit food log test")
    public void editFoodLogTest() throws InterruptedException {
        String newAmount = "2";
        String newCalories = "359";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        foodLogPage.editFoodLog();
        foodLogPage.editFoodAmountInFoodLog(newAmount);
        Thread.sleep(5000);
        //какой написать wait
        Assert.assertEquals(newAmount,foodLogPage.getNewAmount(),
                "Actual and expected food amount do not match");
        Assert.assertEquals(newCalories,foodLogPage.getFinalCalories(),
                "Actual and expected calories values do not match");


    }

    @Test(groups = {"Negative"})
    @Description("food log negative test: add food to log")
    public void addFoodToLogNegativeTest() {
        String food = "qwe";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        Assert.assertTrue(foodLogPage.isNoResultsDisplayed(),
                "Search is successful");
    }

    }
