package ui_automation.utilities;

import org.openqa.selenium.WebDriver;

public class Driver {
    // private constructor
    private Driver(){

    }
    // private static reference variable of the class
    private static Driver driverInstance=new Driver();

    // create a getter method with return type of the class  => Encapsulation
    public static Driver getInstance(){
        return driverInstance;
    }

    // Thread Local is responsible to keep track of the webdrivers that created by different threads
    ThreadLocal<WebDriver> threadLocalDriver=new ThreadLocal<WebDriver>();


    // getDriver() is the instance method of our Driver class
    public WebDriver getDriver(){
        // first we are getting the driver from threadLocal
        WebDriver driver = threadLocalDriver.get();
        return driver;
    }

    //setDriver(Webdriver) is the instance method of our Driver class
    public  void setDriver(WebDriver driverParameter){
        // set the parameter driver into threadLocal
        threadLocalDriver.set(driverParameter);
    }
    //removeDriver() is the instance method of our Driver class
    public void removeDriver(){
        WebDriver driver = threadLocalDriver.get();
        driver.quit();
        threadLocalDriver.remove();

    }
}