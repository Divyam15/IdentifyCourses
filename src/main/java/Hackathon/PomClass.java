package Hackathon;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

public class PomClass {
	
	@FindBy(xpath= "//*[@id=\"react-select-2--value\"]/div[1]")
	public static WebElement Language;
	
	@FindBy(xpath= "//*[@id=\"react-select-2--option-0\"]/div/button/label/input")
	public static WebElement English;
	
	@FindBy(xpath= "//*[@id=\"react-select-3--value\"]/div[1]")
	public static WebElement Level;
	
	@FindBy(xpath= "//input[@value='Beginner']")
	public static WebElement Beginner;
	
	@FindBy(xpath= "//h2[text()=\"Search Engine Optimization (SEO)\"]")
	public static WebElement FirstCourse;
	
	
	@FindBy(xpath= "//h2[text()=\"Search Engine Optimization (SEO)\"]")
	public static WebElement CourseName1;
	
	@FindBy(xpath= "//*[@id=\"main\"]/div/div/div[1]/div[2]/div/div/div/div/div/ul/li[1]/div/div/div/div/div/div[2]/div[4]/div[1]/div[1]/div/span[1]")
	public static WebElement Rating1;
	
	@FindBy(xpath= "//*[contains(text(),'Approximately 5 months to complete')]")
	public static WebElement Duration1;
	
	@FindBy(xpath= "//h2[text()=\"Introduction to Web Development\"]")
	public static WebElement SecondCourse;
	
	@FindBy(xpath= "//h2[text()=\"Introduction to Web Development\"]")
	public static WebElement CourseName2;
	
	@FindBy(xpath= "//*[@id=\"main\"]/div/div/div[1]/div[2]/div/div/div/div/div/ul/li[2]/div/div/div/div/div/div[2]/div[4]/div[1]/div[1]/div/span[1]")
	public static WebElement Rating2;
	
	@FindBy(xpath= "//*[@id=\"main\"]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[5]/div[2]/div/span")
	public static WebElement Duration2;
	
	@FindBy(id = "FirstName")
	public static WebElement Fname;

	@FindBy(id = "LastName")
	public static WebElement Lname;

	@FindBy(id = "C4C_Job_Title__c")
	public static WebElement JobFunction;

	@FindBy(id = "Title")
	public static WebElement Jobtitle;

	@FindBy(id = "Email")
	public static WebElement Email;

	@FindBy(id = "Phone")
	public static WebElement Phone;

	@FindBy(id = "Company")
	public static WebElement Institutename;

	@FindBy(id = "Institution_Type__c")
	public static WebElement InstituteType;

	@FindBy(id = "Primary_Discipline__c")
	public static WebElement PrimaryDisipline;

	@FindBy(id = "Country")
	public static WebElement Country;

	@FindBy(id = "State")
	public static WebElement State;

	@FindBy(xpath = "//*[@id=\"mktoForm_1512\"]/div[20]/span/button")
	public static WebElement Submit;

	@FindBy(id = "ValidMsgEmail")
	public static WebElement ErrorMessage;

}