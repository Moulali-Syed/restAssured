package basics;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class EPayloadFromStaticFile {

	public static void main(String[] args) throws IOException {
		//Step1: set the baseURI
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				
				//step2
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\addPlace.json")))).when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200);
	}
}
