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
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Action_Weight_Watchers_testNG_Excel {

    //declare driver variable outside so it can be re-used on all annotation methods
    WebDriver driver = null;
    Workbook readableFile = null;
    Sheet readableSheet = null;
    WritableWorkbook writableFile = null;
    WritableSheet wSheet = null;
    int rows = 0;


    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {

    //Open readable file that the system can access
        readableFile = Workbook.getWorkbook(new File("C:src\\main\\resources\\WeightWatchers.xls"));

    //Open readable worksheet in the readable file
        readableSheet = readableFile.getSheet("Sheet1");

    //Create a duplicate readable file / work book
        writableFile = Workbook.createWorkbook(new File("C:src\\main\\resources\\Weight_Watchers_Results.xls"), readableFile);

    //Create a writable sheet
        wSheet = writableFile.getSheet(0);

    //Get the existing row count from the readable sheet
        rows = readableSheet.getRows();

    //navigating to the url using navigate command
        driver = Reusable_Library.navigate(driver, "https://www.weightwatchers.com/us");

    } //end of @BeforeSuite


    @Test
     public void test() throws InterruptedException, WriteException {
        for(int i = 1; i < rows; i++) {

      //Access data from readable sheet/ Store zipCodes in a string
        String zipCodes = readableSheet.getCell(0, i).getContents();

     //1. Re-Navigate to web page
         driver.navigate().to("https://www.weightwatchers.com/us");

     //2. Wait 4 seconds after web page is loaded
         Thread.sleep(4000);

     //3. Verify loaded page title matches "Weight Loss Program, Recipes & Help | Weight Watchers"
          String title1 = driver.getTitle();
          if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
              System.out.println("The title matches");
          } else {
              System.out.println("The title does not match. Actual title is " + title1);
          } //end of if else

     //4. Click on "Find a studio Link"
        Reusable_Library.click(driver,"//*[@href='/us/find-a-meeting']", "Find a Studio");

     //5. Wait a few seconds
         Thread.sleep(2000);

     //6. Verify loaded page title matches "Find a studio & Meeting New You |"
            String title2 = driver.getTitle();
            if (title2.equals("Find a studio & Meeting New You |")){
                System.out.println("The title matches");
            } else {
                System.out.println("The title does not match. Actual title is " + title2);
            } //end of if else

     //7. In the search box, search for meetings by zip code
            Reusable_Library.userInput(driver,"//*[@type = 'text']",zipCodes,"Enter Zip Codes");

     //8. Click on the search icon
            Reusable_Library.click(driver, "//*[@class='btn spice-translated']","Search Button");

     //9. Wait a few seconds
            Thread.sleep(3000);

     //10. Capture the first result information
            String studioLocation = Reusable_Library.captureTextByIndex(driver, "//*[@class='location__name']", 0, "Studio Location Results");

     //11. Wait a few seconds
            Thread.sleep(3000);

     //12. Print the title of the first result and the distance and store it back to excel file
            Label meetingLocation = new Label(1,i, studioLocation);
            wSheet.addCell(meetingLocation);

     //13. Click on the result link to go to the next page
            Reusable_Library.clickByIndex(driver, "//*[@class='location__name']", 0, "Location Name Link");

      //14. Capture current day operation hour and store it back to excel file
            String operationHour = null;
            try {
            operationHour = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
            } catch (Exception e){
                System.out.println("Current Operation Hour is not available for this Zip Code");
            } //end of try catch

            Label opHours = new Label(2,i,operationHour);
            wSheet.addCell(opHours);

            Thread.sleep(2000);

    } //end of for loop

    } //end of @Test

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
     //15. Quit the driver and close readable and writable excel file

        //Write back on the writable workbook
        writableFile.write();

        //Close writable workbook
        writableFile.close();

        //Close readable workbook
        readableFile.close();

        driver.quit();

    } //end of @AfterSuite

} //end of Action Item
