package jenkinsDemo;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class BuyTicket {
	
	WebDriver driver =new FirefoxDriver();
	String baseurl = "http://newtours.demoaut.com";
	
  @BeforeMethod
  public void login() {
	  	driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("userName")).sendKeys("tutorial");
		driver.findElement (By.name("password")).sendKeys("tutorial");
		driver.findElement (By.xpath("//input[@alt='Sign-In']")).click();
  }
  @Test
  public void ticketpurchase() {
	  	driver.findElement(By.xpath("//input[@value='roundtrip']")).click();
		Select fromport = new Select(driver.findElement(By.xpath("//select[@name='fromPort']")));
		fromport.selectByValue("London");
		Select frommonth = new Select(driver.findElement(By.xpath("//select[@name='fromMonth']")));
		frommonth.selectByVisibleText("September");
		Select fromday = new Select(driver.findElement(By.xpath("//select[@name='fromDay']")));
		fromday.selectByVisibleText("12");
		Select toport = new Select(driver.findElement(By.xpath("//select[@name='toPort']")));
		toport.selectByVisibleText("New York");
		Select returnmonth = new Select(driver.findElement(By.xpath("//select[@name='toMonth']")));
		returnmonth.selectByVisibleText("September");
		Select returnday = new Select(driver.findElement(By.xpath("//select[@name='toDay']")));
		returnday.selectByVisibleText("29");
		driver.findElement(By.xpath("//input[@value='Business']")).click();
		Select airline = new Select(driver.findElement(By.xpath("//select[@name='airline']")));
		airline.selectByVisibleText("Blue Skies Airlines");
		driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		driver.findElement(By.xpath("//input[@border='0']")).click();
		driver.findElement(By.xpath("//input[@name='passFirst0']")).sendKeys("Naresh");
		driver.findElement(By.xpath("//input[@name='passLast0']")).sendKeys("Prajapati");
		Select meal = new Select(driver.findElement(By.xpath("//select[@name='pass.0.meal']")));
		meal.selectByVisibleText("Vegetarian");
		Select cardtype = new Select(driver.findElement(By.xpath("//select[@name='creditCard']")));
		cardtype.selectByVisibleText("Visa");
		driver.findElement(By.xpath("//input[@name='creditnumber']")).sendKeys("123456789");
		Select expirymonth = new Select(driver.findElement(By.xpath("//select[@name='cc_exp_dt_mn']")));
		expirymonth.selectByVisibleText("01");
		Select expiryyr = new Select(driver.findElement(By.name("cc_exp_dt_yr")));
		expiryyr.selectByVisibleText("2002");		
		driver.findElement(By.xpath("//input[@name='buyFlights']")).click();
		String actualstring = "Your itinerary has been booked!";
		String expectedstring = driver.findElement(By.xpath("//font[@size='+1']")).getText();
		Assert.assertEquals(actualstring, expectedstring);
  }
  
  @AfterMethod
  public void Logout() {
	  driver.findElement(By.xpath("//a[contains(.,'SIGN-OFF')]")).click();
	  driver.close();
	  }

}
