package pageMethods;

import configuration.Iproperties;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CommonsMethods {
    public static WebDriver driver;
    public static Iproperties props;
    public static String browserName;
    public static String os;

    public static void goToBaseUrlChrome() {
        os = System.getProperty("os");
        String pathChromedriver;
        switch (os) {
            case "linux":
                pathChromedriver = "src/test/resources/drivers/linux/chromedriver";
                break;
            default:
                pathChromedriver = "src/test/resources/drivers/windows/chromedriver.exe";
                break;
        }
        props = ConfigFactory.create(Iproperties.class);
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(props.baseUrl());
    }

    public static void goToBaseUrlFirefox() {
        os = System.getProperty("os");
        String pathGeckodriver;
        switch (os) {
            case "linux":
                pathGeckodriver = "src/test/resources/drivers/linux/geckodriver";
                break;
            default:
                pathGeckodriver = "src/test/resources/drivers/windows/geckodriver.exe";
                break;
        }
        props = ConfigFactory.create(Iproperties.class);
        System.setProperty("webdriver.gecko.driver", pathGeckodriver);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(props.baseUrl());
    }

    public static void goToBaseUrl() {
        browserName = System.getProperty("browser");
        switch (browserName) {
            case "chrome":
                goToBaseUrlChrome();
                break;
            case "firefox":
                goToBaseUrlFirefox();
                break;
            default:
                Assert.fail("Driver => " + browserName + " not available. Try with 'Chrome' or 'Firefox'");
        }
    }

    public static void takeScreenshots(String screenshotName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));
    }

    @After
    public static void afterTest(Scenario scenario) {
        System.out.println("FINALIZANDO TEST");
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
            }
            driver.quit();
        }
    }

    public static void elementClick(By element) {
        Awaitility.await().atMost(15, SECONDS).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(CommonsMethods.driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                Thread.sleep(500);
                CommonsMethods.driver.findElement(element).click();
                return true;
            } catch (Throwable throwable) {
                System.out.println("ERROR => " + throwable);
                return false;
            }
        });
    }

    public static void elementSendText(By element, String text) {
        WebDriverWait wait = new WebDriverWait(CommonsMethods.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        CommonsMethods.driver.findElement(element).sendKeys(text);
    }

    public static void scrollToElement(By element) {
        WebElement elemento = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elemento);
    }

    public static void checkPathFromUrl(String urlExpected) {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains(urlExpected));
        System.out.println("URL => " + url + " CONTAINS PATH => " + urlExpected);
    }

    public static void elementWaitDisplayed(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void pressKey(Keys key) {
        Actions a = new Actions(driver);
        a.sendKeys(key).build().perform();
    }

    public static String getElementText(By element) {
        return driver.findElement(element).getText();
    }

}