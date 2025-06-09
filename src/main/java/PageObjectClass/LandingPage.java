package PageObjectClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public WebDriverWait wait;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	
	@FindBy(id= "login")
	WebElement submit;
	
	@FindBy(css = "_ngcontent-xgn-c33")
	WebElement logoutBtn;
	
	//By logoutBtn = By.cssSelector("_ngcontent-xgn-c33");
	
	public ProductCatLog logIn(String email, String password)
	{
		waitForEmlementToApper(By.id("userEmail"));
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatLog productCatlog = new ProductCatLog(driver);
		return new ProductCatLog(driver);
	}
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public void logOut() {
		
		waitForEmlementToApper(By.cssSelector("_ngcontent-xgn-c33"));
		logoutBtn.click();
	}
}
