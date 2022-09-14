package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FoodLogTest extends BaseTest {
    String food = "Apple, baked";

    @Test(groups = {"Smoke", "TestWithDeletion"})
    @Description("positive food log test: add food to log")
    public void addFoodToLogTest() {
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
    public void removeFoodFromLogTest() {
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
    public void editFoodLogTest() {
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
        foodLogPage.editFoodAmountInFoodLog(newAmount, newCalories);
        Assert.assertEquals(newAmount, foodLogPage.getNewAmount(),
                "Actual and expected food amount do not match");
        Assert.assertEquals(newCalories, foodLogPage.getFinalCalories(),
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

    @Test(groups = {"Smoke"})
    @Description("Verify search test")
    public void verifySearchTest() {
        String searchWord = "Apple";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(searchWord);
        foodLogPage.waitForTableLoaded();
        List<String> actualResult = foodLogPage.getAllSearchResults();
        Assert.assertTrue(actualResult.stream().allMatch(result -> result.contains(searchWord)));
    }

}
