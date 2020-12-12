package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.Vehicle;
import co.edu.uniandes.miso4208.mileage.model.VehicleType;
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

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTest {

    private final String DEVICE_NAME = "Android Emulator";
    private final String AUTOMATION_NAME = "UiAutomator2";
    private final String APP_WAIT_ACTIVITY = "com.evancharlton.mileage.Mileage";
    private final String DEFAULT_TIMEOUTS = "20000";

    protected AndroidDriver<WebElement> driver;
    protected WebDriverWait wait;
    protected List<Vehicle> vehicles;

    @BeforeClass
    public void setUp() throws IOException {

        TestConfiguration config = TestConfiguration.getInstance();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("automationName", AUTOMATION_NAME);
        capabilities.setCapability("app", config.getAppUnderTestPath());
        capabilities.setCapability("appWaitActivity", APP_WAIT_ACTIVITY);
        capabilities.setCapability("adbExecTimeout", DEFAULT_TIMEOUTS);
        capabilities.setCapability("uiautomator2ServerLaunchTimeout", DEFAULT_TIMEOUTS);

        driver = new AndroidDriver<>(config.getAppiumServerUrl(), capabilities);
        wait = new WebDriverWait(driver, 2);

        loadDefault();

    }

    @AfterClass
    public void tearDown() {
        // driver.removeApp("com.evancharlton.mileage");
        driver.quit();
    }

    private void loadDefault() {

        VehicleType defaultVehicleType = new VehicleType();
        defaultVehicleType.setTitle("Car");
        defaultVehicleType.setDescription("Passenger car");

        Vehicle defaultVehicle = new Vehicle();
        defaultVehicle.setTitle("Default vehicle");
        defaultVehicle.setYear(2010);
        defaultVehicle.setMake("Android");
        defaultVehicle.setModel("Mileage");
        defaultVehicle.setDescription("Auto-generated vehicle");
        defaultVehicle.setVehicleType(defaultVehicleType);
        defaultVehicle.setCurrencySymbol("$");

        List <Vehicle> vehicles = new LinkedList<>();
        vehicles.add(defaultVehicle);

        this.vehicles = vehicles;

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

    public void pressPageDown() {
        driver.pressKey(new KeyEvent(AndroidKey.PAGE_UP));
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

    public void goBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
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