package pojos;

/*
 {
"message":"Hello",
"greet":"Hi"
 }
 
 */
public class AMessage {

	private String message;
	private String greet;
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setGreet(String greet) {
		this.greet = greet;
	}

	public String getGreet() {
		return greet;
	}
}

/*
 * now with above code we have created , below json with out any values 
 {
"message":"",
"greet":""
 }
 */

/*
//create java objects
Message m = new Message();
m.setMessage("Hello");

*/