package Hackathon;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	
	public static WebDriver driver;
	
	static String projectPath=System.getProperty("user.dir");
	
	static Properties prop=new Properties();
	
	public static WebDriver getWebDriver()
    {
    	
    	try {
			FileInputStream file = new FileInputStream(projectPath
					+ "//Configuration//config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

		String browser = prop.getProperty("browserName");
		     
			   //Instantiating driver based on the type of browser
			   //Launch the browser
			   if(browser.equalsIgnoreCase("chrome"))
			   {
				   System.setProperty("webdriver.chrome.driver",projectPath+"\\Drivers\\chromedriver.exe");
			       driver = new ChromeDriver(); 
			   }
			   
			   else if(browser.equalsIgnoreCase("firefox"))
			   {
				   System.setProperty("webdriver.gecko.driver",projectPath+"\\Drivers\\geckodriver.exe");
			       driver = new FirefoxDriver(); 
			   }
			   else if(browser.equalsIgnoreCase("edge"))
			   {
				   System.setProperty("webdriver.edge.driver",projectPath+"\\Drivers\\msedgedriver.exe");
			       driver = new EdgeDriver(); 
			   }
			   else
			   {
				   System.out.println("Browser Not Found");
			   }
			   
			   			   
			   //Maximize the browser
	 	       driver.manage().window().maximize();
			   return driver;
	 
	 }

	
}
