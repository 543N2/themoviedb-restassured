package APITests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class MovieByName extends Movie {

    public MovieByName (String name){ this.setName(name);}

    @Override
    public Response getRequest(Endpoint endpoint) {
        return null;
    }

    @Override
    public Response rateRequest(Endpoint endpoint, Session session) {
        return null;
    }

    public Response searchRequest(Endpoint endpoint){
        return (
            given()
                .queryParam("api_key", endpoint.getApiKey())
                .queryParam("query", getName())
            .when()
                    .get(endpoint.getMovieSearch())
            .then()
                    .extract()
                    .response()
        );
    }


}
