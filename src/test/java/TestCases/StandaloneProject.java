package TestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjectClass.LandingPage;
import PageObjectClass.MyCart;
import PageObjectClass.OrderSuccessPage;
import PageObjectClass.PaymentPage;
import PageObjectClass.ProductCatLog;
import TestCompounent.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneProject extends BaseClass {
	
		@Test(dataProvider = "dataReq")
		  public void submitOredr(HashMap<String, String> input) throws IOException 
		{
		//	String productName = "ADIDAS ORIGINAL";		
		//	String countryName = "india";
		//	String orderPageTitle = "THANKYOU FOR THE ORDER.";
	
		ProductCatLog productCatlog = landingPage.logIn(input.get("email"), input.get("password"));
		List<WebElement> products = productCatlog.getProductlist();
		productCatlog.addProductToCart(input.get("productName"));
		MyCart cartProducts = productCatlog.clickAddCartBtn();
		List<WebElement> addedProducts = cartProducts.listOfCartProductAdded(input.get("productName"));
		Boolean match = cartProducts.verifyAddedCartProducts(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage paymentPage = cartProducts.checkOutBtn();
		paymentPage.selectCountry(input.get("countryName"));
		paymentPage.placeOrderBtn();
		OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
		orderSuccessPage.verifyOrderSuccessMsg(input.get("orderPageTitle"));
		landingPage.logOut();
		}
		
		
		@DataProvider
		public Object[][] dataReq() throws IOException
		{
			//datamap = new StringToHashMap();
			List<HashMap<String, String>> data = jsonReader(System.getProperty("user.dir") +"\\src\\test\\java\\TestData\\Login.json");
			return new Object[][] {{data.get(0)}, {data.get(1)}};
			
		}
	
}

