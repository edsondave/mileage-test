package co.edu.uniandes.miso4208.mileage.test.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DefaultDataTest extends AbstractTest {

    public static final String FILLUP_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[1]";

    @Test
    public void tabBarTest() {

        List <WebElement> tabs = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout");

        Assert.assertEquals(tabs.get(0).findElement(By.className("android.widget.TextView")).getText(), "Fillup");
        Assert.assertEquals(tabs.get(1).findElement(By.className("android.widget.TextView")).getText(), "History");
        Assert.assertEquals(tabs.get(2).findElement(By.className("android.widget.TextView")).getText(), "Statistics");
        Assert.assertEquals(tabs.get(3).findElement(By.className("android.widget.TextView")).getText(), "Vehicles");

    }

    @Test
    public void defaultFillupTabTest() {
        Assert.assertEquals(driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView").getText(), "Fillup information");
        Assert.assertEquals(driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText(), "Extra data");
    }

    private void goToFillUpTab() {
        waitAndClickByXPath(FILLUP_BUTTON_XPATH);
        waitASecond();
    }

}
