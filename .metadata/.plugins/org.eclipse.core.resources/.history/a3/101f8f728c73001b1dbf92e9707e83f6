package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserInfo {
	
	public WebDriver driver;
	
	By EnterName= By.id("txtName");//validations
	By EnterMobile=By.id("txtMobileNo");//validations
	By EnterCity=By.id("txtCity");//validations
	By Otp=By.id("txtOtp");//validations
	By RefCode=By.id("txtReferralPromoCode");//validations
	By TC=By.id("spnTermsConditions");//click and take screen
	By ChkBx=By.id("iAgree");//click/check(mandatory)
	By TkMeAhd=By.id("btnOpenAccount");//click(validate by clicking several time)
	By ResApp=By.xpath("//u[contains(text(),'Resume Application')]");//validations
	By BrokCal=By.className("brokeragecal");//click and take screen
	By BrokCalDelEq=By.id("Delivery_tab");//take screen
	By BrokCalFu=By.id("Future_tab");//take screen
	By BrokCalOp=By.id("Options_tab");//take screen
	By MyLang=By.className("btn btn-primary dropdown-toggle");//just hover and take screen
	By Questicon=By.className("sprite-ico question");//click and take screen
	By QuesticonRC=By.xpath("//div[contains(text(),'What is a Referral Code?')]");//click and take screen
	By QuesticonBrok=By.xpath("//div[contains(text(),'What is Brokerage?')]");//click and take screen
	By QuesticonOS=By.xpath("//div[contains(text(),'What is Order size?')]");//click and take screen
	
	public UserInfo(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		
	}
	public WebElement getName()
	{
		return driver.findElement(EnterName);
	}
	
	public WebElement getMobile()
	{
		return driver.findElement(EnterMobile);
	}
	public WebElement getCity()
	{
		return driver.findElement(EnterCity);
	}
	public WebElement getOTP()
	{
		return driver.findElement(Otp);
	}
	public WebElement getRefCode()
	{
		return driver.findElement(RefCode);
	}
	public WebElement getTC()
	{
		return driver.findElement(TC);
	}
	public WebElement getChkBx()
	{
		return driver.findElement(ChkBx);
	}
	public WebElement getTkMeAhd()
	{
		return driver.findElement(TkMeAhd);
	}
	
	
}
