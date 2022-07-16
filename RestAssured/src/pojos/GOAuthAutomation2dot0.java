package pojos;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class GOAuthAutomation2dot0 {

	
	public static void main(String[] args) throws InterruptedException {
		
		String[] courseTitles = {"Selenium WebDriver Java","Cypress","Protractor"};
		/*
		//Because of latest updates by google , we cannot automate the authentication
		//so we have to do it manually
		//get authorization code - for this we need browser invocation
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("demo123");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("demo123");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();
		*/
		String url = "getThis Url by doing manually";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		
		//get access token - its a post request
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParam("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken  = js.getString("access_token");
		
		
		//using the above generated access token
		
		//when passing the payload , if content-type is text/html then give expect().defaultParser(Parser.JSON)
		
		BGetCourse gc = given().queryParam("access_token", "accessToken")
				.expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(BGetCourse.class);
		
		//now we can access all methods
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		//get a course title which is present inside api
		System.out.println( gc.getCourses().getApi().get(1).getCourseTitle());
		
		//what if we added many courses under the api , and we need a particular course price
		//we can get all the courses , store it in a list and iterate through it and get the required
		List<EApi> apiCourses = gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
			 System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		//print course titles from webAutomation
		ArrayList<String> a = new ArrayList<>();
		List<DWebAutomation> webCourses = gc.getCourses().getWebAutomation();
		
		for(int i=0;i<webCourses.size();i++) {
			System.out.println(webCourses.get(i).getCourseTitle());
			a.add(webCourses.get(i).getCourseTitle());
			
		}
		
		List<String> expectedList =  Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
		
	}
}
