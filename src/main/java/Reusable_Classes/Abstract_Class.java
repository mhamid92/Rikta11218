package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


import java.io.IOException;
import java.lang.reflect.Method;

public class Abstract_Class extends Reusable_Library_Loggers{
    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;

    @BeforeSuite()
    public static void openBrowser() throws IOException, BiffException {
        //path to your report
        report = new ExtentReports("src\\main\\java\\Report_Folder\\AutomationReport" + getDateTime() + ".html", true);
    }//end of before suite
    // vcs

    @Parameters("Browser")
    @BeforeMethod
    public static void captureTestName(String Browser,Method methodName) throws IOException {
        if(Browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if(Browser.equalsIgnoreCase("Chrome")) {
            driver = navigate(driver, "https://www.google.com");
        }
        //path t
        //logger below will get the actual name of each of your @Test method(s)
        logger = report.startTest(methodName.getName() + "--" + Browser);
        logger.log(LogStatus.INFO,"Automation Test Scenario Started....");
    }//end of before method

    @AfterMethod
    public static void endTest(){
        report.endTest(logger);
        logger.log(LogStatus.INFO,"Automation Test Scenario ended....");
    }//end of after method

    @AfterSuite
    public void closeBrowser(){
        report.flush();
        report.close();
        //driver.quit();
    }//end of after suite







}
