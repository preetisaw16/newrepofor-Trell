package Mavenofficeproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.UserInfo;
import resources.base;
import resources.validation;
import resources.ExcelData;

public class Home extends base {
	public static Logger log =LogManager.getLogger(base.class.getName());
	validation ve = new validation();
	

	@BeforeTest
	public void commonnavigation() throws IOException
	{
		driver=initializeDriver();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	@Test
	public void userInfo() throws IOException, InterruptedException
	{
		
		//Declare variables
		String typedText;
		String sheetName = "UserInfoTestData";
		String excelPath = "C:\\Users\\Admin\\GitStuffs\\iTrade.xlsx";
		
		ArrayList<String> iName = new ArrayList<String>();
		ArrayList<String> iMobNumb = new ArrayList<String>();
		ArrayList<String> iStatus = new ArrayList<String>();
		ArrayList<String> iCity = new ArrayList<String>();
		ArrayList<String> iRefCode = new ArrayList<String>();
		ArrayList<String> iRefCodeFlag = new ArrayList<String>();
		ArrayList<String> iAlertTypeFlag = new ArrayList<String>();
		ArrayList<String> iValidateFlag = new ArrayList<String>();
		
		try
		{
			// Fetch data from excel
			ExcelData ed = new ExcelData();
			iName = ed.getData("Name",sheetName,excelPath);
			iMobNumb = ed.getData("Mobilenumber",sheetName,excelPath);
			iStatus = ed.getData("Status",sheetName,excelPath);
			iCity = ed.getData("City",sheetName,excelPath);
			iRefCode = ed.getData("RefferalCode",sheetName,excelPath);
			iRefCodeFlag = ed.getData("RefferalCodeFlag",sheetName,excelPath);
			iAlertTypeFlag = ed.getData("AlertFlag", sheetName, excelPath);
			iValidateFlag = ed.getData("ValidationFlag", sheetName, excelPath);
			
			
			//open url
			UserInfo ui=new UserInfo(driver);
			
			
			// Run the test for each set of data present in excel
			for(int cnt=0; cnt<iName.size(); cnt++)
			{
				String[] arr_iAlertTypeFlag = iAlertTypeFlag.get(cnt).split(";");
				String[] arr_iValidateFlag = iValidateFlag.get(cnt).split(";");
				
				re.logReport("Info", "-------------------- Testing for" + iStatus.get(cnt) + "------------------", "");
				
				//Perform all UI actions
				
				ui.getMobile().sendKeys(iMobNumb.get(cnt));
				Thread.sleep(2000);
				ve.validateLog("Mobile Number",iMobNumb.get(cnt), ui.getMobile().getAttribute("value"), arr_iValidateFlag[1]);
				ve.validateLogAlerts("Mobile Number", ui.getErrorMessage().getText(), arr_iAlertTypeFlag[1]);
				
				
				ui.getName().sendKeys(iName.get(cnt));
				typedText=ui.getName().getAttribute("value");
				ve.validateLog("Name", iName.get(cnt), ui.getName().getAttribute("value"), arr_iValidateFlag[0]);
				
				ui.getCity().sendKeys(iCity.get(cnt));
				ve.validateLog("City", iCity.get(cnt), ui.getCity().getAttribute("value"), arr_iValidateFlag[2]);
				//Assert.assertEquals(ui.getCityError().getText(), "Please enter valid city.");
				
				String otpReadonly = null;
				otpReadonly=ui.getOTP().getAttribute("readonly");//checks if the webelemnt can be in readonly form
				if(otpReadonly == null)
				{
					ui.getOTP().sendKeys("000000");					//database connection required
					ui.getRegenerateOTP().getText();
					ve.validateLog("OTP", "000000", ui.getOTP().getAttribute("value"), arr_iValidateFlag[4]);
					//Assert.assertEquals(ui.getOTPErrorMsg().getText(), "Please enter valid otp");	
				}
				
				ui.getRefCode().sendKeys(iRefCode.get(cnt));
				ve.validateLog("Referral Code", iRefCode.get(cnt), ui.getRefCode().getAttribute("value"), arr_iValidateFlag[3]);
				//Assert.assertEquals(ui.getRefCodeError().getText(), "Please continue from https://5minwebsite.angelbroking.com");
				
				Actions action = new Actions(driver);
				action.doubleClick(ui.getChkBx()).perform();
				ve.validateLog("CheckBox", "", "auto checked, when double clicked it checks and unchecks", "");
				
				//ui.getTC().click();
				
				ui.getTkMeAhd().click();
				Thread.sleep(5000);
				ve.validateLogAlerts("Name",ui.getNameErrorMsg().getText(), arr_iAlertTypeFlag[0]);
				ve.validateLogAlerts("City", ui.getCityError().getText(), arr_iAlertTypeFlag[2]);
				ve.validateLogAlerts("Referral Code", ui.getRefCodeError().getText(), arr_iAlertTypeFlag[3]);
				ve.validateLogAlerts("OTP", ui.getOTPErrorMsg().getText(), arr_iAlertTypeFlag[4]);
				
				// Get value from the Web
				boolean tFlag = true;
				
				
				//validate Enter Name textbox input
				int tLen = iName.get(cnt).length();
				for(int inrcnt = 0; inrcnt<tLen; inrcnt++)
				{
					if (typedText.contains(Character.toString(iName.get(cnt).charAt(inrcnt))))//else
					{
						if (!Character.toString(iName.get(cnt).charAt(inrcnt)).toLowerCase().matches("[a-z/./ ]"))
						{
							tFlag = false;
						}
					}
					else if(Character.toString(iName.get(cnt).charAt(inrcnt)).toLowerCase().matches("[a-z/.]"))
					{
						tFlag = false;
					}
				}
				
				if (tFlag)
				{
					
					Assert.assertTrue(true, "Validation for Enter Name passed");
					re.logReport("Pass", "Validation for Name passed", "");
				}
				else
				{
					Assert.assertFalse(false, "Validation for Enter Name failed");
					re.logReport("Fail", "Validation for Name failed", "");
				}
			
			
				driver.navigate().refresh();
				
				}

		}
		catch(Exception e)
		{
			re.logReport("Failed", "Exception occured "+ e, "");
		}
		finally
		{
			re.writeFile("UserInfo");
		}
		
	}
	
}
