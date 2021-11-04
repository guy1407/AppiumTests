import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CalculatorTest {

    static AppiumDriver driver;

    public static void main(String[] args) {

        System.out.println("Starting calculator");

        try {
            openCalculator();
        } catch (Exception ex) {
            System.out.println("Cause = " + ex.getCause());
            System.out.println("Message = " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public static void openCalculator() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        //samsung galaxy note 9:
        cap.setCapability("deviceName", "SM-N960F");

        //cap.setCapability("udid", "288979af33027ece");
        cap.setCapability("udid", "192.168.1.81:5555");

        cap.setCapability("platformName", "Android");

        cap.setCapability("platformVersion", "10");

        cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");

        cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver<MobileElement>(url, cap);

        System.out.println("Application started...");

        boolean bFoundElement = false;
        int t = 0;

        MobileElement clear = null;
        while( (!bFoundElement) && (t < 4000)) {

            try{
                clear = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_clear"));

                bFoundElement = (clear != null);
            }catch (Exception ex) {
                clear = null;

                bFoundElement = false;
            }

            Thread.sleep(1);
            t = t + 1;
        }




        MobileElement one = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_01"));

        MobileElement two = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_02"));

        MobileElement plus = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_add"));

        MobileElement equal = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal"));

        MobileElement result = (MobileElement) driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula"));


        clear.click();

        one.click();
        plus.click();
        two.click();
        equal.click();

        String calculation = result.getText();
        System.out.println("\n******\nResult is: " + calculation + "\n******");

        System.out.println("Completed !");
    }
}