package basics;

import file.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		//1. Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
//		2.Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//3. Print Title of the first course
		String firstTitle = js.get("courses[0].title");
		System.out.println(firstTitle);
		
		
		//4. Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
		String courseTitles  = js.get("courses["+i+"].title");
		System.out.println(js.get("courses["+i+"].price").toString());
		System.out.println(courseTitles);
		}
	}
}
