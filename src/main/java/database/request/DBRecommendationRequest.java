package database.request;

import database.DBRequest;

import java.util.ArrayList;

public class DBRecommendationRequest extends DBRequest {
    private ArrayList<String> tags;
    public DBRecommendationRequest(){
        this.tags = new ArrayList<>();
    }
    public void put(String s){
        tags.add(s);
    }
    public String getTag(){
        return tags.get(0);
    }
}
