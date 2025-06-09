package PageObjectClass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class OrderSuccessPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderSuccessPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".hero-primary")
	WebElement orderPageTitleEle;
	
	public void verifyOrderSuccessMsg(String orderPageTitle) throws IOException {
		String confMsg = orderPageTitleEle.getText();
		Assert.assertTrue(confMsg.equalsIgnoreCase(orderPageTitle));
		TakeScreenshots(driver, "OrderPlaced");
	}
	
	
	

}
