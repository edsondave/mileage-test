package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.FillUp;
import co.edu.uniandes.miso4208.mileage.model.Vehicle;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class FillUpTest extends AbstractTest {

    public static final String FILLUP_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[1]";
    public static final String STATISTICS_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[3]";
    public static final String VEHICLE_SELECT_ID = "com.evancharlton.mileage:id/vehicle";
    public static final String UNIT_PRICE_FIELD_ID = "com.evancharlton.mileage:id/price";
    public static final String AMOUNT_FIELD_ID = "com.evancharlton.mileage:id/volume";
    public static final String ODOMETER_FIELD_ID = "com.evancharlton.mileage:id/odometer";
    public static final String DATE_BUTTON_ID = "com.evancharlton.mileage:id/date";
    public static final String PARTIAL_CHECKBOX_FIELD_ID = "com.evancharlton.mileage:id/partial";
    public static final String COMMENT_FIELD_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText";
    public static final String SAVE_BUTTON_ID = "com.evancharlton.mileage:id/save_btn";
    public static final String EXPECTED_STATISTICS[][] = {
            {"Average fuel economy", "26.67 mpg"},
            {"Worst fuel economy", "22.22 mpg"},
            {"Best fuel economy", "33.33 mpg"},
            {"Average distance", "100.00 mi"},
            {"Minimum distance", "100.00 mi"},
            {"Maximum distance", "100.00 mi"},
            {"Average cost", "$18.83"},
            {"Minimum cost", "$12.00"},
            {"Maximum cost", "$27.00"},
            {"Total cost", "$56.50"},
            {"Cost last month", "$39.00"},
            {"Estimated cost per month", "$1695.00 / mo"},
            {"Cost last year", "$39.00"},
            {"Estimated cost per year", "$20622.50 / yr"},
            {"Average cost per mi", "$0.20 / mi"},
            {"Minimum cost per mi", "$0.12 / mi"},
            {"Maximum cost per mi", "$0.27 / mi"},
            {"Average price", "$4.50"},
            {"Minimum price", "$3.50"},
            {"Maximum price", "$6.00"},
            {"Smallest fillup", "3.00 Gallons"},
            {"Largest fillup", "5.00 Gallons"},
            {"Average fillup", "4.17 Gallons"},
            {"Total fuel", "12.50 Gallons"},
            {"Fuel per year", "4562.50 Gallons / yr"}
    };

    @Test
    public void registerFillUpTest() {

        Vehicle vehicle = vehicles.get(0);

        FillUp fillUp1 = new FillUp();
        fillUp1.setPricePerVolume(new BigDecimal(3.5));
        fillUp1.setVolume(new BigDecimal(5));
        fillUp1.setOdometer(new BigDecimal(100));
        fillUp1.setPartial(false);
        fillUp1.setComment("Nothing");

        goToFillUpTab();
        takeSnapshot("before-register-fillup");
        registerFillUp(vehicle, fillUp1);
        takeSnapshot("after-register-fillup");

        FillUp fillUp2 = new FillUp();
        fillUp2.setPricePerVolume(new BigDecimal(4));
        fillUp2.setVolume(new BigDecimal(3));
        fillUp2.setOdometer(new BigDecimal(200));
        fillUp2.setPartial(false);
        fillUp2.setComment("Terpel");

        goToFillUpTab();
        registerFillUp(vehicle, fillUp2);

        FillUp fillUp3 = new FillUp();
        fillUp3.setPricePerVolume(new BigDecimal(6));
        fillUp3.setVolume(new BigDecimal(4.5));
        fillUp3.setOdometer(new BigDecimal(300));
        fillUp3.setPartial(false);
        fillUp3.setComment("Esso");

        goToFillUpTab();
        registerFillUp(vehicle, fillUp3);

        verifyFillUpsInHistory(vehicle);

        takeSnapshot("history");

    }

    @Test(dependsOnMethods = "registerFillUpTest")
    public void statisticsTest() {
        goToStatisticsTab();

        verifyStatistics();

        takeSnapshot("statistics");

    }

    private void registerFillUp(Vehicle vehicle, FillUp fillUp) {
        fillFillUpForm(fillUp);
        click(SAVE_BUTTON_ID);
        waitASecond(); // TODO: wait visibility
        vehicle.getFillUps().add(fillUp);
    }

    private void goToFillUpTab() {
        waitAndClickByXPath(FILLUP_BUTTON_XPATH);
        waitASecond();
    }

    private void goToStatisticsTab() {
        waitAndClickByXPath(STATISTICS_BUTTON_XPATH);
        waitASecond();
    }

    private void fillFillUpForm(FillUp fillUp) {
        type(fillUp.getPricePerVolume().toString(), UNIT_PRICE_FIELD_ID);
        type(fillUp.getVolume().toString(), AMOUNT_FIELD_ID);
        type(fillUp.getOdometer().toString(), ODOMETER_FIELD_ID);
        typeByXpath(fillUp.getComment(), COMMENT_FIELD_XPATH);
    }

    private void verifyFillUpsInHistory(Vehicle vehicle) {

        List<FillUp> fillUps = vehicle.getFillUps();
        List <WebElement> listItems = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");

        Assert.assertEquals(listItems.size(), fillUps.size());

        for (int i = 0; i < listItems.size(); i++) {
            FillUp fillUp = fillUps.get(listItems.size() - i - 1);

            Assert.assertEquals(listItems.get(i).findElement(By.id("com.evancharlton.mileage:id/volume")).getText(),
                    String.format( "%.2f", fillUp.getVolume()).replace(',', '.') + " g");

            Assert.assertEquals(listItems.get(i).findElement(By.id("com.evancharlton.mileage:id/price")).getText(),
                    String.format( "%.2f", fillUp.getPricePerVolume()).replace(',', '.'));

        }

    }

    private void verifyStatistics() {

        List <WebElement> listItems = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");;

        int i = 0;
        for (String expectedParameter[]: EXPECTED_STATISTICS) {

            WebElement item = listItems.get(i++);
            Assert.assertEquals(item.findElement(By.id("android:id/text1")).getText(), expectedParameter[0]);
            Assert.assertEquals(item.findElement(By.id("android:id/text2")).getText(), expectedParameter[1]);

            if (i == listItems.size()) {
                swipeUp();
                listItems = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");
                i = 0;
                do {
                    item = listItems.get(i++);
                } while(!item.findElement(By.id("android:id/text1")).getText().equals(expectedParameter[0]));
            }

        }

    }

    private void swipeUp() {
        new TouchAction(driver)
                .press(PointOption.point(160, 500))
                .moveTo(PointOption.point(160, 150))
                .release()
                .perform();
    }

}
