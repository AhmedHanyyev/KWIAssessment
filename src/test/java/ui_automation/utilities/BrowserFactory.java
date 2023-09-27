package ui_automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserFactory {
    static WebDriver driver;
        public static WebDriver createInstance() {
            try {
                if (driver == null) {
                    // if browser type not specified, create chrome
                    if(System.getProperty("browser")==null){
                        // WebDriver Manager way of setting property and having chromedriver ready
                        WebDriverManager.chromedriver().setup();
                        /**
                         * Chrome options provide a way to customize the behavior
                         * and settings of the Google Chrome Browser like
                         * controlling certificate errors
                         * managing cookies
                         * download directory
                         * handling notifications and so on.
                         * Some options can be added directly addArguments()
                         * Some need to be added as key/value
                         */
                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"/src/test/resources/testData/Downloads");

                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*");
                        options.addArguments("--ignore-ssl-errors=yes");
                        options.addArguments("--ignore-certificate-errors");
                        options.setExperimentalOption("prefs", chromePrefs);

                        // once we have driver ready and customized,
                        // we can instantiate driver by passing the options to constructor
                        driver = new ChromeDriver(options);

                    }
                    else {
                        switch (System.getProperty("browser")) {
                            case "chrome-headless":
                                WebDriverManager.chromedriver().setup();
                                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                                break;
                            case "chromeRemote":
                                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                                chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\Downloads");
                                ChromeOptions chrOptions = new ChromeOptions();
                                chrOptions.addArguments("--ignore-ssl-errors=yes");
                                chrOptions.addArguments("--ignore-certificate-errors");
                                chrOptions.setExperimentalOption("prefs", chromePrefs);
                                try {
                                    driver = new RemoteWebDriver(new URL("http://3.92.26.2:4444/wd/hub"), chrOptions);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "firefox":
                                WebDriverManager.firefoxdriver().setup();
                                driver = new FirefoxDriver();
                                break;
                            case "firefox-headless":
                                WebDriverManager.firefoxdriver().setup();
                                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                                break;
                            case "firefoxRemote":
                                FirefoxOptions firOptions = new FirefoxOptions();
                                try {
                                    driver = new RemoteWebDriver(new URL("http://3.92.26.2:4444/wd/hub"), firOptions);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "ie":
                                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.iedriver().setup();
                                driver = new InternetExplorerDriver();
                                break;

                            case "edge":
                                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.edgedriver().setup();
                                driver = new EdgeDriver();
                                break;

                            case "safari":
                                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.getInstance(SafariDriver.class).setup();
                                driver = new SafariDriver();
                                break;
                            default:
                                WebDriverManager.chromedriver().setup();
                                driver = new ChromeDriver();
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return driver;
        }
    }