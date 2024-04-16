package Maven.SeleniumFrameWork.TestComponent;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckOutTest {
	
	public WebDriver driver;
	
	//String browserName ="chrome";
	
	//Cross-Browser Testing --> 
	//send browsername parameter from testNG file ex- chrome, firefox, and edge 
	//The test case will run on the specified as per the defined by user on TestNg File
	@Parameters("browserName")
	@BeforeMethod
	public WebDriver initializeDriver(String browserName) throws IOException
	{
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDriver\\chromedriver.exe");
		 //WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	      
	       
	}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		
		else if (browserName.equalsIgnoreCase("edge"))
		{
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

}
	
//Verify that users can add items to their cart from the product detail
	//The below code will navigate the user on Amazon Home Page
	//Search for a specific product and add it on Cart 
	// If Add cart button is not available it throw exception 
	//Finally it will compare the cart count before product addition and after product addition 
	// if the cart count is incremeneted the test case will pass and if not it will
	@Parameters("Product")
  @Test 
  public void addinItemToCart(String Product) throws InterruptedException {
  driver.get("https://www.amazon.in/");
  try {
  driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Product);
  }
  catch (Exception e) {
	  System.out.println("Invalid product details");
  }
  driver.findElement(By.id("nav-search-submit-button")).click();
  
  String countbefore = driver.findElement(By.id("nav-cart-count")).getText();
  int count1 = Integer.parseInt(countbefore);
  try { driver.findElement(By.id("a-autoid-1-announce")).click();
  
  
  }catch (Exception e) {
  System.out.println("Add to cart button is not visible."); }
  
  Thread.sleep(5000);
  String count = driver.findElement(By.id("nav-cart-count")).getText();
  int count2 = Integer.parseInt(count);
  
  if(count1>count2) {
  System.out.println("Count after product added:"+ count1 +" "+
  "Count after product added:"+count1  + "First Test scenario Passed" );
  } else {
	  System.out.println("Count does not matches" +" "+ "First Test scenario Failed");
  }
  driver.close();
  
  }
  
  //: Updating Shipping Information
  //The below test case will login the User
  // Then it will add the the product 
  //Update the shipping addres
  //Finaly it will check the first line of address updated or not 
  
  @Parameters({"Product","Product2", "FullName", "MobileNumber", "Pincode", "Apartment", "Area", "Landmark", "Town","Email", "Password"})
  @Test
  public void changeShippingAddress(String Email, String Password, String Product, String Product2, String FullName, String MobileNumber, String Pincode,String Apartment,String Area, String Landmark, String Town) throws InterruptedException {
  driver.get("https://www.amazon.in/");
  
  driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
  driver.findElement(By.id("ap_email")).sendKeys(Email);
  driver.findElement(By.id("continue")).click();
  driver.findElement(By.id("ap_password")).sendKeys(Password);
  driver.findElement(By.id("signInSubmit")).click();
  driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Product);
  driver.findElement(By.id("nav-search-submit-button")).click(); try {
  driver.findElement(By.id("a-autoid-1-announce")).click(); }catch (Exception
  e) { System.out.println("Add to cart button is not visible."); }
  Thread.sleep(5000);
  driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
  Thread.sleep(5000); driver.findElement(By.xpath("//input[@data-feature-id='proceed-to-checkout-action']")).click();
  driver.findElement(By.xpath("//div[@class='a-row address-row list-address-selected']//a[contains(@aria-label,'Edit address')][normalize-space()='Edit address']")).click(); 
  Thread.sleep(5000);
  driver.findElement(By.id("address-ui-widgets-enterAddressFullName")).clear();
  driver.findElement(By.id("address-ui-widgets-enterAddressFullName")).sendKeys(FullName);
  driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber")).clear(); 
  driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber")).sendKeys(MobileNumber);
  driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode")).clear(); 
  driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode")).sendKeys(Pincode);
  driver.findElement(By.id("address-ui-widgets-enterAddressLine1")).clear();
  driver.findElement(By.id("address-ui-widgets-enterAddressLine1")).sendKeys(Apartment);
  driver.findElement(By.id("address-ui-widgets-enterAddressLine2")).clear();
  driver.findElement(By.id("address-ui-widgets-enterAddressLine2")).sendKeys(Area);
  driver.findElement(By.id("address-ui-widgets-landmark")).clear();
  driver.findElement(By.id("address-ui-widgets-landmark")).sendKeys(Landmark);
  
  
  JavascriptExecutor jse= (JavascriptExecutor) driver;
  jse.executeScript(("window.scroll(0,1200)"));
  driver.findElement(By.id("address-ui-widgets-enterAddressCity")).clear();
  
  driver.findElement(By.id("address-ui-widgets-enterAddressCity")).sendKeys(Town);
  
  //WebElement select = driver.findElement(By.xpath( "//span[@id='address-ui-widgets-enterAddressStateOrRegion']//span[@role='button']")); //Select se = new Select(select); //se.selectByValue("BIHAR");
  driver.findElement(By.id("address-ui-widgets-use-as-my-default")).click();
  
  //driver.findElement(By.id("address-ui-widgets-form-submit-button-announce")).click();
  WebElement element =driver.findElement(By.id("address-ui-widgets-form-submit-button-announce"));
  JavascriptExecutor executor = (JavascriptExecutor)driver;
  executor.executeScript("arguments[0].click();", element); Thread.sleep(5000);
  String Line1 = driver.findElement(By.
  xpath("//li[@class='displayAddressLI displayAddressFullName']")).getText();
  //String Line2 =
  driver.findElement(By.xpath("displayAddressAddressLine1")).getText();
  //String Line3 = driver.findElement(By.cssSelector("displayAddressLI displayAddressAddressLine2")).getText();
  Assert.assertEquals(Line1, FullName,"Since first line of address is matched with the Updated one the Address is updated");
  
  }
 
