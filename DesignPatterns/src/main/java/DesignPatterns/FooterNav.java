package DesignPatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//https://rahulshettyacademy.com/dropdownsPractise/
public class FooterNav extends AbstractComponent{

	
	//method to handle flights
	
	
	//when selenium executes any method in this class - scope of selenium
	//should be in footer only
	
	//to do so - select the entire footer section
	
	WebDriver driver;
	
	
	
	By flights = By.cssSelector("[title='Flights']");
	
	public FooterNav(WebDriver driver, By sectionElement) {

		super(driver,sectionElement);//when you inherit parent class you should invoke parent class
		//constructor in your own child constructor
	}

	public void selectFlight() {
		
		//we need customized findElement , so that scope is limited to footer section
		findElement(flights).click();
	}
}
