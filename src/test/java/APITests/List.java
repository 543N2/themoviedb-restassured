package APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class List {

    protected String id;
    protected String name;
    protected String description;
    protected String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) { this.language = language; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract Response deleteRequest(Endpoint endpoint, Session session);

    public Response addMovieToListRequest(Endpoint endpoint, Session session, Movie movie)
    {
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
    };

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
    };

    public abstract Response getListDetailsRequest(Endpoint endpoint, Session session);

    public abstract Response createRequest(Endpoint endpoint, Session session);



    }
