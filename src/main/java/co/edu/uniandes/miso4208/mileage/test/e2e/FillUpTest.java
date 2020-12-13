package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.FillUp;
import co.edu.uniandes.miso4208.mileage.model.Vehicle;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class FillUpTest extends AbstractTest {

    public static final String FILLUP_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[1]";
    public static final String HISTORY_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[2]";
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
            {"Average fuel economy", "143.33 mpg"},
            {"Worst fuel economy", "115.78 mpg"},
            {"Best fuel economy", "184.67 mpg"},
            {"Average distance", "537.50 mi"},
            {"Minimum distance", "521.00 mi"},
            {"Maximum distance", "554.00 mi"},
            {"Average cost", "$18.83"},
            {"Minimum cost", "$12.00"},
            {"Maximum cost", "$27.00"},
            {"Total cost", "$56.50"},
            {"Cost last month", "$39.00"},
            {"Estimated cost per month", "$58.45 / mo"},
            {"Cost last year", "$39.00"},
            {"Estimated cost per year", "$711.12 / yr"},
            {"Average cost per mi", "$0.04 / mi"},
            {"Minimum cost per mi", "$0.02 / mi"},
            {"Maximum cost per mi", "$0.05 / mi"},
            {"Average price", "$4.50"},
            {"Minimum price", "$3.50"},
            {"Maximum price", "$6.00"},
            {"Smallest fillup", "3.00 Gallons"},
            {"Largest fillup", "5.00 Gallons"},
            {"Average fillup", "4.17 Gallons"},
            {"Total fuel", "12.50 Gallons"},
            {"Fuel per year", "157.33 Gallons / yr"}
    };

    public static final String EXPECTED_HISTORIES[][] = {
            {"11/29/20", "", "4.50 g", "6.00", "115.78 mpg"},
            {"11/15/20", "", "3.00 g", "4.00", "184.67 mpg"},
            {"11/1/20", "", "5.00 g", "3.50", ""}
    };

    public static final String EXPECTED_FILLUPS_1[][] = {
            {"Is partial?", "false"},
            {"Volume", "4.50 Gallons"},
            {"Odometer", "1175.00 mi"},
            {"Price per unit", "$6.00"},
            {"Total cost", "$27.00"},
            {"Distance", "521.00 mi"},
            {"Economy", "115.78 mpg"}
    };

    @Test
    public void registerFillUpTest() {

        Vehicle vehicle = vehicles.get(0);

        FillUp fillUp1 = new FillUp();
        fillUp1.setPricePerVolume(new BigDecimal(3.5));
        fillUp1.setVolume(new BigDecimal(5));
        fillUp1.setOdometer(new BigDecimal(100));
        fillUp1.setPartial(false);
        fillUp1.setMonth("Nov");
        fillUp1.setDay("1");
        fillUp1.setYear("2020");
        fillUp1.setComment("Nothing");

        goToFillUpTab();
        takeSnapshot("before-register-fillup");
        registerFillUp(vehicle, fillUp1);
        takeSnapshot("after-register-fillup");

        FillUp fillUp2 = new FillUp();
        fillUp2.setPricePerVolume(new BigDecimal(4));
        fillUp2.setVolume(new BigDecimal(3));
        fillUp2.setOdometer(new BigDecimal(654));
        fillUp2.setPartial(false);
        fillUp2.setMonth("Nov");
        fillUp2.setDay("15");
        fillUp2.setYear("2020");
        fillUp2.setComment("Terpel");

        goToFillUpTab();
        registerFillUp(vehicle, fillUp2);

        FillUp fillUp3 = new FillUp();
        fillUp3.setPricePerVolume(new BigDecimal(6));
        fillUp3.setVolume(new BigDecimal(4.5));
        fillUp3.setOdometer(new BigDecimal(1175));
        fillUp3.setPartial(false);
        fillUp3.setMonth("Nov");
        fillUp3.setDay("29");
        fillUp3.setYear("2020");
        fillUp3.setComment("Esso");

        goToFillUpTab();
        registerFillUp(vehicle, fillUp3);

        verifyFillUpsInHistory(vehicle);

    }

    @Test(dependsOnMethods = "registerFillUpTest")
    public void historyTest() {
        goToHistoryTab();

        List<WebElement> items = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");

        Assert.assertEquals(items.size(), EXPECTED_HISTORIES.length);

        for (int i = 0; i < items.size(); i++) {
            List<WebElement> labels = items.get(i).findElements(By.className("android.widget.TextView"));
            String expectedHistory[] = EXPECTED_HISTORIES[i];

            Assert.assertEquals(labels.size(), expectedHistory.length);

            for (int j = 0; j < labels.size(); j++) {
                Assert.assertEquals(labels.get(j).getText(), expectedHistory[j]);
            }

        }

    }

    @Test(dependsOnMethods = "registerFillUpTest")
    public void statisticsTest() {
        goToStatisticsTab();

        takeSnapshot("statistics");

        verifyStatistics();

    }

    private void registerFillUp(Vehicle vehicle, FillUp fillUp) {
        fillFillUpForm(fillUp);
        waitAndClick(SAVE_BUTTON_ID);
        waitASecond(); // TODO: wait visibility
        vehicle.getFillUps().add(fillUp);
    }

    private void goToFillUpTab() {
        waitAndClickByXPath(FILLUP_BUTTON_XPATH);
        waitASecond();
    }

    private void goToHistoryTab() {
        waitAndClickByXPath(HISTORY_BUTTON_XPATH);
        waitASecond();
    }

    private void goToStatisticsTab() {
        waitAndClickByXPath(STATISTICS_BUTTON_XPATH);
        waitASecond();
    }

    private void fillFillUpForm(FillUp fillUp) {
        type(fillUp.getPricePerVolume().toString().replace(',','.'), UNIT_PRICE_FIELD_ID);
        type(fillUp.getVolume().toString().replace(',','.'), AMOUNT_FIELD_ID);
        type(fillUp.getOdometer().toString().replace(',','.'), ODOMETER_FIELD_ID);
        typeByXpath(fillUp.getComment(), COMMENT_FIELD_XPATH);

        click("com.evancharlton.mileage:id/date");
        waitVisibility("android:id/customPanel");
        List<WebElement> dateFields = driver.findElementsById("android:id/numberpicker_input");
        fillDatePicker(dateFields.get(0), fillUp.getMonth());
        fillDatePicker(dateFields.get(1), fillUp.getDay());
        fillDatePicker(dateFields.get(2), fillUp.getYear());
        click("android:id/button1");
    }

    private void fillDatePicker(WebElement element, String text) {

        while (!element.getText().equals(text)) {

            new TouchAction(driver)
                    .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
                    .release()
                    .perform();

            driver.getKeyboard().sendKeys(Keys.DELETE);

            driver.getKeyboard().sendKeys(text);

        }

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

        takeSnapshot("history");

        verifyFristHistory();

    }

    private void verifyFristHistory() {

        waitAndClickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]");

        Assert.assertEquals(driver.findElementById("com.evancharlton.mileage:id/header").getText(),
                "November 29, 2020");

        List <WebElement> items = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout");

        Assert.assertEquals(items.size(), EXPECTED_FILLUPS_1.length);

        for (int i = 0; i < items.size(); i++) {
            List<WebElement> labels = items.get(i).findElements(By.className("android.widget.TextView"));
            Assert.assertEquals(labels.get(0).getText(), EXPECTED_FILLUPS_1[i][0]);
            Assert.assertEquals(labels.get(1).getText(), EXPECTED_FILLUPS_1[i][1]);
        }

        takeSnapshot("fillup-summary");

        goBack();

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
