package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeightLogTest extends BaseTest {


    @Test(groups = {"Smoke"})
    @Description("positive weight log test: add weight to log")
    public void addWeightParameterToLogTest() {
        String weightParameter = "60";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("WEIGHT");
        weightLogPage.waitForPageLoaded();
        weightLogPage.clickEditButton();
        weightLogPage.setWeightParameter(weightParameter);
        weightLogPage.clickSaveButton();
        weightLogPage.waitForPageLoaded();
        StringBuilder expectedResult = new StringBuilder(weightParameter);
        expectedResult.append(" kgs");
        Assert.assertEquals(weightLogPage.getWeightParameter(), String.valueOf(expectedResult),
                "Weight parameters don't match");




    }
}
