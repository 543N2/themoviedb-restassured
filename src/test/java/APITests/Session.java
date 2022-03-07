package APITests;

import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Session {

    private String token;
    private String sessionId;

    public void requestToken(Endpoint endpoint){
        setToken(
                given().
                        queryParam("api_key", endpoint.getApiKey())
                .when()
                        .get(endpoint.getToken())
                .then()
                        .extract()
                        .response()
                        .getBody()
                        .path("request_token")
                        .toString()
        );
    }

    public void loginWithUserAndPassword(User user, Endpoint apidata){

        Map<String,Object> bodyLogin = new HashMap<>();
        bodyLogin.put("username", user.getUsername());
        bodyLogin.put("password", user.getPassword());
        bodyLogin.put("request_token", getToken());

        given().
                contentType(ContentType.JSON)
                .body(bodyLogin)
        .when()
                .queryParam("api_key", apidata.getApiKey())
                .post(apidata.getLogin())
        .then()
                .statusCode(200);
    }

    public void requestSessionId(Endpoint apidata){

        Map<String,Object> bodySession = new HashMap<>();
        bodySession.put("request_token", getToken());

        setSessionId(
                given()
                        .contentType(ContentType.JSON)
                        .body(bodySession)
                .when()
                        .queryParam("api_key", apidata.getApiKey())
                        .post(apidata.getSession())
                .then()
                        .extract()
                        .response()
                        .getBody()
                        .path("session_id")
                        .toString()
        );
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getSessionId(){
        return sessionId;
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
}
