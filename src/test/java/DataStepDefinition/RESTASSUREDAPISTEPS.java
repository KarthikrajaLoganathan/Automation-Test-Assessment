package DataStepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.net.MalformedURLException;
import Pages.APIAction;


public class RESTASSUREDAPISTEPS {

    @Given("User set {string} service endpoint")
    public void userSetServiceEndpoint(String endPoint) throws MalformedURLException {
    	APIAction.setServiceEndpoint(endPoint);
    }
    
    @When("User perform {string} request type for {string}")
    public void userPerformRequestType(String requestType, String apiName) throws IOException {
        APIAction.setRequestType(requestType, apiName);
    }
    
    @And("User verifies the response code for {string}")
    public void userVerifiesTheResponseCodeFor(String apiName) throws IOException {
    	APIAction.checkStatusCode(apiName);
    }

    @And("User verifies the request path for {string}")
    public void userVerifiesTheRequestPathFor(String apiName) throws IOException {
    	APIAction.checkPathURL(apiName);
    } 

    @And("User verifies the response data for {string}")
    public void userVerifiesTheResponseDataFor(String apiName) throws IOException {
    	APIAction.verifyResponseBody(apiName);
    }
}

