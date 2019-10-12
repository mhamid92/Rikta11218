package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class Yahoo_Registration_Page extends Abstract_Class {


    //1. Extend Abstract Class in method

    //2. Extend test to include 'logger;
    ExtentTest logger;

    //3. Create constructor to use driver and logger as a Page Object
    public Yahoo_Registration_Page(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    } //end of child method


    //4. Enter elements required during the test
    //4A. Element for First Name
    @FindBy (xpath = "//*[@id = 'usernamereg-firstName']")
    public static WebElement firstName;

    //4B. Element for Last Name
    @FindBy (xpath = "//*[@id = 'usernamereg-lastName']")
    public static WebElement lastName;

    //4C. Element for Email Address
    @FindBy (xpath = "//*[@id = 'usernamereg-yid']")
    public static WebElement emailAddress;

    //4C1. Element to click outside of email address
    @FindBy (xpath = "//*[@class = 'yid-domain']")
    public static WebElement clickOutside;

    //4D. Element for Password
    @FindBy (xpath = "//*[@id = 'usernamereg-password' and @placeholder = 'Password']")
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
    @FindBy (css = "#recaptcha-script > h1")
    public static WebElement verificationMessage;

    public Yahoo_Registration_Page checkTitle () throws InterruptedException {
        Thread.sleep(2000);
         //8. Verify title of page is 'Yahoo'
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"Yahoo");
        softAssert.assertAll();
        return this;
    } //end of checkTitle

    public Yahoo_Registration_Page firstN (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, firstName, 0, userInput, logger, "First Name");
        return this;
     } //end of firstN

    public Yahoo_Registration_Page lastN (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, lastName, 0, userInput, logger, "Last Name");
        return this;
    } //end of lastN

    public Yahoo_Registration_Page emailA (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, emailAddress, 0, userInput, logger, "Email Address");

        return this;
    } //end of email Address

    public Yahoo_Registration_Page clickOutsideBox () throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.click(driver, clickOutside, 0, logger, "Click Outside");
        return this;
    } //end of Continue Button

    public Yahoo_Registration_Page password (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, passWord, 0, userInput, logger, "Password");
        return this;
    } //end of password

    public Yahoo_Registration_Page phoneNum (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, phoneNumber, 0, userInput, logger, "Phone Number");
        return this;
    } //end of phone number

    public Yahoo_Registration_Page Month (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.click(driver,birthMonth,0,logger,"Click on tab");
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//option[text() = '" + userInput + "']")).click();
        // The code below is the same as the above driver.findElement code above
        //Select BMONTH = new Select(birthMonth);
        //BMONTH.selectByVisibleText(userInput);
        return this;
    } //end of birth month

    public Yahoo_Registration_Page Date (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, birthDate, 0, userInput, logger, "Birth Date");
        return this;
    } //end of birth date

    public Yahoo_Registration_Page Year (String userInput) throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.userInput(driver, birthYear, 0, userInput, logger, "Birth Year");
        return this;
    } //end of birth year

    public Yahoo_Registration_Page Continue () throws IOException, InterruptedException {
        //Thread.sleep(2000);
        Reusable_Library_Loggers_POM.click(driver, continueButton, 0, logger, "Continue Button");
        return this;
    } //end of Continue Button

    public String VerificationMess () throws IOException, InterruptedException {
        Thread.sleep(2000);
        driver.switchTo().frame("recaptcha-iframe");
        //driver.findElement(By.xpath("//*[@target = '_parent']")).click();

        String text = Reusable_Library_Loggers_POM.captureTextByIndex(driver, verificationMessage, 0, logger, "Verification Message");
        return text;
    } //end of verification message



} //end of class