package APITests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class MovieById extends Movie {

    public MovieById(String id){
            this.setId(id)
        ;}

    public Response getRequest(Endpoint endpoint){
        return (
                given().queryParam("api_key", endpoint.getApiKey())
                .when().get(endpoint.getMovieObtain(this.getId()))
                .then().extract().response()
        );
    }

    @Override
    public Response searchRequest(Endpoint endpoint) {
        return null;
    }

    @Override
    public Response rateRequest(Endpoint endpoint, Session session) {
        return null;
    }
}
