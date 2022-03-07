package APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class MovieByIdRating extends Movie {

    public MovieByIdRating(String id, double rating){
            this.setId(id);
            this.setRating(rating);
        }

    @Override
    public Response getRequest(Endpoint endpoint) {
        return null;
    }

    public Response rateRequest(Endpoint endpoint, Session session){

        Map<String,Object> bodyRateMovie = new HashMap<>();
        bodyRateMovie.put("value", this.getRating());

        return (
            given()
                    .contentType(ContentType.JSON)
                    .queryParam("api_key", endpoint.getApiKey())
                    .and().queryParam("session_id", session.getSessionId())
                    .body(bodyRateMovie)
            .when()
                    .post(endpoint.getMovieRating(getId()))
            .then()
                    .extract().response()
        );
    }

    @Override
    public Response searchRequest(Endpoint endpoint) {
        return null;
    }
}
