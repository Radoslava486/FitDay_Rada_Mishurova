package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FoodLogTest extends BaseTest {

    @Test(groups = {"Smoke"})
    @Description("food log test")
    public void addFoodToLogTest(){
        String food = "Apple, baked";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        Assert.assertTrue(foodLogPage.isAlertDisplayed());
        Assert.assertEquals(foodLogPage.getFinalCalories(), foodLogPage.getCalories());
        Assert.assertTrue(foodLogPage.getFinalFoodName().contains(food));
    }

    @Test(groups = {"Smoke"})
    @Description("food log test")
    public void removeFoodFromLogTest(){
        String food = "Apple, baked";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.removeFoodFromLog();
        Assert.assertTrue(foodLogPage.isTableEmpty());

    }

    @Test(groups = {"Negative"})
    @Description("food log negative test")
    public void addFoodToLogNegativeTest() {
        String food = "qwe";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        Assert.assertTrue(foodLogPage.isNoResultsDisplayed());
    }

    }
