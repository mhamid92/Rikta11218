package Test_Suite_10052019;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class Test_WeightWatchers_TestNG extends Abstract_Class {

    @Test
    public void Capture_Current_Operation_Hour() throws InterruptedException, IOException {

        //re navigate to weight watchers
        logger.log(LogStatus.INFO,"Navigating to Weight Watchers Homepage");
        driver.navigate().to("https://www.weightwatchers.com/us");

        //timeout to capture the page title
        Thread.sleep(3000);
        //verifying the page title
        String title1 = driver.getTitle();
        if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
           logger.log(LogStatus.PASS,"Title matches");
        } else {
            logger.log(LogStatus.FAIL,"Title doesn't match " + title1);
        }

        //click on 'Find a Meeting' button
        click(driver, "//*[@class='find-a-meeting']", logger,"Find a Meeting");

        //timeout to capture the page title 2
        Thread.sleep(3000);
        //verifying the page title 2
        String title2 = driver.getTitle();
        if (title2.equals("Find a Studio & Meeting Near You |")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title2);
        }

        //enter zip code
        userInput(driver, "//*[@id='meetingSearch']", "11218", logger,"Enter Location");
        //click on Search button
        click(driver, "//*[@spice='SEARCH_BUTTON']", logger,"Search Button");

        //capture the first result for meeting location
        String meetLocation = captureTextByIndex(driver, "//*[@class='location__name']", 0, logger,"Meeting Location");
        //click on first element using index
        clickByIndex(driver, "//*[@class='location__name']", 0, logger,"Location Name Link");

        //capture the current day operation hour
        String opHour = null;
        //if the operation hour is not present for a zipcode then store the variable inside try catch
        try {
            Thread.sleep(2800);
            opHour = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
        } catch (Exception e) {
            opHour = "Current Operation Hour is not available for this Zip Code";
        } //end of try catch

        }//end of test


    }//end of class





