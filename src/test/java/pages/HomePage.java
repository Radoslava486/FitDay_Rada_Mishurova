package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2

public class HomePage extends BasePage {
    private final static String LOG_CATEGORY_LOCATOR = "//li/a[text()='%s']";
    protected final By HOME_BUTTON = By.xpath("//a/span[text()='HOME']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeButtonDisplayed() {
        return driver.findElement(HOME_BUTTON).isDisplayed();
    }

    @Override
    public void waitForPageLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(HOME_BUTTON);
    }


    public void chooseField(String label) {
        log.info(String.format("Opening %s", label));
        WebElement button = driver.findElement(By.xpath(String.format(LOG_CATEGORY_LOCATOR, label)));
        jsClick(button);
    }


}
