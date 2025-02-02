package FIS_AssessmentUIAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class coinDeskGetRequest {
    public static void main(String args[]){

        coinDeskGetrequest();
    }
    public static void coinDeskGetrequest(){
        String baseURL="api.coindesk.com/v1/bpi/currentprice.json";
        Response response=given().contentType(ContentType.JSON).auth().oauth().get(baseURL);
        response.prettyPrint();
        //Note:Here in the requirement Authentication details are not provided so i have written the snippets by assuming  oauth

        // Step 2: Verify the status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Step 3: Verify there are 3 BPIs (USD, GBP, EUR)
        Assert.assertTrue(response.jsonPath().getMap("bpi").containsKey("USD"), "USD is missing in BPIs");
        Assert.assertTrue(response.jsonPath().getMap("bpi").containsKey("GBP"), "GBP is missing in BPIs");
        Assert.assertTrue(response.jsonPath().getMap("bpi").containsKey("EUR"), "EUR is missing in BPIs");

        // Step 4: Verify the GBP description equals 'British Pound Sterling'
        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals(gbpDescription, "British Pound Sterling", "GBP description is incorrect");
    }
}
