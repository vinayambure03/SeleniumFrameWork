package AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	// Local variable
	WebDriver driver;
	public WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}

	public void waitForEmlementToApper(By FindBy)

	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public void waitForEmlementToDisapear(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}


	public File TakeScreenshots(WebDriver driver, String ScreenName) throws IOException {

		File screeShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screeShot, new File("D://SeleniumProjects//Selenium_FrameWork//ScreenShots"+ScreenName+".png"));
		return screeShot;
	}

}
