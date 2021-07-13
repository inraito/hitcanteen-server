package database.reply;

import database.DBReply;

import java.util.ArrayList;

public class DBMenuPictureReply extends DBReply {
    private final ArrayList<String> path;
    public DBMenuPictureReply(){
        this.path = new ArrayList<>();
    }

    public void put(String p){
        this.path.add(p);
    }
    public ArrayList<String> getList(){
        return this.path;
    }

}
