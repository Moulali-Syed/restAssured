package pojos;

import java.util.List;

//after creating this go back to main class change return type of Courses
public class CCourses {

	private List<DWebAutomation> webAutomation;
	private List<EApi> api;
	private FMobile mobile;

	public List<DWebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<DWebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<EApi> getApi() {
		return api;
	}

	public void setApi(List<EApi> api) {
		this.api = api;
	}

	public FMobile getMobile() {
		return mobile;
	}

	public void setMobile(FMobile mobile) {
		this.mobile = mobile;
	}

}
