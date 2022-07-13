package basics;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class FJiraTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8080";
		
		//Login Scenario
		//instead of using JsonPath class and extracting the required key value
		//we have SessionFilter class , when ever we know that a response returns a session
		//which will be used in subsequent requests , u simply create object of SessionFilter class
		//and pass that object using the filter(sessionObject)
		
		SessionFilter session = new SessionFilter();
		String response = given().relaxedHTTPSValidation().header("Content-Type","application/json").body("{\r\n"
				+ "\r\n"
				+ "\"username\":\"demoUser\",\r\n"
				+ "\"password\":\"demo123@\"\r\n"
				+ "\r\n"
				+ "}").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all(). extract().response().asString();
		
		String expectedMessage = "Hi , How are you ?";
	
		//adding a comment
		//if we see something in {} - these braces , it means path parameter
		//if it query we have ?
		
		String addCommentResponse = given().pathParam("key", "10101").log().all().header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"body\": \""+expectedMessage+" \",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		.filter(session).when().post("/rest/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
	
		JsonPath js1 = new JsonPath(addCommentResponse);
		String commentId = js1.getString("id");
	
	
		//Add attachement - sending file
		
		//to send any attachment in rest assured we use multipart method - multiPart("file",new File("C:\\Testing\\RestAssuredJava\\RestAssured\\jira.txt"))
		//when we are sending file as input , we must use header as 
		given().header("X-Atlassian-Token","no-check").filter(session).
		pathParam("key","10101").
		header("Content-Type","multipart/form-data").
		multiPart("file",new File("C:\\Testing\\RestAssuredJava\\RestAssured\\jira.txt")). when()
		.post("/rest/api/2/issue/{key}/attachments").then()
		.log().all().assertThat().statusCode(200);
		
		
		//get issue - using path and query parameters 
		String issueDetails = given().filter(session).pathParam("key", "10101").queryParam("fields", "comment").log()
				.all().when().get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();
		System.out.println(issueDetails);

		JsonPath js = new JsonPath(issueDetails);
		
		
		int commentsCount = js.get("fields.comment.comments.size()");
		for (int i = 0; i < commentsCount; i++) {
			String commentIdIssue = js.get("fields.comment.comments[" + i + "].id");
			if (commentIdIssue.equalsIgnoreCase(commentId)) {
				String message = js.get("fields.comment.comments[" + i + "].id").toString();
				System.out.println(message);
				Assert.assertEquals(message, expectedMessage);
			}
		}
		
	}
}
