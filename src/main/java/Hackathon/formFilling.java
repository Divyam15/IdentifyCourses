package Hackathon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//REGRESSION TEST
public class formFilling extends DriverSetup{

	 

	String parentWindow;
	static String projectPath=System.getProperty("user.dir");
    static WebDriver driver;
    static Properties prop=new Properties();

	@BeforeSuite(groups = { "Regression" })
	public static WebDriver openBrowser() {
		driver =DriverSetup.getWebDriver();
		return driver;

	}

	/**
	 * Get the homepage Search for web development courses In Home page, go to
	 * "For Enterprise"
	 */
	@Test(priority = 1, groups = { "Regression" })
	public void ForEnterprise() {
		// driver.manage().window().maximize();
		try {
			FileInputStream file = new FileInputStream(projectPath
					+ "//Configuration//config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String HomePage=prop.getProperty("url");
		driver.get(HomePage);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Get the homepage
		driver.get(HomePage);

		// Click "For Enterprise"
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

         //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.linkText("For Enterprise")).click();	
		

	}

	/**
	 * Look into Courses for Campus under Product Fill the "Ready to transform"
	 * form with any one input invalid (example: email) Capture the error
	 * message & display
	 */
	@Test(priority = 2, groups = { "Regression" })
	public void FillForm() throws IOException {

		// Look into Courses for Campus under Product
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String parentWindow = driver.getWindowHandle();
		Actions action = new Actions(driver);
		WebElement ProductHover = driver.findElement(By
				.xpath("//a[text()=\"Products\"]"));
		action.moveToElement(ProductHover);
		action.build().perform();

		driver.findElement(By.xpath("//*[@id=\"menu-item-4901\"]/a")).click();

		// Locate the excel sheet to be read
		File src = new File(projectPath + "\\Resources\\HacathonExcel.xlsx");
		FileInputStream stream = new FileInputStream(src);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Sheet1");

		// Fill the "Ready to transform" form from the data given in excel sheet
		String FirstName = sheet.getRow(1).getCell(0).getStringCellValue();
		String LastName = sheet.getRow(1).getCell(1).getStringCellValue();
		String JobTitle = sheet.getRow(1).getCell(2).getStringCellValue();
		String Email = sheet.getRow(1).getCell(3).getStringCellValue();
		 long PhoneNumber = (long)sheet.getRow(1).getCell(4).getNumericCellValue();
		// Change the phone number from integer to string
		String phone = String.valueOf(PhoneNumber);
		String InstituteName = sheet.getRow(1).getCell(5).getStringCellValue();
		String InstituteType=sheet.getRow(1).getCell(6).getStringCellValue();
		String PrimaryDisipline=sheet.getRow(1).getCell(7).getStringCellValue();
		String Country=sheet.getRow(1).getCell(8).getStringCellValue();
		String State=sheet.getRow(1).getCell(9).getStringCellValue();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Set<String> handle = driver.getWindowHandles();
		for (String childWindow : handle) {
			driver.switchTo().window(childWindow);
		}

		try {

			// Fill the "Ready to transform" form from the data given in excel
			// sheet
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			PageFactory.initElements(driver, PomClass.class);
			PomClass.Fname.sendKeys(FirstName);

			PomClass.Lname.sendKeys(LastName);

			Select select = new Select(PomClass.JobFunction);
			select.selectByIndex(1);

			PomClass.Jobtitle.sendKeys(JobTitle);

			PomClass.Email.sendKeys(Email);

			PomClass.Phone.sendKeys(phone);

			PomClass.Institutename.sendKeys(InstituteName);

			book.close();
			Select select1 = new Select(PomClass.InstituteType);
			select1.selectByVisibleText(InstituteType);

			Select select2 = new Select(PomClass.PrimaryDisipline);
			select2.selectByVisibleText(PrimaryDisipline);

			Select select3 = new Select(PomClass.Country);
			select3.selectByVisibleText(Country);

			Select select4 = new Select(PomClass.State);
			select4.selectByVisibleText(State);

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PomClass.Submit.click();

			// Capture the error message & display
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String Error = PomClass.ErrorMessage.getText();
			Reporter.log("Error : " + Error);
			File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(screenshot, new File(projectPath
						+ "\\screenshots\\Regression.png"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			driver.close();
			driver.switchTo().window(parentWindow);
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Close the browser
	 */
	@AfterSuite(groups = { "Regression" })
	public void closeBrowser() {
		driver.quit();
	}

}
