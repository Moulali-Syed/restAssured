package basics;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import file.Payload;
import file.ReUsableMethods;


public class CAssertionsOnJSONResponseBodyAndHeaders {

	public static void main(String[] args) {

		//Step1: set the baseURI
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//step2
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		//lets extract the place_id from response
		//for this we have a class JsonPath , create object 
		//it will take string and convert it to JSON , it has so many methods to parse Json
		JsonPath js = new JsonPath(response);//for parsing json
		String placeId = js.getString("place_id");//give the path of key whichever value we need from response
		System.out.println(placeId);
		
		
		//update place
		String newAddress = "70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
		//get place
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
//		JsonPath js1 = new JsonPath(getPlaceResponse);
		//instead now use the reusable method
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}
}
