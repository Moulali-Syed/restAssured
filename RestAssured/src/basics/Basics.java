package basics;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;//add this static import manually

public class Basics {
	
	public static void main(String[] args) {
		
		//validate if Add place API is working as expected
		
		/*
		 * RestAssured works on 3 principles
		 * 
		 * given  - all input details we are giving for the API are wrapped under given method
		 * 			[queryParam() , header() , body() will be in given()]
		 * 
		 * when   - submit the API [resource and http method will be in when]
		 * 
		 * 
		 * then   -  validate the response[any validations will be in then() method]
		 * 
		 * to log everything we use log().all()
		 */
		
		//Step1: set the baseURI
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//step2
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
	}

}
