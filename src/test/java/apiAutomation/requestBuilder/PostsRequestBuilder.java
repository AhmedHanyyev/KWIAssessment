package apiAutomation.requestBuilder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//POJO bean class for api elements
public class PostsRequestBuilder {
    @JsonPropertyOrder({"userId", "id","title", "body"})
    private int userId;
    private int Id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;


    }
}
