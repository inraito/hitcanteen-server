package database.reply;

import database.DBReply;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

public class DBRecommendationReply extends DBReply {
    private final ArrayList<JsonObject> arrayList;
    public DBRecommendationReply(){
        this.arrayList = new ArrayList<>();
    }
    public void put(JsonObject jsonObject){
        arrayList.add(jsonObject);
    }
    public ArrayList<JsonObject> getArrayList(){
        return this.arrayList;
    }
}
