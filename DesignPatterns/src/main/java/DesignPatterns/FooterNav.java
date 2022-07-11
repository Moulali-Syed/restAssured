package DesignPatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//https://rahulshettyacademy.com/dropdownsPractise/
public class FooterNav extends AbstractComponent{

	
	//method to handle flights
	
	
	//when selenium executes any method is this class - scope of selenium
	//should be in footer only
	
	//to do so - select the entire footer section
	
	WebDriver driver;
	
	By sectionElement = By.id("traveller-home");
	
	By flights = By.cssSelector("[title='Flights']");
	
	public void selectFlight() {
		
		//we need customized findElement , so that scope is limited to footer section
		driver.findElement(flights).click();
	}
}
