package ui_automation.pages;
/*This class contains all webElements for further use in Step Definition part Of Page Object Model*/

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class KWIPage {
    /*This line is needed to use FinBy annotations*/
    public KWIPage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    //WebElement for the text Retail OmniChannel per requirement
    @FindBy(xpath = "//span[contains(text(),'RETAIL OMNI')]")
    public WebElement textRetail;

    //WebElement for the text All-IN-ONE Omnichannel per requirement
    @FindBy(xpath = "//h2[contains(text(),'ALL-IN-ONE OMNI')]")
    public WebElement textAllInOne;
    //List of WebElement for contacts
    @FindBys(@FindBy(xpath = "//div[@class='textwidget']/p/a"))
    public List<WebElement> contactUs;

    @FindBy(xpath = "//div[@class = 'button_container']")
    public WebElement hamburgerBtn;

    @FindBy(xpath = "//li[@id='menu-item-497']")
    public WebElement buttonPlatformServices;

    @FindBy(xpath = "//li[@id='menu-item-26951']/a")//
    public WebElement buttonLoyalty;
    @FindBy(xpath = "//h2[contains(text(),'Get more')]")
    public WebElement getMoreControl;
    //List of Elements for paragraphs
    @FindBys(@FindBy(xpath = "//h2[contains(text(),'Get more')]/..//p"))
    public List<WebElement> paragraphs;
    @FindBy(xpath = "//li[@id='menu-item-24798']/a")
    public WebElement eBook;
    @FindBy(xpath = "//a[contains(text(),'9 Noble Truths')]/../..//a[contains(text(),'View')]")
    public WebElement book9Noble;

    @FindBy(id = "input_10_1")
    public WebElement nameInput;

    @FindBy(id = "input_10_3")
    public WebElement lastNameInput;

    @FindBy(id = "input_10_24")
    public WebElement companyInput;

    @FindBy(id = "input_10_4")
    public WebElement emailInput;

    @FindBy(id = "gform_submit_button_10")
    public WebElement downloadBtn;
    @FindBy(xpath = "//span[contains(text(),'Download eBook PDF')]/../..")
    public WebElement dwnLoadEbook;
}
