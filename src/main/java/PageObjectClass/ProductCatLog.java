package PageObjectClass;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class ProductCatLog extends AbstractComponent {

	WebDriver driver;

	// Constructor
	public ProductCatLog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement loader;

	@FindBy(css = "[routerlink*='cart']")
	WebElement addCartBtn;

	By prodctsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By tosteMsg = By.cssSelector("#toast-container");

	public List<WebElement> getProductlist() {

		waitForEmlementToApper(prodctsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductlist().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForEmlementToDisapear(loader);
	//	waitForEmlementToApper(tosteMsg);

	}

	public MyCart clickAddCartBtn() {
		addCartBtn.click();
		return new MyCart(driver);
	}

}
