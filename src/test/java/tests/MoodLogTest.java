package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MoodLogTest extends BaseTest {

    @AfterMethod(onlyForGroups = {"TestWithDeletion"}, groups = {"Smoke"})
    public void deleteData() {
        moodLogPage.removeDataFromLog();
        driver.navigate().refresh();
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }


    @Test(groups = {"Smoke", "TestWithDeletion"})
    @Description("positive mood log test: edit mood diary")
    public void editMoodDiaryTest() {
        String mood = "Ate twice. Feel sad:(";
        loginPage.login(USERNAME, PASSWORD);
        homePage.waitForPageLoaded();
        homePage.chooseField("MOOD");
        moodLogPage.waitForPageLoaded();
        moodLogPage.clickEditButton();
        moodLogPage.editMoodLog(mood);
        moodLogPage.clickSaveButton();
        moodLogPage.waitForPageLoaded();
        Assert.assertEquals(moodLogPage.getMoodText(), mood,
                "Mood comments don't match");




    }
}
