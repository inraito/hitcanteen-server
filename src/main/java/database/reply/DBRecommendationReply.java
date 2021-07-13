package database.reply;

import database.DBReply;
import io.vertx.core.json.JsonObject;

public class DBRecommendationReply extends DBReply {
    private final JsonObject jsonObject;
    public DBRecommendationReply(){
        this.jsonObject = new JsonObject();
    }
    public void put(String key, String value){
        jsonObject.put(key, value);
    }
    public JsonObject getJsonObject(){
        return this.jsonObject;
    }
}
