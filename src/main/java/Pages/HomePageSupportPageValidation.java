package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageSupportPageValidation {
	
	public static WebDriver driver;
	
	/* WebElement Repo */
	
	@FindBy(xpath = "/html/body/div[1]/main/div/h2[1]/text()")
	WebElement HomePageText;
		
	@FindBy(xpath = "//a[@data-key='request-output-link']/span")
	WebElement RequestURL;
	
	@FindBy(xpath = "//*[@data-key='response-code']")
	WebElement ResponseURL;
	
	@FindBy(xpath = "//button//a[@href='#support-heading']")
	WebElement SupportReqResEle;
	
	@FindBy(xpath = "//button[@id='trigger-pro']")
	WebElement UpgradeButton;	
	
	@FindBy(xpath = "//*[@id='mc_embed_signup_scroll']/div[1]/label")
	WebElement UpgradeEmailAddress;
	
	@FindBy(xpath = "//*[@id='mc_embed_signup_scroll']/div[1]/label")
	WebElement UpgradeSubscribeButton;
	
	
	/* Constructor Call */
	public HomePageSupportPageValidation() {
		PageFactory.initElements(driver, this);
	}
	
	public void HomePageValidation() throws InterruptedException {
		
		driver = new ChromeDriver();
		driver.get("https://reqres.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
		
		System.out.println("Current URL is: "+driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));	
	}	
	public void RequestMethodsList() throws InterruptedException {
		WebElement FirstReqMethod = driver.findElement(By.xpath("//*[@data-key='endpoints']/ul/li[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", FirstReqMethod);
		Thread.sleep(5000);
		List<WebElement> RequestMethodsList = driver.findElements(By.xpath("//*[@data-key='endpoints']/ul/li"));
		for(int i=0; i<RequestMethodsList.size(); i++) {
		String RequestMethodText = RequestMethodsList.get(i).getAttribute("data-http");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		System.out.println("Request Methods are: "+RequestMethodText);
		if(RequestMethodText.isEmpty()) {
			System.out.println("Request Methods Lists are not available");
		}else {
			System.out.println("Request Methods Lists are available");
		}
	
		}
	}	
	public void RequestTypesList() throws InterruptedException {		
		List<WebElement> EndPointsListsEle = driver.findElements(By.xpath("//*[@data-key='endpoints']/ul/li/a"));
		for(int i=0; i<EndPointsListsEle.size(); i++) {
		String EndPointsText = EndPointsListsEle.get(i).getText();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		System.out.println("Request Types are: "+EndPointsText);
		if(EndPointsText.isEmpty()) {
			System.out.println("Request Types are not available");
		}else {
			System.out.println("Request Types are available");
		}
		}
	}
	public void ClickOnEndpoint() throws InterruptedException {
		System.out.println("Click on Single User End Point");
		WebElement Endpoint = driver.findElement(By.xpath("(//a[@data-key='try-link'])[3]"));
		Endpoint.click();
		Thread.sleep(8000);
	}
	public void ViewRequestResponseDetails() throws InterruptedException {	
		WebElement ReqURLEle = driver.findElement(By.xpath("//*[@data-key='url']"));
		String ReqURLString = ReqURLEle.getText();
		Thread.sleep(5000);	
		System.out.println("Request URL is: "+ReqURLString);
		if(ReqURLString.contains("/api/users/23")) {
			System.out.println("Expected Request URL is available");
		}else {
			System.out.println("Expected Request URL is not available");
		}
		WebElement RespCodeLEle = driver.findElement(By.xpath("//*[@data-key='response-code']"));
		String RespCode = RespCodeLEle.getText();
		Thread.sleep(5000);
		System.out.println("Response Code is: "+RespCode);	
		if(RespCode.contains("404")) {
			System.out.println("Expected Request URL is available");
		}else {
			System.out.println("Expected Request URL is not available");
		}	
	}
	
	/* Support Page */
	
	public void SupportReqResButton() throws InterruptedException {	
		JavascriptExecutor jsexec = (JavascriptExecutor)driver;
		jsexec.executeScript("window.scrollBy(0, -350);");
		Thread.sleep(5000);
		
		//WebElement SupportReqResEle = driver.findElement(By.xpath("//button//a[@href='#support-heading']"));

		if(SupportReqResEle.isDisplayed()) {
			Thread.sleep(5000);
			System.out.println("Support ReqRes Button is displayed: "+SupportReqResEle.isDisplayed());
			Thread.sleep(5000);
			SupportReqResEle.click();
			Thread.sleep(5000);
		}else
		{
			System.out.println("Support ReqRes Button is not displayed"+SupportReqResEle.isDisplayed());
		}		
	}
	public void SupportPageListOfOptions() throws InterruptedException {		
		List<WebElement> SupportPageListEle = driver.findElements(By.xpath("//*[@id='supportForm']/div/label"));
		for(int i=0; i<SupportPageListEle.size(); i++) {
		String SupportPageListOptions = SupportPageListEle.get(i).getText();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		System.out.println("Support Page List of Options are: "+SupportPageListOptions);
		if(SupportPageListOptions.isEmpty()) {
			System.out.println("Support Page List of Options are not available");
		}else {
			System.out.println("Support Page List of Options are available");
		}
		}
	}
	public void UpgradeDetails() throws InterruptedException {
		if(UpgradeButton.isDisplayed()) {
			Thread.sleep(5000);
			System.out.println("Upgrade Button is displayed: "+UpgradeButton.isDisplayed());
			Thread.sleep(5000);
			UpgradeButton.click();
			Thread.sleep(5000);
		}else
		{
			System.out.println("Upgrade Button is not displayed: "+UpgradeButton.isDisplayed());
		}		
	}
	public void UpgradeFieldsAvailability() throws InterruptedException {
		if(UpgradeEmailAddress.isDisplayed()) {
			Thread.sleep(5000);
			System.out.println("Email Address Field is displayed: "+UpgradeEmailAddress.isDisplayed());
			Thread.sleep(5000);
		}else
		{
			System.out.println("Email Address Field is not displayed: "+UpgradeEmailAddress.isDisplayed());
		}
		if(UpgradeSubscribeButton.isDisplayed()) {
			Thread.sleep(5000);
			System.out.println("Subscribe Button is available: "+UpgradeSubscribeButton.isDisplayed());
			Thread.sleep(5000);
		}else
		{
			System.out.println("Subscribe Button is not available: "+UpgradeSubscribeButton.isDisplayed());
		}
		
	}	
}
