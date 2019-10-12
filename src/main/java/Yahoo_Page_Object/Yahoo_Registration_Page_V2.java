package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class Yahoo_Registration_Page_V2 extends Abstract_Class {
    //1. Extend Abstract Class in Main method

    //2. Extend test to include logger
    ExtentTest loggers;

    //3. Create constructor to use driver and logger as a Page Object
    public Yahoo_Registration_Page_V2(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.loggers = super.logger;
    } //end of constructor

    //4. Enter elements required during the test
    //4A. Element for First Name
    @FindBy(xpath = "//*[@id = 'usernamereg-firstName']")
    public static WebElement firstName;

    //4B. Element for Last Name
    @FindBy (xpath = "//*[@id = 'usernamereg-lastName']")
    public static WebElement lastName;

    //4C. Element for Email Address
    @FindBy (xpath = "//*[@id = 'usernamereg-yid']")
    public static WebElement emailAddress;

    //4D. Element for Password
    @FindBy (xpath = "//*[@id = 'usernamereg-password']")
    public static WebElement passWord;

    //4E. Element for Phone Number
    @FindBy (xpath = "//*[@id = 'usernamereg-phone']")
    public static WebElement phoneNumber;

    //4F. Element for Birth Month
    @FindBy (xpath = "//*[@id = 'usernamereg-month']")
    public static WebElement birthMonth;

    //4G. Element for Birth Date
    @FindBy (xpath = "//*[@id = 'usernamereg-day']")
    public static WebElement birthDate;

    //4H. Element for Birth Year
    @FindBy (xpath = "//*[@id = 'usernamereg-year']")
    public static WebElement birthYear;

    //4I. Element for Continue Button
    @FindBy (xpath = "//*[@id = 'reg-submit-button']")
    public static WebElement continueButton;

    //4J. Element for Verification message
    @FindBy (xpath = "//*[@class = 'write-up m-t-24px txt-align-center']")
    public static WebElement verificationMessage;

    //5. Create strings for methods
    public static String firstname = null;
    public static String lastname = null;
    public static String emailaddress = null;
    public static String password = null;
    public static String phonenumber = null;
    public static String month = null;
    public static String date = null;
    public static String year = null;
    public static String continueB = null;
    public static String message = null;

    //6. Create methods used on the page
    public Yahoo_Registration_Page_V2 captureTitle (){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"Yahoo");
        softAssert.assertAll();
        return new Yahoo_Registration_Page_V2(driver);
    } //end of captureTitle


    public Yahoo_Registration_Page_V2 enterFirstName(int i){





        return new Yahoo_Registration_Page_V2(driver);
    } //end of enterFirst Name

} //end of class
