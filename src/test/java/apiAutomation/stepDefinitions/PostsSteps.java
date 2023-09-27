package apiAutomation.stepDefinitions;
import apiAutomation.requestBuilder.PostsRequestBuilder;
import apiAutomation.utils.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsSteps extends TestBase {
    PostsRequestBuilder request = new PostsRequestBuilder();
    static Response response;
    int id;

    @Given("User sends GET request to posts URL and validates status code")
    public void user_sends_GET_request_to_posts_URL_validates_status_code() {
        response = given().
                contentType(ContentType.JSON).
                when().get(property.getProperty("postsUri"));
        response.then().statusCode(200);

    }

    @Given("User sends POST request to posts URL")
    public void user_sends_POST_request_to_posts_URL() throws JsonProcessingException {
        request.setTitle("foo");
        request.setBody("bar");
        request.setUserId(12);
        ObjectMapper objectMapper = new ObjectMapper();
        String respBod = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        response = given().contentType(ContentType.JSON).
                body(respBod).when().
                post(property.getProperty("postsUri"));
    }

    @Given("User validates status code {int}")
    public void user_validates_status_code(Integer int1) {
        response.then().statusCode(int1);
    }

    @Given("User retrieves ID")
    public void user_retrieves_ID() {
        id = JsonPath.read(response.asString(), "$.id");
    }

    @Given("User sends GET request to posts URL with the id of POST call")
    public void user_sends_GET_request_to_posts_URL_with_the_id_of_POST_call() {
        response = given().contentType(ContentType.JSON).
                when().get(property.getProperty("postsUri") + "/" + id);
        response.then().statusCode(200);
        assertEquals("foo", request.getBody(), "Body is not matching");
        assertEquals("bar", request.getTitle(), "Title is not matching");
        assertEquals(12, request.getUserId(), "UserId is not matching");
    }

    @Given("User sends PUT request with body to posts URL id")
    public void user_sends_PUT_request_with_body_to_posts_URL_id() throws JsonProcessingException {
        request.setBody("Hello KWI");
        request.setTitle("greetings");
        request.setUserId(14);
        request.setId(id);
        ObjectMapper mapper = new ObjectMapper();
        String respBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        response = given().contentType(ContentType.JSON).
                body(respBody).when().put(property.getProperty("postsUri") + "/" + id);

    }

    @Then("User validates body provided on PUT call")
    public void user_validates_body_provided_on_PUT_call() throws JsonProcessingException {
        response = given().contentType(ContentType.JSON).
                when().get(property.getProperty("postsUri") + "/" + id);
        ObjectMapper obj = new ObjectMapper();
        PostsRequestBuilder request = obj.readValue(response.asString(), PostsRequestBuilder.class);
        assertEquals("Hello KWI", request.getBody(), "Body is not matching");
        assertEquals("greetings", request.getTitle(), "Title is not matching");
        assertEquals(14, request.getUserId(), "UserId is not matching");

    }

//These are Automation scripts for each step in CUCUMBER
    @Given("User sends PATCH request with change of element to posts URL id")
    public void user_sends_PATCH_reuqest_with_change_of_element_to_posts_URL_id() throws JsonProcessingException {
        request.setBody("I love Java");
        ObjectMapper obj = new ObjectMapper();
        String payload = obj.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        response = given().body(payload).when().patch(property.getProperty("postsUri") + "/" + id);
    }

    @Given("User sends GET request to posts URL with the id of PATCH call")
    public void user_sends_GET_request_to_posts_URL_with_the_id_of_PATCH_call() {
        response = given().contentType(ContentType.JSON).
                when().
                get(property.getProperty("postsUri") + "/" + id);
    }

    @Given("User sends DELETE request to posts URL with id")
    public void user_sends_DELETE_request_to_posts_URL_with_id() {
        response = given().when().delete(property.getProperty("postsUri") + "/" + id);
        response.then().statusCode(200);
    }

    @Then("User validates payload id is not existing")
    public void user_validates_payload_id_is_not_existing() {
        response = given().when().get(property.getProperty("postsUri") + "/" + id);
        response.then().statusCode(404);
    }
}
