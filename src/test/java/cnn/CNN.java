package cnn;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CNN {

	@Test
	public void main() throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://edition.cnn.com/markets");

		WebElement spe = driver
				.findElement(By.xpath("//span[@class='cnn-pcl-fyz6fg'][normalize-space()='5 Year Treasury']"));
		JavascriptExecutor e = (JavascriptExecutor) driver;
		e.executeScript("arguments[0].scrollIntoView(true);", spe);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class='cnn-pcl-fyz6fg'][normalize-space()='5 Year Treasury']")));
		CNN cnn = new CNN();
		System.out.println(spe.getText());
		cnn.captureScreenshot(driver);

		WebElement searchFeild = driver.findElement(By.xpath("(//input[@name='q'])[2]"));
		JavascriptExecutor e1 = (JavascriptExecutor) driver;
		e1.executeScript("argument[0].scrollIntoView(true)", searchFeild);
		searchFeild.sendKeys("Vinayak");
		cnn.captureScreenshot(driver);

	}

	public void captureScreenshot(WebDriver driver) throws IOException {
		// Capture screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Save screenshot to specified path
		FileUtils.copyFile(scrFile, new File("D://SeleniumProjects//Selenium_FrameWork//CNN.png"));
	}

}
