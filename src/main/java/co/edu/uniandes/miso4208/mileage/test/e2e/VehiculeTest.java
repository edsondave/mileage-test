package co.edu.uniandes.miso4208.mileage.test.e2e;

import co.edu.uniandes.miso4208.mileage.model.Vehicle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VehiculeTest extends AbstractTest {

    public static final String VEHICLE_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[4]";
    public static final String ADD_NEW_VEHICLE_BUTTON_XPATH = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[1]";
    public static final String TITLE_FIELD_ID = "com.evancharlton.mileage:id/title";
    public static final String YEAR_FIELD_ID = "com.evancharlton.mileage:id/year";
    public static final String MAKE_FIELD_ID = "com.evancharlton.mileage:id/make";
    public static final String MODEL_FIELD_ID = "com.evancharlton.mileage:id/model";
    public static final String DESCRIPTION_FIELD_ID = "com.evancharlton.mileage:id/description";
    public static final String TYPE_SELECT_ID = "com.evancharlton.mileage:id/type";
    public static final String DISTANCE_UNIT_ID = "com.evancharlton.mileage:id/distance";
    public static final String VOLUME_UNIT_ID = "com.evancharlton.mileage:id/volume";
    public static final String ECONOMY_UNIT_ID = "com.evancharlton.mileage:id/economy";
    public static final String CURRENCY_SYMBOL_FIELD_ID = "com.evancharlton.mileage:id/currency";
    public static final String ADD_VEHICLE_BUTTON_ID = "com.evancharlton.mileage:id/save_btn";
    public static final String TABS_BAR_ID = "android:id/tabs";
    public static final String LIST_ID = "android:id/list";
    public static final String LIST_ITEM_CLASS_NAME = "android.widget.TwoLineListItem";

    @Test
    public void createVehicleTest() {

        Vehicle vehicle = new Vehicle();
        vehicle.setTitle("My Renault");
        vehicle.setYear(1986);
        vehicle.setMake("Renault");
        vehicle.setModel("4");
        vehicle.setDescription("My first car.");

        createVehicle(vehicle);

        Assert.assertTrue(verifyVehicleInList(vehicle));

    }

    private void createVehicle(Vehicle vehicle) {
        goToVehicleTab();
        goToCreateVehicleForm();
        takeSnapshot("before-create-vehicle");
        fillVehicleForm(vehicle);
        click(ADD_VEHICLE_BUTTON_ID);
        waitVisibility(TABS_BAR_ID);
        takeSnapshot("after-create-vehicle");
    }

    private void goToVehicleTab() {
        waitAndClickByXPath(VEHICLE_BUTTON_XPATH);
        waitASecond();
    }

    private void goToCreateVehicleForm() {
        pressMenu();
        waitAndClickByXPath(ADD_NEW_VEHICLE_BUTTON_XPATH);
        waitVisibility(TITLE_FIELD_ID);
    }

    private void fillVehicleForm(Vehicle vehicle) {
        type(vehicle.getTitle(), TITLE_FIELD_ID);
        type(vehicle.getYear().toString(), YEAR_FIELD_ID);
        type(vehicle.getMake(), MAKE_FIELD_ID);
        type(vehicle.getModel(), MODEL_FIELD_ID);
        type(vehicle.getDescription(), DESCRIPTION_FIELD_ID);
    }

    private boolean verifyVehicleInList(Vehicle vehicle) {
        List <WebElement> listItems = driver.findElementsByClassName(LIST_ITEM_CLASS_NAME);
        for (int i = 0; i < listItems.size(); i++) {
            List <WebElement> textViews = listItems.get(i).findElements(By.className("android.widget.TextView"));
            if (textViews.get(0).getText().equals(vehicle.getTitle())
                    && textViews.get(1).getText().equals(vehicle.getDescription())) {
                return true;
            }
        }
        return false;
    }


}
