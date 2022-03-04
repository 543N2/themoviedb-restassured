package APITests;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Session {

    private static Session session;
    private String baseURL;
    private String api_key;
    private String request_token;
    private String session_id;

    private Session(){
        this.baseURL = "https://api.themoviedb.org/3";
        this.api_key = "0383f0931f7f6bb14fe64530a706ce6c";
    }

    public static Session createSession () {
        if (session == null){
            session = new Session();
        }
        else
            System.out.println("Only one session is available");
        return session;
    }

    public String getUrl() {
        return baseURL;
    }

    public void setUrl(String url) {
        this.baseURL = url;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
