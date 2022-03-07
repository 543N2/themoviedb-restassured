package previousFormatTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITests {

    static String url = "https://api.themoviedb.org/3";
    static String api_key = "0383f0931f7f6bb14fe64530a706ce6c";
    static String username = "543n2";
    static String password = "543n2Movies";
    static String token;
    static String session_id;
    String list_id;
    String movie_id = "807";

    @BeforeAll
    public static void setup(){

        RestAssured.baseURI = url;

        String endpointGetToken = "/authentication/token/new";
        String endpointLogInSession = "/authentication/token/validate_with_login";
        String endpointCreateSession = "/authentication/session/new";

        // Get token
        token = given().queryParam("api_key", api_key)
                .when().get(endpointGetToken)
                .then().extract().response().getBody().path("request_token").toString();

        // Login
        Map<String,Object> bodyLogin = new HashMap<>();
        bodyLogin.put("username", username);
        bodyLogin.put("password", password);
        bodyLogin.put("request_token", token);
        given().contentType(ContentType.JSON).body(bodyLogin)
                .when().queryParam("api_key", api_key).post(endpointLogInSession)
                .then().statusCode(200);

        // Session
        Map<String,Object> bodySession = new HashMap<>();
        bodySession.put("request_token", token);
        session_id = given().contentType(ContentType.JSON).body(bodySession)
                .when().queryParam("api_key", api_key).post(endpointCreateSession)
                .then().extract().response().getBody().path("session_id").toString();
    }

    @Test
    public void searchMovie(){

        String movieQuery = "se7en";
        String endpointMovieQuery = "/search/movie";

        Response response = given().queryParam("api_key",api_key).queryParam("query", movieQuery)
                .when().get(endpointMovieQuery)
                .then().extract().response();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(200,response.getStatusCode());
    }

    @Test
    @Order(1)
    public void getMovie() {

        String movieId = "24";
        //String movieId = movie_id;

        String endpointGetMovie = "/movie/" + movieId;

        Response response = given().queryParam("api_key",api_key)
                .when().get(endpointGetMovie)
                .then().extract().response();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(200,response.getStatusCode());
    }

    @Test
    @Order(2)
    public void rateMovie() {

        String movieId = "24";
        //String movieId = movie_id;

        double rating = 9.5;
        String endpointRateMovie = "/movie/" + movieId + "/rating";

        Map<String,Object> bodyRateMovie = new HashMap<>();
        bodyRateMovie.put("value", rating);

        Response response = given().contentType(ContentType.JSON)
                                .queryParam("api_key", api_key)
                                .and().queryParam("session_id", session_id)
                                .body(bodyRateMovie)
                            .when().post(endpointRateMovie)
                            .then().extract().response();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }


    @Test
    @Order(3)
    public void createList(){

        String listName = "ListByAll created in Rest Assured";
        String listDescription = "I don't know what to write";
        String listLanguage = "en";

        String endpointCreateList = "/list";

        Map<String,Object> bodyList = new HashMap<>();
        bodyList.put("name", listName);
        bodyList.put("description", listDescription);
        bodyList.put("language", listLanguage);

        Response response = given()
                                .contentType(ContentType.JSON)
                                .body(bodyList)
                                .queryParam("api_key",api_key)
                                .and().queryParam("session_id",session_id)
                            .when().post(endpointCreateList)
                            .then().extract().response();

        list_id = response.path("list_id").toString();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }

    @Test
    @Order(4)
    public void getListDetails(){

        String listId = "8193620";
        //String listId = list_id;

        System.out.println("OJO ESTA ES LA LISTA ID: " + listId);

        String endpointGetListDetails = "/list/";

        Response response = given()
                                .queryParam("api_key",api_key)
                            .when().get(endpointGetListDetails + listId)
                            .then().extract().response();

        Assertions.assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    @Order(5)
    public void addMovieToList(){

        String listId = "8193620";
        String mediaId = "81";
        //String listId = list_id;
        //String mediaId = movie_id;

        String endpointAddMovie = "/list/" + listId + "/add_item";

        Map<String,Object> bodyMovieList = new HashMap<>();
        bodyMovieList.put("media_id", mediaId);

        Response response = given().contentType(ContentType.JSON)
                .body(bodyMovieList)
                .queryParam("api_key",api_key)
                .and().queryParam("session_id",session_id)
            .when().post(endpointAddMovie)
            .then().extract().response();

        Assertions.assertEquals(201,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    @Order(6)
    public void clearList(){

        String listId = "8193620";
        //String listId = list_id;

        boolean confirm = true;

        String endpointAddMovie = "/list/" + listId + "/clear";

        Response response = given().contentType(ContentType.JSON)
                .queryParam("api_key",api_key)
                .and().queryParam("session_id",session_id)
                .and().queryParam("confirm",confirm)
            .when().post(endpointAddMovie)
            .then().extract().response();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }

    @Test
    @Order(7)
    public void deleteList(){

        String listId = "8193694";
        //String listId = list_id;

        String endpointDeleteList = "/list/" + listId;

        Response response = given().queryParam("api_key",api_key)
                .and().queryParam("session_id", session_id)
                .when().delete(endpointDeleteList)
                .then().extract().response();

        System.out.println(response.prettyPrint());
        Assertions.assertEquals(500,response.getStatusCode());
    }


}
