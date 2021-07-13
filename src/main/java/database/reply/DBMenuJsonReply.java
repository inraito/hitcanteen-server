package database.reply;

import database.DBReply;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

public class DBMenuJsonReply extends DBReply {
    private final ArrayList<JsonObject> arrayList;
    public DBMenuJsonReply(){
        this.arrayList = new ArrayList<>();
    }
    public void put(JsonObject jsonObject){
        arrayList.add(jsonObject);
    }
    public ArrayList<JsonObject> getJsonArray(){
        return this.arrayList;
    }
}
