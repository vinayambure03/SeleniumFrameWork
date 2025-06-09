package Practice;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayNameGenerator.Standard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportTest extends StringToHashMap {

	ExtentReports extent;
	ExtentReportTest ert;
//	StringToHashMap datamap ;
	
	
	@BeforeTest
	public void config()
	{
		
//ExtentReport , ExtentSparkRepoter
		
		String path = System.getProperty("user.dir")+"\\reportTest\\index.html";
		ExtentSparkReporter reporter = new  ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Testing");
		reporter.config().setDocumentTitle("Project");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vinayak Ambure");
		
	}
	
	
	
	@Test(dataProvider = "dataReq")
	public void flow(HashMap<String, String>input) throws IOException, InterruptedException
	{
		ExtentTest test =extent.createTest("Login Test");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https:\\rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
		driver.findElement(By.id("userPassword")).sendKeys(input.get("password"));
		driver.findElement(By.id("login")).click();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		
	
		driver.close();
		extent.flush();
	}
	
	
	@DataProvider
	public Object[][] dataReq() throws IOException
	{
		//datamap = new StringToHashMap();
		List<HashMap<String, String>> data = jsonReader(System.getProperty("user.dir") +"\\src\\test\\java\\TestData\\Login.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	
	
}
