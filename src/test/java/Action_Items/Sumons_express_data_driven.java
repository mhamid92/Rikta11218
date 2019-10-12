package Action_Items;

import Reusable_Classes.Reusable_Library;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Sumons_express_data_driven {
    WebDriver driver = null;
    Workbook readFile = null;
    WritableWorkbook writeFile = null;
    Sheet readSheet = null;
    WritableSheet writeSheet = null;
    int rows = 0;
    ExtentReports report = null;
    ExtentTest logger = null;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {

        //access readable excel workbook
        readFile = Workbook.getWorkbook(new File("src\\main\\resources\\express.xls"));
        // create duplicate workbook to input new data
        writeFile = Workbook.createWorkbook(new File("src\\main\\resources\\express_results.xls"),readFile);
        // create writable sheet
        writeSheet = writeFile.getSheet(0);
        // access readable sheet to retrieve data
        readSheet = readFile.getSheet(0);
        rows = readSheet.getRows();
        System.out.println("Number of rows in express sheet is: " + rows);

        // define webdriver here
        driver = Reusable_Library.navigate(driver,"https://express.com");

        //Define the path of extent report folder
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExpressReport.html",true);

    } // end of before suite

    @Test
    public void clickOnMeeting() throws InterruptedException, WriteException {

        // begin iteration
        for (int i = 1; i < rows; i++) {

            // store excel values in string
            String size = readSheet.getCell(0, i).getContents();
            String sizeNum = readSheet.getCell(1, i).getContents();
            String quantity = readSheet.getCell(2, i).getContents();
            String firstName = readSheet.getCell(3, i).getContents();
            String lastName = readSheet.getCell(4, i).getContents();
            String email  = readSheet.getCell(5, i).getContents();
            String phoneNum  = readSheet.getCell(6, i).getContents();
            String address  = readSheet.getCell(7, i).getContents();
            String zipCode = readSheet.getCell(8, i).getContents();
            String city = readSheet.getCell(9, i).getContents();
            String state = readSheet.getCell(10, i).getContents();
            String cardNum = readSheet.getCell(11, i).getContents();


            //1. re-navigate to express
            driver.navigate().to("https://express.com");
            //2.verify title
            String expectedTitle = "Men’s and Women’s Clothing";
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Home page title matches. Expected title is: " + expectedTitle + " Actual title is: " + actualTitle);
            } else {
                System.out.println("Home page title does not match. Expected title: " + expectedTitle + " Actual title: " + actualTitle);
            }// end of if else

            //3.hover to womens clothing
            Reusable_Library.mouseHover(driver, "//*[@href='/womens-clothing']", "Women");
            //4.hover to dresses
            Reusable_Library.mouseHover(driver, "//*[contains(@href,'/womens-clothing/dresses/cat550007')]","Dresses");
            //5.click on jumpsuits & rompers
            Reusable_Library.click(driver, "//*[contains(text(),'Jumpsuits & Rompers')]", "Jumpsuits & Rompers");

            Thread.sleep(1000);

            //6.scroll down 400 pixels
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0,5000)");

            //7. Click on first image //use @src property with contains to match up to fashion keyword
            Reusable_Library.clickByIndex(driver, "//*[@class='active loaded']",0,"First Result");

            //8. choose size
            Thread.sleep(3500);
            //Reusable_Library.click(driver, "//button[contains(@value,'"+size+"')]", "Size" );
            try{
                System.out.println("Clicking on Size by Text");
                driver.findElement(By.xpath("//*[@value='"+size+"']")).click();
            } catch (Exception e) {
                System.out.println("Clicking on Size by Number");
                driver.findElement(By.xpath("//*[@value='"+sizeNum+"']")).click();
            }

            //9. Click Add to Bag
            Reusable_Library.click(driver, "//button[contains(text(),'Add to Bag')]", "Add To Bag");

            //10. Click on Checkout from Popup
            Reusable_Library.click(driver, "//*[text()='CHECKOUT']", "Checkout");

            //11.Select Quantity from drop down
            Reusable_Library.dropDownByText(driver, "//*[contains(@id,'quantity')]", quantity, "Quantity");

            //12. Click on Checkout
            Reusable_Library.click(driver, "//*[text()='Checkout']", "Checkout");

            //13. Click on checkout as guest
            Reusable_Library.click(driver, "//*[contains(text(),'Continue as Guest')]", "Continue as Guest");

            //14. Input first name
            Reusable_Library.userInput(driver, "//*[@name='firstname']", firstName, "First Name");

            //15. Input last name
            Reusable_Library.userInput(driver, "//*[@name='lastname']", lastName, "Last Name");

            //16. Input email address
            Reusable_Library.userInput(driver, "//*[@name='email']", email, "Email Address");

            //17. Re-input to confirm email address
            Reusable_Library.userInput(driver, "//*[@name='confirmEmail']", email, "Last Name");

            //18. Input phone number
            Reusable_Library.userInput(driver, "//*[@name='phone']", phoneNum, "Phone Number");

            //19. Click continue
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //20. Click on continue again
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //21. Input address
            Reusable_Library.userInput(driver, "//*[contains(@aria-label,'Street Address')]", address, "Streetaddress");

            //22. Input postal code
            Reusable_Library.userInput(driver, "//*[contains(@aria-label,'Postal Code')]", zipCode, "Zip Code");

            //23. Input city
            Reusable_Library.userInput(driver, "//*[contains(@aria-label,'City')]", city, "City");

            //24.Choose state
            Reusable_Library.dropDownByText(driver, "//*[@name='shipping.state']", state, "State");

            //25. click on continue
            Reusable_Library.click(driver, "//*[text()='Continue']", "Continue");

            //26. clear and enter card num
            Reusable_Library.userInput(driver, "//*[contains(@aria-label,'Credit Card Number')]", cardNum, "Card Number");

            //27. click on place order\
            Reusable_Library.click(driver, "//*[contains(text(),'Place Order')]", "Place Order");

            //28. Print credit card message error
            String cardErrorMsg = new String();
            try {
                cardErrorMsg = driver.findElement(By.xpath("//div[contains(text(),'valid credit card')]")).getText();
            } catch (Exception e) {
                System.out.println("card error message not avaialable" + e);
            }

            Label label = new Label(12, i, cardErrorMsg);
            writeSheet.addCell(label);

            //29. delete all cookies
            driver.manage().deleteAllCookies();

        } // end of loop
    } // end of test 1

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writeFile.write();
        writeFile.close();
        readFile.close();
        driver.quit();

    }
}

