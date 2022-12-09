package utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
//cross browser
public class CommonMethods {
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication(){
    ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
    switch (ConfigReader.getPropertyValue("browser")){
        case "chrome":
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            break;
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
            break;
        default:
            throw new RuntimeException("Invalid browser name");
    }
    driver.manage().window().maximize();
    driver.get(ConfigReader.getPropertyValue("url"));
    driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }
    public static void closeBrowser(){
        driver.quit();
    }
    //use this method instead of sendKeys method throughout the framework
    public static void sendText(WebElement element, String textToSend){//means sendKeys method
      element.clear();
      element.sendKeys(textToSend);
    }
    //to wait for the element to be visible
    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }
    //will wait for the element to be clickable
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    //will perform click operation, but before click perform, it will wait
    //for the element clickable
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }
    //this method return JavascriptExecutor Object
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        return js;
    }
    //this function will perfor click on element using javascript executor
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }
    //selecting the dropdown using text
    public static void selectDropDwon(WebElement element, String text){
        Select s=new Select(element);
        s.selectByVisibleText(text);
    }

}
