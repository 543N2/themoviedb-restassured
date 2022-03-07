package APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITests {

    private static User user = new User();
    private static Endpoint endpoint = new Endpoint();
    private static Session session = new Session();

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = endpoint.getUrl();
        session.requestToken(endpoint);
        session.loginWithUserAndPassword(user, endpoint);
        session.requestSessionId(endpoint);
        System.out.println("Session Id: " + session.getSessionId());
    }

    @Test
    public void searchMovie(){
        Movie movie = new MovieByName("se7en");
        Response response = movie.searchRequest(endpoint);
        Assertions.assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getMovie() {
        Movie movie = new MovieById("106");
        Response response = movie.getRequest(endpoint);
        Assertions.assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void rateMovie() {
        Movie movie = new MovieByIdRating("24", 9.5);
        Response response = movie.rateRequest(endpoint, session);
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }


    @Test
    public void createList(){
        List list = new ListByAll("My List","I have created a list!", "en");
        Response response = list.createRequest(endpoint, session);
        System.out.println("List id: " + list.getId());
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }

    @Test
    public void getListDetails(){
        List list = new ListById("8194301");
        Response response = list.getListDetailsRequest(endpoint, session);
        System.out.println("List id: " + list.getId());
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void addMovieToList(){
        List list = new ListById("8194301");
        Movie movie = new MovieById("78");
        Response response = list.addMovieToListRequest(endpoint, session, movie);
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }

    @Test
    public void clearList(){
        List list = new ListById("8194301");
        Response response = list.clearListRequest(endpoint, session);
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(201,response.getStatusCode());
    }

    @Test
    public void deleteList(){
        List listById = new ListById("8194302");
        Response response = listById.deleteRequest(endpoint, session);
        System.out.println("List id: " + listById.getId());
        System.out.println(response.prettyPrint());
        Assertions.assertEquals(500,response.getStatusCode());
    }

    @Test
    public void createAndPopulateList(){
        List list = new ListByAll("E2E List 1","valid description? Please","en");
        Response createList = list.createRequest(endpoint,session);
        for(int i=100; i<=140; i++){
            Movie movie1 = new MovieById(Integer.toString(i));
            Response addedMovie = list.addMovieToListRequest(endpoint,session,movie1);
        }
    }

    @Test
    public void createPopulateAnClearList(){
        List list = new ListByAll("listaa X","another desc yes","en");
        Response createList = list.createRequest(endpoint,session);
        for(int i=1; i<=10; i++){
            Movie movie1 = new MovieById(Integer.toString(i));
            Response addedMovie = list.addMovieToListRequest(endpoint,session,movie1);
        }
        Response clearedList = list.clearListRequest(endpoint, session);
        Assertions.assertEquals(201,clearedList.getStatusCode());
    }

}
