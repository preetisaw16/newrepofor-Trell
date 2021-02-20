package Mavenofficeproject;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.UserInfo;
import resources.ExcelData;
import resources.base;

public class ResumeApplication extends base {
	
	@BeforeTest
	public void commonnavigation() throws IOException
	{
		driver=initializeDriver();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void userInfoResumeApp() throws IOException, InterruptedException
	{
	
		UserInfo ui=new UserInfo(driver);
		
		String sheetName = "ResumeApplication";
		String excelPath = "C:\\Users\\Admin\\GitStuffs\\iTrade.xlsx";
		ArrayList<String> iMobNumb = new ArrayList<String>();
		ArrayList<String> iStatus = new ArrayList<String>();
		
		try
		{
			ExcelData ed = new ExcelData();
			iMobNumb = ed.getData("Mobilenumber",sheetName,excelPath);
			iStatus = ed.getData("Status",sheetName,excelPath);
		
			for(int cnt=0; cnt<iMobNumb.size(); cnt++)
			{
				re.logReport("Info", "-------------------- Testing for " + iStatus.get(cnt) + "------------------", "");
				final String previousUrl=driver.getCurrentUrl();
				ui.getMobile().sendKeys(iMobNumb.get(cnt));
				Thread.sleep(2000);
				String currentURL = driver.getCurrentUrl();
			
				ExpectedCondition e = new ExpectedCondition<Boolean>()
				{
					public Boolean apply(WebDriver driver) 
					{
						return (driver.getCurrentUrl() != previousUrl);
					}
				};
				re.logReport("Pass","Application Successfully Resumed CurrentURL: " + currentURL, "");
		
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
	
		driver.close();
	}
		
	

}
