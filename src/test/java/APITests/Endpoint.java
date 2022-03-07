package APITests;

public class Endpoint {

    public String getUrl() { return "https://api.themoviedb.org/3"; }

    public String getApiKey() {
        return "0383f0931f7f6bb14fe64530a706ce6c";
    }

    public String getToken() {
        return "/authentication/token/new";
    }

    public String getLogin(){
        return "/authentication/token/validate_with_login";
    }

    public String getSession(){
        return "/authentication/session/new";
    }

    public String getMovieSearch() {
        return "/search/movie";
    }

    public String getMovieObtain(String movieId){
        return "/movie/" + movieId;
    }

    public String getMovieRating(String movieId){
        return "/movie/" + movieId + "/rating";
    }

    public String getListCreate(){
        return "/list";
    }

    public String getListDelete(String listId){ return "/list/" + listId; };

    public String getAddMovieToList(String listId){ return "/list/" + listId + "/add_item"; }

    public String getClearList(String listId){ return "/list/" + listId + "/clear"; }

    public String getListDetails(String listId){ return "/list/" + listId; }
}
