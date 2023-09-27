package ui_automation.step_definitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import ui_automation.pages.KWIPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

import java.util.Set;

/*This class is a Step Definitions from feature files where actually automation is getting performed*/
public class KWISteps {
    KWIPage kwiPage = new KWIPage();
    JavascriptExecutor jse = (JavascriptExecutor) Driver.getInstance().getDriver();

    @Given("I navigated to KWI application")
    public void i_navigated_to_KWI_application() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "kwi.url");
        Driver.getInstance().getDriver().get(url);
    }

    @When("I should be able to see a text RETAIL OMNICHANNEL PLATFORM")
    public void i_click_button() {
        String verifyTextRetailOmnichannel = kwiPage.textRetail.getText();
        System.out.println("TEXT FROM WEBSITE! : " + verifyTextRetailOmnichannel);
    }

    @Then("I scroll down and verify text ALL-IN-ONE OMNICHANNEL SOLUTION")
    public void i_scroll_down_and_verify_text_ALL_IN_ONE_OMNICHANNEL_SOLUTION() {
        jse.executeScript("arguments[0].scrollIntoView();", kwiPage.textAllInOne);
        String verifyTextAllInOne = kwiPage.textAllInOne.getText();
        System.out.println("TEXT FROM WEBSITE! : " + verifyTextAllInOne);
    }

    @Then("I scroll down and verify phone and email")
    public void i_scroll_down_and_verify_phone_and_email() throws InterruptedException {
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        for (WebElement contacts : kwiPage.contactUs) {
            System.out.println("CONATCTS:  " + contacts.getText());
        }
        jse.executeScript("window.scrollTo(0, 0)");
    }

    @Then("I click on hamburger menu, click on Platform & Services and then Loyalty")
    public void i_click_on_hamburger_menu_click_on_Platform_Services_and_then_Loyalty() throws InterruptedException {
        kwiPage.hamburgerBtn.click();
        kwiPage.buttonPlatformServices.click();
        kwiPage.buttonLoyalty.click();
    }

    @Then("I should be able to scroll down to Get More Control")
    public void i_should_be_able_to_scroll_down_to_Get_More_Control() {
        jse.executeScript("arguments[0].scrollIntoView();", kwiPage.getMoreControl);
    }

    @Then("I print out paragraph on Get More Section")
    public void i_print_out_paragraph_on_Get_More_Section() {
        for (WebElement paragraph : kwiPage.paragraphs) {
            System.out.println("TEXT FROM PARAGRAPH: " + jse.executeScript("return arguments[0].innerText;", paragraph));
        }
    }

    @Then("I scroll down and Ebooks and click on '9' Noble Truth of Modern Retailinig")
    public void i_scroll_down_and_Ebooks_and_click_on_Noble_Truth_of_Modern_Retailinig() {

        jse.executeScript("arguments[0].click();", kwiPage.eBook);
        jse.executeScript("window.scrollBy(0,250)");
        jse.executeScript("arguments[0].click();", kwiPage.book9Noble);
    }

    @Then("I fill up the form and click on Download")
    public void i_fill_up_the_form_and_click_on_Download() {
        Faker faker = new Faker();
        kwiPage.nameInput.sendKeys(faker.name().firstName());
        kwiPage.lastNameInput.sendKeys(faker.name().lastName());
        kwiPage.companyInput.sendKeys(faker.company().name());
        kwiPage.emailInput.sendKeys(faker.internet().safeEmailAddress());
        kwiPage.downloadBtn.click();
        jse.executeScript("window.scrollBy(0,250)");
        try {
            jse.executeScript("arguments[0].click();", kwiPage.dwnLoadEbook);
        } catch (StaleElementReferenceException e) {
            jse.executeScript("arguments[0].click();", kwiPage.dwnLoadEbook);
        }
    }

    @Then("I validate it opens PDF page")
    public void i_validate_it_opens_PDF_page() throws InterruptedException {
        String current = Driver.getInstance().getDriver().getWindowHandle();
        Set<String> handles = Driver.getInstance().getDriver().getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(current)) {
                Driver.getInstance().getDriver().switchTo().window(actual);
            }
        }
        String expectedUrl = ConfigurationReader.getProperty("ui-config.properties", "kwiPdf.url");
        String actualUrl = Driver.getInstance().getDriver().getCurrentUrl();
        Assert.assertEquals("Titles are not matching", expectedUrl, actualUrl);
    }

}