//	 Removing Items from Cart
//The below test case will login the User
  // Then it will add the the product
  //Finally it will remove the product and check weather the count is decremented or not
  @Parameters({"Email","Password"})
@Test
public void removingItemFromCart(String Email, String Password, String Product, String Product1) throws InterruptedException {
	driver.get("https://www.amazon.in/");
	driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
	driver.findElement(By.id("ap_email")).sendKeys(Email);
	driver.findElement(By.id("continue")).click();
	driver.findElement(By.id("ap_password")).sendKeys(Password);
	driver.findElement(By.id("signInSubmit")).click();
	
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Product);
	driver.findElement(By.id("nav-search-submit-button")).click();
	try {
	driver.findElement(By.id("a-autoid-1-announce")).click();
	}catch (Exception e) {
	      System.out.println("Add to cart button is not visible.");
	}
	driver.findElement(By.id("twotabsearchtextbox")).clear();

	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Product1);
	try {
		driver.findElement(By.id("a-autoid-11-announce")).click();
	}catch (Exception e) {
	      System.out.println("Add to cart button is not visible.");
	}
	
	driver.findElement(By.id("nav-cart-count-container")).click();
	Thread.sleep(5000);
	String count = driver.findElement(By.id("nav-cart-count")).getText();
int count1 = Integer.parseInt(count);
	driver.findElement(By.id("nav-cart-count-container")).click();
	
	List<WebElement> delete= driver.findElements(By.xpath("//input[@data-action='delete']"));
	for(int i=0; i<1; i++) {
		delete.get(i).click();
	}
	String countAfterDelete = driver.findElement(By.id("nav-cart-count")).getText();
	int count2= Integer.parseInt(countAfterDelete);
	if(count2 < count1) {
		System.out.println("Delete action sucessfully completed");
		
	} else {
		System.out.println("Delete action is not sucessfully completed");
		
	}
}
	
}
