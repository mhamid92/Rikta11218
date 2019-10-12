package Yahoo_Test_Page_Object;

import Reusable_Classes.Abstract_Class;
import Yahoo_Page_Object.Yahoo_Registration_Page;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static Yahoo_Page_Object.Yahoo_Base_Class.yahoo_registration_page;


public class TC02_Yahoo_Registration extends Abstract_Class {
    //5. declare re-usable driver variables
    Workbook readableFile = null;
    Sheet readableSheet = null;
    WritableWorkbook writableFile = null;
    WritableSheet wSheet = null;
    int readableRows = 0;


    @Test
        public void Test_Yahoo_Reg_Page () throws IOException, InterruptedException, WriteException, BiffException {
        //6. Store excel information
        //Open readable file
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\Yahoo Registration.xls"));

        //Open readable sheet
        readableSheet = readableFile.getSheet("Sheet1");

        //Create a duplicate readable file
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\Yahoo Registration Result.xls"), readableFile);

        //Create a writable worksheet
        wSheet = writableFile.getSheet("Sheet1");

        //Get the existing row count from the readable sheet
        readableRows = readableSheet.getRows();

            for (int i = 1; i < readableRows; i++) {
                logger.log(LogStatus.INFO, "Navigating to Yahoo registration page");
                driver.navigate().to("https://login.yahoo.com/account/create");

                String fName = readableSheet.getCell(0, i).getContents();
                String lName = readableSheet.getCell(1, i).getContents();
                String email = readableSheet.getCell(2, i).getContents();
                String pWord = readableSheet.getCell(3, i).getContents();
                String pNumber = readableSheet.getCell(4, i).getContents();
                String bMonth = readableSheet.getCell(5, i).getContents();
                String bDate = readableSheet.getCell(6, i).getContents();
                String bYear = readableSheet.getCell(7, i).getContents();

                //8. Verify title
                yahoo_registration_page().checkTitle();

                //9. Enter First Name
                yahoo_registration_page().firstN(fName);

                //10. Enter Last Name
                yahoo_registration_page().lastN(lName);

                //11. Enter Email Address
                yahoo_registration_page().emailA(email);

                //11B. Click outside of email address
                yahoo_registration_page().clickOutsideBox();

                //12. Enter Password
                yahoo_registration_page().password(pWord);

                //13. Enter Phone Number
                yahoo_registration_page().phoneNum(pNumber);

                //14. Enter Birth Month
                yahoo_registration_page().Month(bMonth);

                //15. Enter Birth Date
                yahoo_registration_page().Date(bDate);

                //16. Enter Birth Year
                yahoo_registration_page().Year(bYear);

                //17. Click on Continue Button
                yahoo_registration_page().Continue();

                //18. Capture Error Message
               String Message = yahoo_registration_page().VerificationMess();

                yahoo_registration_page().VerificationMess();
                Label label = new Label(8, i, Message);
                wSheet.addCell(label);

                //19. Delete all cookies
                driver.manage().deleteAllCookies();

            } //end of loop

        writableFile.write();
        writableFile.close();
        readableFile.close();

        } //end of TC02


} //end of class
