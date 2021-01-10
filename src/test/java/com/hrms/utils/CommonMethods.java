package com.hrms.utils;

import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public static void setup(){
        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigsReader.getPropertyValue("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                //System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser");
        }
        driver.get(ConfigsReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

    }
    @AfterMethod(alwaysRun = true)
    public static void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
//    @Test
//    public void test(){
//        System.out.println(System.getProperty("user.dir"));//,os.name,user.name
//    }

    /**
     * this method will clear a textbox and send text to it
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element,String textToSend){
        element.clear();
        element.sendKeys();
    }

    /**
     * This method will return an objct of Explicit wait with time set to 20 sec
     * @return
     */
    public static WebDriverWait getWait (){
        WebDriverWait wait=new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
        return wait;
    }

    /**
     * this method will wait until given element becomes clickable
     * @param element
     */
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will wait till and then click
     * @param element
     */
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }
}
