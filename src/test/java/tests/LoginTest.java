package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"Smoke"})
    @Description("positive Login Test")
    public void positiveLoginTest() {
        loginPage.setUsername(USERNAME);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isHomeButtonDisplayed(),
                "Home page is not open");
    }

    @Test(dataProvider = "negativeLoginData", groups = {"Negative", "Regression"})
    @Description("negative Login Test")
    public void negativeLoginTest(String userName, String password, String errorMessage) {
        loginPage.setUsername(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessageText(), errorMessage,
                "Error and error message do not match");
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", PASSWORD, "Username is required."},
                {USERNAME, "", "Password is required."},
                {"", "", "Username is required."},
        };
    }

}
