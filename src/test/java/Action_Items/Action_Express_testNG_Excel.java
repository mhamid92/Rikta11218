package Action_Items;

import Reusable_Classes.Reusable_Library;
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
import java.sql.ResultSet;

public class Action_Express_testNG_Excel {
    //declare re-usable driver variables
    WebDriver driver = null;
    Workbook readableFile = null;
    Sheet readableSheet = null;
    WritableWorkbook writableFile = null;
    WritableSheet wSheet = null;
    int rows = 0;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {

        //Open readable file
        readableFile = Workbook.getWorkbook(new File("C:src\\main\\resources\\Action_Item_Express.xls"));

        //Open readable sheet
        readableSheet = readableFile.getSheet("Sheet1");

        //Create a duplicate readable file
        writableFile = Workbook.createWorkbook(new File("C:src\\main\\resources\\Action_Item_Express_Results.xls"), readableFile);

        //Create a writable worksheet
        wSheet = writableFile.getSheet("Sheet1");

        //Get the existing row count from the readable sheet
        rows = readableSheet.getRows();

        //Navigate to the URL
        driver = Reusable_Library.navigate(driver, "https://www.express.com/");

    } //end of @BeforeSuite

    @Test
    public void test() throws InterruptedException, WriteException {
        for(int i = 1; i < rows; i++) {
        String size = readableSheet.getCell(0,i).getContents();
        String sizeNum = readableSheet.getCell(1,i).getContents();
        String quantity = readableSheet.getCell(2,i).getContents();
        String firstName = readableSheet.getCell(3,i).getContents();
        String lastName = readableSheet.getCell(4,i).getContents();
        String email = readableSheet.getCell(5,i).getContents();
        String phoneNumber = readableSheet.getCell(6,i).getContents();
        String address = readableSheet.getCell(7,i).getContents();
        String zipCode = readableSheet.getCell(8,i).getContents();
        String city = readableSheet.getCell(9,i).getContents();
        String state = readableSheet.getCell(10, i).getContents();
        String cardNumber = readableSheet.getCell(11, i).getContents();
        String errorMessage = readableSheet.getCell(12,i).getContents();


        //1. Re-navigate to Express home page
            driver.navigate().to("https://www.express.com/");

        //2. Verify we are on following page title = ‘Men’s and Women’s Clothing’
        String title1 = driver.getTitle();
        if(title1.equals("Men’s and Women’s Clothing")){
            System.out.println("The title matches");
        }else {
            System.out.println("The title does not match. Actual title is " + title1);
        }//end of if else

        //3. Hover to ‘Women’ tab using Actions //use @href property
            Reusable_Library.mouseHover(driver,"//*[@href = '/womens-clothing']","Women tab");

        //4. Hover on ‘Dresses’ Link using Actions //use contains @href property or contains text() property
            Thread.sleep(2000);
            Reusable_Library.mouseHover(driver,"//*[contains(@href, '/womens-clothing/dresses/cat550007')]", "Dresses tab");

        //5. Click on ‘Jumpsuits & Rompers’ link //use contains @href property or contains text() property
            Reusable_Library.click(driver,"//*[@href = '/womens-clothing/dresses/jumpsuits-rompers/cat320051']", "Jumpsuit & Rompers tab");

        //6. Wait few seconds using thread.sleep and Scroll down about 400 pixels
            Thread.sleep(3000);
            JavascriptExecutor jseDown = (JavascriptExecutor) driver;
            jseDown.executeScript("scroll (0,4000)");

        //7. Click on first image //use @src property with contains to match up to fashion keyword
            Reusable_Library.click(driver, "//*[@class = 'active loaded']", "First Image clicked");

        //8. On size page click on specific size and the value should be passed as a locator as text or value property
            Thread.sleep(3500);
            try{
                System.out.println("Clicking on Size by Text");
                driver.findElement(By.xpath("//*[@value= '"+size+"']")).click();
            } catch (Exception e) {
                System.out.println("Clicking on Size by Number");
                driver.findElement(By.xpath("//*[@value= '"+sizeNum+"']")).click();
            } //end of try catch

        //9. Click on ‘Add to Bag’ button
            Thread.sleep(2000);
            Reusable_Library.click(driver, "//button[contains(text(),'Add to Bag')]", "Add To Bag Button");

        //10. Click on ‘Checkout’ button on pop up
            Reusable_Library.click(driver, "//*[text() = 'CHECKOUT']", "Checkout Button");

        //11. Select a quantity
            Reusable_Library.dropDownByText(driver, "//*[@id = 'qdd-0-quantity']", quantity, "Quantity");
            Thread.sleep(2000);

        //12. Click on  ‘Checkout’ button
            Reusable_Library.click(driver, "//*[@class = 'btn _9yfmt _194FD _2tFXQ _1s_-v']", "Checkout Button 2");

        //13. On pop up click on ‘Continue as Guest’ button
            Thread.sleep(1000);
            Reusable_Library.click(driver, "//*[@class = 'btn _9yfmt _194FD _2tFXQ']", "Continue as guest button");

        //14. Enter first name
            Reusable_Library.userInput(driver, "//*[@name = 'firstname']", firstName, "First Name");

        //15. Enter last name
            Reusable_Library.userInput(driver, "//*[@name = 'lastname']", lastName, "Last Name");

        //16. Enter Email address (put invalid address)
            Reusable_Library.userInput(driver, "//*[@name = 'email']", email, "Email Address");

        //17. Re-enter Email address(put same email above)
            Reusable_Library.userInput(driver, "//*[@name = 'confirmEmail']", email, "Confirm Email Address");

        //18. Enter phone number(invalid numeric 10 digit number)
            Reusable_Library.userInput(driver, "//*[@name = 'phone']", phoneNumber, "Phone Number");

        //19. Click on ‘Continue’ button
            Reusable_Library.click(driver, "//*[text() = 'Continue']", "Continue Button");

        //20. Click on ‘Continue’ button again
            Reusable_Library.click(driver, "//*[text() = 'Continue']", "Continue Button");

        //21. Enter address
            Reusable_Library.userInput(driver, "//*[@name = 'shipping.line1']", address, "Address");

        //22. Enter zip code
            Reusable_Library.userInput(driver, "//*[@name = 'shipping.postalCode']", zipCode, "Zip Codes" );

        //23. Enter city
            Reusable_Library.userInput(driver, "//*[@name = 'shipping.city']", city, "City");

        //24. Select state (keep as NY for zip codes)
            Reusable_Library.dropDownByText(driver, "//*[@name ='shipping.state']", state, "State" );

        //25. Click on continue button
            Reusable_Library.click(driver, "//*[text() = 'Continue']", "Continue Button");

        //26. Clear and Enter card number
            Reusable_Library.userInput(driver, "//*[@id = 'creditCardNumberInput']", cardNumber, "Credit Card Number");

        //27. Click on ‘Place Order’ button
            Reusable_Library.click(driver, "//*[text() = 'Place Order']", "Place Order");

        //28. Capture the error message for card number field and send it back to  errorMessage column in excel
        String cardErrorMessage = new String();
        try {
            cardErrorMessage = driver.findElement(By.xpath("//*[@class = 'dOxMH labelError']")).getText();
        } catch (Exception e) {
            System.out.println("Unable to capture error message " + e);
        } //end of try catch

            Label errMessage = new Label(12,i, cardErrorMessage);
            wSheet.addCell(errMessage);

    //29. Delete all cookies
            driver.manage().deleteAllCookies();

        } //end of for loop

    } //end of @Test

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();

    } //end of @AfterSuite


} //end of action item
