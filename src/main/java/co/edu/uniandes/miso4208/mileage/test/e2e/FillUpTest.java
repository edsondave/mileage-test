package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.FillUp;
import co.edu.uniandes.miso4208.mileage.model.Vehicle;
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
                    String.format( "%.2f", fillUp.getVolume()) + " g");

            Assert.assertEquals(listItems.get(i).findElement(By.id("com.evancharlton.mileage:id/price")).getText(),
                    String.format( "%.2f", fillUp.getPricePerVolume()));

        }

    }

    private void verifyStatistics() {
        List <WebElement> listItems = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");

        // 0
        Assert.assertEquals(listItems.get(0).findElement(By.id("android:id/text1")).getText(),
                "Average fuel economy");

        Assert.assertEquals(listItems.get(0).findElement(By.id("android:id/text2")).getText(),
                "26.67 mpg");

        // 1
        Assert.assertEquals(listItems.get(1).findElement(By.id("android:id/text1")).getText(),
                "Worst fuel economy");

        Assert.assertEquals(listItems.get(1).findElement(By.id("android:id/text2")).getText(),
                "22.22 mpg");

        // 2
        Assert.assertEquals(listItems.get(2).findElement(By.id("android:id/text1")).getText(),
                "Best fuel economy");

        Assert.assertEquals(listItems.get(2).findElement(By.id("android:id/text2")).getText(),
                "33.33 mpg");

        // 3
        Assert.assertEquals(listItems.get(3).findElement(By.id("android:id/text1")).getText(),
                "Average distance");

        Assert.assertEquals(listItems.get(3).findElement(By.id("android:id/text2")).getText(),
                "100.00 mi");

        // 4
        Assert.assertEquals(listItems.get(4).findElement(By.id("android:id/text1")).getText(),
                "Minimum distance");

        Assert.assertEquals(listItems.get(4).findElement(By.id("android:id/text2")).getText(),
                "100.00 mi");

        // 5
        Assert.assertEquals(listItems.get(5).findElement(By.id("android:id/text1")).getText(),
                "Maximum distance");

        Assert.assertEquals(listItems.get(5).findElement(By.id("android:id/text2")).getText(),
                "100.00 mi");

        // 6
        Assert.assertEquals(listItems.get(6).findElement(By.id("android:id/text1")).getText(),
                "Average cost");

        Assert.assertEquals(listItems.get(6).findElement(By.id("android:id/text2")).getText(),
                "$18.83");

        // 7
        Assert.assertEquals(listItems.get(7).findElement(By.id("android:id/text1")).getText(),
                "Minimum cost");

        Assert.assertEquals(listItems.get(7).findElement(By.id("android:id/text2")).getText(),
                "$12.00");

        // 8
        Assert.assertEquals(listItems.get(8).findElement(By.id("android:id/text1")).getText(),
                "Maximum cost");

        Assert.assertEquals(listItems.get(8).findElement(By.id("android:id/text2")).getText(),
                "$27.00");

        // 9
        Assert.assertEquals(listItems.get(9).findElement(By.id("android:id/text1")).getText(),
                "Total cost");

        Assert.assertEquals(listItems.get(9).findElement(By.id("android:id/text2")).getText(),
                "$56.50");

        // 10
        Assert.assertEquals(listItems.get(10).findElement(By.id("android:id/text1")).getText(),
                "Cost last month");

        Assert.assertEquals(listItems.get(10).findElement(By.id("android:id/text2")).getText(),
                "$39.00");

        // 11
        Assert.assertEquals(listItems.get(11).findElement(By.id("android:id/text1")).getText(),
                "Estimated cost per month");

        Assert.assertEquals(listItems.get(11).findElement(By.id("android:id/text2")).getText(),
                "$1695.00 / mo");

        // 12
        Assert.assertEquals(listItems.get(12).findElement(By.id("android:id/text1")).getText(),
                "Cost last year");

        Assert.assertEquals(listItems.get(12).findElement(By.id("android:id/text2")).getText(),
                "$39.00");

        // 13
        Assert.assertEquals(listItems.get(13).findElement(By.id("android:id/text1")).getText(),
                "Estimated cost per year");

        Assert.assertEquals(listItems.get(13).findElement(By.id("android:id/text2")).getText(),
                "$20622.50 / yr");

        // 14
        Assert.assertEquals(listItems.get(14).findElement(By.id("android:id/text1")).getText(),
                "Average cost per mi");

        Assert.assertEquals(listItems.get(14).findElement(By.id("android:id/text2")).getText(),
                "$0.20 / mi");

        // 15
        Assert.assertEquals(listItems.get(15).findElement(By.id("android:id/text1")).getText(),
                "Minimum cost per mi");

        Assert.assertEquals(listItems.get(15).findElement(By.id("android:id/text2")).getText(),
                "$0.12 / mi");

        // 16
        Assert.assertEquals(listItems.get(16).findElement(By.id("android:id/text1")).getText(),
                "Maximum cost per mi");

        Assert.assertEquals(listItems.get(16).findElement(By.id("android:id/text2")).getText(),
                "$0.27 / mi");

    }

}
