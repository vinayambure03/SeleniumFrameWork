package PageObjectClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	
	WebDriver driver;
	Actions a ;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement ContryField;
	
	By countryVisiable = By.cssSelector(".ta-results");
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectedCountry;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderEle;

	
	public String selectCountry(String countryName ) {
		
		a = new Actions(driver);
		a.sendKeys(ContryField, countryName).build().perform();
		waitForEmlementToApper(countryVisiable);
		selectedCountry.click();
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		return countryName;
	}

	public void placeOrderBtn() {
		
		placeOrderEle.click();
	}
	

}
