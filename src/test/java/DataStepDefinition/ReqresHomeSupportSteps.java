package DataStepDefinition;

import Pages.HomePageSupportPageValidation;
//import Pages.SupportPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ReqresHomeSupportSteps {
	
	HomePageSupportPageValidation homePage = new HomePageSupportPageValidation();
	
	@Given("^Navigate to Reqres Home Page$")
	public void navigate_to_reqres_home_page() throws InterruptedException {
		homePage.HomePageValidation();
	}
	
	@When("^Ensure that different Request Types and End Points are listed$")
	public void ensure_that_different_request_types_and_end_points_are_listed() throws InterruptedException {
	    homePage.RequestMethodsList();
	    homePage.RequestTypesList();
	}

	@And("^Click on specific endpoint$")
	public void click_on_specific_endpoint() throws InterruptedException {
	    homePage.ClickOnEndpoint();
	}

	@And("^Validate whether the Request and Response details are displayed$")
	public void validate_whether_the_request_and_response_details_are_displayed() throws InterruptedException {
		homePage.ViewRequestResponseDetails();
	}

	@When("^Verify that Support Page navigation button is available$")
	public void verify_that_support_page_navigation_button_is_available() throws InterruptedException {
	    homePage.SupportReqResButton();
	}

	@And("^Ensure that One Time and Monthly support options are available$")
	public void ensure_that_one_time_and_monthly_support_options_are_available() throws InterruptedException {
	    homePage.SupportPageListOfOptions();
	}

	@And("^Ensure that Upgrade details are available$")
	public void ensure_that_upgrade_details_are_available() throws InterruptedException {
	    homePage.UpgradeDetails();
	    homePage.UpgradeFieldsAvailability();
	}


}
