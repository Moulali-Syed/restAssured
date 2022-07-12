package basics;

import org.testng.Assert;

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
		
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String title = js.get("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA")) {
				int noOfCopies = js.getInt("courses["+i+"].copies");
				System.out.println(noOfCopies + " copies of RPA");
				break;
			}
		}
//		int noOfCopies = js.getInt("courses[2].copies");
//		System.out.println(noOfCopies);
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		int sum = 0;
		
		for(int i=0;i<count;i++) {
			int amt = js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
			sum+=amt;
		}
		
		Assert.assertEquals(purchaseAmt,sum);
		System.out.println(purchaseAmt+" "+sum);
	}
}
