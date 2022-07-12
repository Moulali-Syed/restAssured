package DesignPatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelHomePage {

	//to go to all pages - class use
	By sectionElement = By.id("traveller-home");
	WebDriver driver;
	
	public NavigationBar getNavigationBar() {
		return new NavigationBar();
	}
	
	public FooterNav getFooter() {
		return new FooterNav(driver,sectionElement);
	}
}
