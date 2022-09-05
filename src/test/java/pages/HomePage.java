package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2

public class HomePage extends BasePage {
    private final static String logCategoryLocator = "//li/a[text()='%s']";
    protected final By homeButton = By.xpath("//a/span[text()='HOME']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeButtonDisplayed() {
        return driver.findElement(homeButton).isDisplayed();
    }

    @Override
    public void waitForPageLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(homeButton);
    }

    public void chooseField(String label) {
        log.info(String.format("Opening %s", label));
        WebElement button = driver.findElement(By.xpath(String.format(logCategoryLocator, label)));
        jsClick(button);
    }


}
