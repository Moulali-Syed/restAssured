package DesignPatterns;

public class TravelHomePage {

	//to go to all pages - class use
	
	public NavigationBar getNavigationBar() {
		return new NavigationBar();
	}
	
	public FooterNav getFooter() {
		return new FooterNav();
	}
}
