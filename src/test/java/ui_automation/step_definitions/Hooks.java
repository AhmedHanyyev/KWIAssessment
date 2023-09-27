package ui_automation.step_definitions;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ui_automation.utilities.BrowserFactory;
import ui_automation.utilities.Driver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public WebDriver driver=null;

    @Before
    public void setUp(){
        driver= BrowserFactory.createInstance(); // it will decide the type of browser
        // driver = new ChromeDriver();
        // It will return MiddleMan driver from JVM and set browser that we have in ThreadLocal
        try {
            Driver.getInstance().setDriver(driver);
            driver = Driver.getInstance().getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (NoSuchSessionException e) {
            driver.quit();
            Driver.getInstance().setDriver(driver);
            driver = Driver.getInstance().getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
//        driver.manage().window().maximize(); this line was commented since in a maximized version Hamburger button getting transformed
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // we are enforcing immutability and preventing screenshot from accidental change
            final byte[] screenshot = ((TakesScreenshot) Driver.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
        }
      Driver.getInstance().removeDriver(); //This line is for quiting driver and closing threadLocal
    }
}