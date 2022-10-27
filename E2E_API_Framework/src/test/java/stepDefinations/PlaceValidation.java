package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceValidation extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload(String name, String lang, String add) throws IOException {
						
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, lang, add));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
		//Here enum class constructor will be called with value of resource
		APIResources resAPI = APIResources.valueOf(resource);
		
//		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		if (httpMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resAPI.getResource());
//					.then().spec(resspec).extract().response();
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = res.when().get(resAPI.getResource());
		}
		
	}

	@Then("API call is successful with status code {int}")
	public void api_call_is_successful_with_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200);
	    
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String expectedkey, String expectedValue) {
		
		assertEquals(getJsonPath(response, expectedkey).toString(), expectedValue);
		
	}
	
	@And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_something_using_something(String expectedName, String resource) throws IOException {
		
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name"); 
		assertEquals(actualName, expectedName);
    }
	
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
