package APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ListById extends List {

    public ListById(String id){
            this.id = id;
        }

    public Response deleteRequest(Endpoint endpoint, Session session){
        return (
            given()
                    .queryParam("api_key", endpoint.getApiKey())
                    .and().queryParam("session_id", session.getSessionId())
            .when()
                    .delete(endpoint.getListDelete(this.getId()))
            .then()
                    .extract()
                    .response()
        );
    }

    public Response addMovieToListRequest(Endpoint endpoint, Session session, Movie movie){
        Map<String,Object> bodyMovieList = new HashMap<>();
        bodyMovieList.put("media_id", movie.getId());
        return (
                given().contentType(ContentType.JSON)
                        .body(bodyMovieList)
                        .queryParam("api_key", endpoint.getApiKey())
                        .and().queryParam("session_id",session.getSessionId())
                .when()
                        .post(endpoint.getAddMovieToList(this.getId()))
                .then()
                        .extract()
                        .response()
        );
    }

    public Response clearListRequest(Endpoint endpoint, Session session){
        boolean confirm = true;
        return (
            given().contentType(ContentType.JSON)
                .queryParam("api_key", endpoint.getApiKey())
                .and().queryParam("session_id",session.getSessionId())
                .and().queryParam("confirm", confirm)
            .when()
                .post(endpoint.getClearList(this.getId()))
            .then()
                .extract()
                .response()
        );
    }

    public Response getListDetailsRequest(Endpoint endpoint, Session session){
        return (
            given()
                .queryParam("api_key", endpoint.getApiKey())
            .when()
                .get(endpoint.getListDetails(this.getId()))
            .then()
                .extract()
                .response()
        );
    }

    @Override
    public Response createRequest(Endpoint endpoint, Session session) {
        return null;
    }

    ;
}
