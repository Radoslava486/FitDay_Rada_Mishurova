package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FoodLogTest extends BaseTest {
    String food = "Apple, baked";

    @Test(groups = {"Smoke"})
    @Description("food log test")
    public void addFoodToLogTest(){
        int calories = 180;
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        Assert.assertTrue(foodLogPage.isAlertDisplayed());
        Assert.assertEquals(Integer.parseInt(foodLogPage.getFinalCalories()), calories);
        Assert.assertTrue(foodLogPage.getFinalFoodName().contains(food));
    }

    @Test(groups = {"Smoke"})
    @Description("food log test")
    public void removeFoodFromLogTest(){
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.removeFoodFromLog();
        Assert.assertTrue(foodLogPage.isTableEmpty());
    }

    @Test(groups = {"Regression"})
    @Description("edit food log test")
    public void editFoodLogTest() throws InterruptedException {
        String newAmount = "2";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("FOOD");
        foodLogPage.waitForSearchInputLoaded();
        foodLogPage.searchFood(food);
        foodLogPage.waitForTableLoaded();
        foodLogPage.clickAdd();
        foodLogPage.editFoodLog();
        foodLogPage.editFoodAmountInFoodLog(newAmount);
        Thread.sleep(3000);//какой написать wait
        Assert.assertEquals(newAmount,foodLogPage.getNewAmount());

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
