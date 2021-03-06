package specBuilder;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class SpecBuilderTest {

	public static void main(String[] args) {
		
		TAddPlace p  = new TAddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhoneNumber("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList = new ArrayList<>();
		myList.add("shoe-park");
		myList.add("shop");
		p.setTypes(myList);
		ULocation l = new ULocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//request spec builder
		RequestSpecification req =   new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		
		RequestSpecification res = given().spec(req)
				.body(p);
		
		//response spec builder
		ResponseSpecification resspec  = new ResponseSpecBuilder().expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		
		
		Response resp =  res
		.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		
		String responseString = resp.asString();
		System.out.println(responseString);
	}
}
