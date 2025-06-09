package PageObjectClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent{
	
	WebDriver driver;
	public MyCart(WebDriver driver)
	{	super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;
	
	public List<WebElement> listOfCartProductAdded(String productName)
	{
		return cartProducts;
	}
	
	public Boolean verifyAddedCartProducts(String productName)
	{
		Boolean match = listOfCartProductAdded(productName).stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public PaymentPage checkOutBtn() {
		
		checkOutBtn.click();
		return new PaymentPage(driver);
	}
}
