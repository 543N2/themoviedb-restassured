package APITests;

import io.restassured.response.Response;

public abstract class Movie {

    protected String name;
    protected String id;
    protected double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public abstract Response getRequest(Endpoint endpoint);

    public abstract Response rateRequest(Endpoint endpoint, Session session);

    public abstract Response searchRequest(Endpoint endpoint);



    }
