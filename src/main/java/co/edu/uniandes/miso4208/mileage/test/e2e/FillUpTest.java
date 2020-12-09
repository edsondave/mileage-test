package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.FillUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class FillUpTest extends AbstractTest {

    public static final String FILLUP_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[1]";
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

        FillUp fillUp = new FillUp();
        fillUp.setPricePerGallons(new BigDecimal(3.5));
        fillUp.setGallons(new BigDecimal(5));
        fillUp.setOdometer(new BigDecimal(2.5));
        fillUp.setFull(false);
        fillUp.setComment("Nothing");

        takeSnapshot("before-register-fillup");
        registerFillUp(fillUp);
        takeSnapshot("after-register-fillup");
        Assert.assertTrue(verifyFillUpInHistory(fillUp));
    }

    private void registerFillUp(FillUp fillUp) {
        goToFillUpTab();
        fillFillUpForm(fillUp);
        click(SAVE_BUTTON_ID);
        waitASecond();
        // TODO: wait visibility
    }

    private void goToFillUpTab() {
        waitAndClickByXPath(FILLUP_BUTTON_XPATH);
        waitASecond();
    }

    private void fillFillUpForm(FillUp fillUp) {
        type(fillUp.getPricePerGallons().toString(), UNIT_PRICE_FIELD_ID);
        type(fillUp.getGallons().toString(), AMOUNT_FIELD_ID);
        type(fillUp.getOdometer().toString(), ODOMETER_FIELD_ID);
        typeByXpath(fillUp.getComment(), COMMENT_FIELD_XPATH);
    }

    private boolean verifyFillUpInHistory(FillUp fillUp) {

        waitVisibility("com.evancharlton.mileage:id/volume");

        return driver.findElementById("com.evancharlton.mileage:id/volume").getText().equals("5.00 g");

        /*
        List <WebElement> listItems = driver.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout");
        for (int i = 0; i < listItems.size(); i++) {
            List <WebElement> textViews = listItems.get(i).findElements(By.className("android.widget.TextView"));
            if (textViews.get(1).getText().equals(fillUp.getGallons().toString() + " g")) {
                return true;
            }
        }
        return false;

         */
    }

}
