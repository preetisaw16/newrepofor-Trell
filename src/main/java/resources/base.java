package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import resources.Reporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class base {

	public static WebDriver driver;
	public Properties prop;
	public static Reporter re = new Reporter();
	
	
	public WebDriver initializeDriver() throws IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\OfficeItradeProject\\src\\main\\java\\Resources\\Data.Properties");
		prop.load(fis);
		String BrowserName=prop.getProperty("browser");
		
		
		if(BrowserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			driver=new ChromeDriver();
		}
	
		else if(BrowserName.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}
	
		else if(BrowserName.equals("IE"))
		{
			driver=new InternetExplorerDriver();
		}
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	public void getScreenshots() throws IOException
	{
		File Src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Src,new File("D:\\Screenshots"));
	}
	
	

	

	}


