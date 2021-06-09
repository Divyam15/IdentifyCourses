package Hackathon;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


//SMOKE TEST
public class IdentifyCourses extends DriverSetup{
	
	
	String parentWindow;
	static String projectPath=System.getProperty("user.dir");
    static WebDriver driver;
    static Properties prop=new Properties();
	
    @BeforeTest(groups= {"Smoke","Regression"})  
	public static WebDriver openBrowser()
    {
    	driver =DriverSetup.getWebDriver();
    	return driver;
    	
	}
	
	/** 
	* Search for web development courses
	*/
	@Test(priority=1,groups= {"Smoke"})
	public void searchCourse()
	{
		//driver.manage().window().maximize();
		try {
			FileInputStream file = new FileInputStream(projectPath
					+ "//Configuration//config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String HomePage=prop.getProperty("url");
		driver.get(HomePage);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.className("react-autosuggest__input")).click();
		
		//Enter web development courses and click enter key
		driver.findElement(By.className("react-autosuggest__input")).sendKeys("web development courses" + Keys.ENTER);
		
		
	}
	
	/** 
	* Apply filter for Language
    * Extract all the languages with its total count & display them 
	*/
	@Test(priority=2,groups= {"Smoke"})
	public void selectLanguage() 
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageFactory.initElements(driver, PomClass.class);
		
		// Look for Language 
		
		PomClass.Language.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Extract all the languages with its total count
		List<WebElement> countLang=driver.findElements(By.xpath("//div[@class='checkboxText']"));
		Reporter.log("Total count of Languages : "+countLang.size()); 
		Reporter.log("");
		for(WebElement lang1:countLang)
		{ 
			 String languages=lang1.getText();
			 Reporter.log(languages); 
		}
			
		//Select English Language
		PomClass.English.click();
	}
	
	/**
	* Apply filter for Level
    * Extract all the levels with its total count & display them 
	*/
	@Test(priority=3,groups= {"Smoke"})
	public void selectLevel()
	{
		
		
		// Look for Level
		PomClass.Level.click();
		
		
		//Extract all the levels with its total count
		List<WebElement> countLevel=driver.findElements(By.xpath("//div[@class='checkboxText']"));
		Reporter.log(""); 
		Reporter.log("Total count of Level : "+countLevel.size()); 
		for(WebElement lang2:countLevel)
		{ 
			 String levels=lang2.getText();
			 Reporter.log(levels); 
		}
		
		//Select Beginner Level
		
		
			PomClass.Beginner.click();
		
	}
	
	/**
	 * Extract the course names, total learning hours & rating for first course
	 */
	@Test(priority=4,groups= {"Smoke"})
	public void firstCourse() 
	{
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		//WebElement FirstCourse=wait.until(ExpectedCondition.elementToBeClickable(PomClass.FirstCourse));
		
	    Reporter.log(" ");
	    Reporter.log("Details of First Course :");
	    Reporter.log(" ");
    	
	    //Extract the name of the first course
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
    	String courseName1=PomClass.CourseName1.getText();
    	Reporter.log("Course Name - " + courseName1);
    	
    	//Extract the rating of the first course
    	String courseRating1=PomClass.Rating1.getText();
    	Reporter.log("Course Rating - " + courseRating1);
    	
    	parentWindow=driver.getWindowHandle();
		
	    wait.until(ExpectedConditions.elementToBeClickable(PomClass.FirstCourse));
	    PomClass.FirstCourse.click();
	    
	    Set<String> handles1=driver.getWindowHandles();
	    
	    for(String childWindow1:handles1)
	    {
	    	if(!childWindow1.contentEquals(parentWindow))
	    	{
	    		
	        //Navigate to child window
	    	driver.switchTo().window(childWindow1);
	    	
	    	//Extract the duration of the first course
	    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    	String courseDuration1=PomClass.Duration1.getText();
	    	Reporter.log("Course Duration - " + courseDuration1);
	    	
	    	driver.close();
	    	
	    	}
	    	
	    	//Get back to Parent window
	    	driver.switchTo().window(parentWindow);
	    }
	}
	
	/**
	 * Extract the course names, total learning hours & rating for second course
	 */
	@Test(priority=5,groups= {"Smoke"},dependsOnMethods="firstCourse")
	public void secondCourse() 
	{

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PomClass.SecondCourse.click();

		Reporter.log(" ");
		Reporter.log("Details of Second Course :");
		Reporter.log(" ");
 
		// Extract the name of the second course
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String courseName2 = PomClass.CourseName2.getText();
		Reporter.log("Course Name - " + courseName2);

		// Extract the rating of the second course
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String courseRating2 = PomClass.Rating2.getText();
		Reporter.log("Course Rating - " + courseRating2);

		Set<String> handles2 = driver.getWindowHandles();

		for (String childWindow2 : handles2) {
			if (!childWindow2.contentEquals(parentWindow)) {

				// Navigate to child window
				driver.switchTo().window(childWindow2);

				// Extract the duration of the second course
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String courseDuration2 =PomClass.Duration2.getText();
				Reporter.log("Course Duration - " + courseDuration2);

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.close();
			}
		}

		System.out.println("");

		// Get back to Parent window
		driver.switchTo().window(parentWindow);

	}
	
	/**
	 * Takes screenshot and store it in the specified path
	 */
	@Test(priority=5,groups= {"Smoke"})
	public void screenShot()throws InterruptedException
	 {
		 File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try
		 {
			 FileHandler.copy(screenshot, new File(projectPath+"\\screenshots\\Smoke.png"));
		 }
		 catch(IOException e)
		 {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	
	/**
	 * Close the browser
	 */
	@AfterClass(groups= {"Smoke","Regression"})
	public void closeBrowser() 
	{
		driver.quit();
	}

}

