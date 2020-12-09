package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.test.TestConfiguration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    private final String DEVICE_NAME = "Android Emulator";
    private final String AUTOMATION_NAME = "UiAutomator2";
    private final String APP_WAIT_ACTIVITY = "com.evancharlton.mileage.Mileage";
    protected final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();

    protected AndroidDriver<WebElement> driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUp() throws IOException {

        TestConfiguration config = TestConfiguration.getInstance();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("automationName", AUTOMATION_NAME);
        capabilities.setCapability("app", config.getAppUnderTestPath());
        capabilities.setCapability("appWaitActivity", APP_WAIT_ACTIVITY);

        driver = new AndroidDriver<>(config.getAppiumServerUrl(), capabilities);
        wait = new WebDriverWait(driver, 2);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public URL getServiceUrl () throws MalformedURLException {
        return new URL("http://localhost:4723/wd/hub");
    }

    public void waitAndClick(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public void waitAndClickByXPath(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void waitVisibility(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public void pressMenu() {
        driver.pressKey(new KeyEvent(AndroidKey.MENU));
    }

    public void waitASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public void type(String text, String id) {
        WebElement field = driver.findElementById(id);
        field.sendKeys(text);
    }

    public void typeByXpath(String text, String xpath) {
        WebElement field = driver.findElementByXPath(xpath);
        field.sendKeys(text);
    }

    public void click(String id) {
        WebElement field = driver.findElementById(id);
        field.click();
    }

    public void takeSnapshot(String filename) {
        File outputDirectory = new File(TestConfiguration.getInstance().getOutputDir(), "screenshots/");
        if (outputDirectory != null) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            outputDirectory.mkdirs();
            File destinationFile = new File(outputDirectory, filename + ".png");
            screenshotFile.renameTo(destinationFile);
        }
    }

}