package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BodyLogTest extends BaseTest {

    @AfterMethod(onlyForGroups = {"TestWithDeletion"}, groups = {"Smoke"})
    public void deleteData() {
        bodyLogPage.removeDataFromLog();
        driver.navigate().refresh();
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }

    @Test(groups = {"Smoke", "TestWithDeletion"})
    @Description("positive body log test: add parameters to log")
    public void addBodyParametersToLogTest() {
        String waistParameter = "60";
        String chestParameter = "90";
        String hipsParameter = "90";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("BODY");
        bodyLogPage.waitForPageLoaded();
        bodyLogPage.setHipsParameter(hipsParameter);
        bodyLogPage.setChestParameter(chestParameter);
        bodyLogPage.setWaistParameter(waistParameter);
        bodyLogPage.clickSaveButton();
        bodyLogPage.waitForDataSaved();
        Assert.assertEquals(bodyLogPage.getWaistParameter(), waistParameter,
                "Waist parameters don't match");
        Assert.assertEquals(bodyLogPage.getChestParameter(), chestParameter,
                "Chest parameters don't match");
        Assert.assertEquals(bodyLogPage.getHipsParameter(), hipsParameter,
                "Hips parameters don't match");



    }
}
