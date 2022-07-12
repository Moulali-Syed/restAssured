package basics;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.Payload;
import file.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DDynamicJson {

	@Test(dataProvider="Books")
	public void addBook(String aisle,String isbn) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json").
		body(Payload.addBook(aisle,isbn))
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		//after this we delete the book
	}
	
	
	@DataProvider(name="Books")
	public Object[][] getData() {
		//array -collection of elements
		//multidimensional array - collection of arrays
		
		return new Object[][] {{"ojfgtha","87678"},{"qssdbma","864889"},{"nmzgakd","90568"}};
	}
}
