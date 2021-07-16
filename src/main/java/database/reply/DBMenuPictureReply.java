package database.reply;

import database.DBReply;

import java.util.ArrayList;

public class DBMenuPictureReply extends DBReply {
    public String path;
    public int number;
    private DBMenuPictureReply(String path, int number){
        this.path =path;
        this.number = number;
    }
    public DBMenuPictureReply(String path){
        new DBMenuPictureReply(path, -1);
    }
    public DBMenuPictureReply(int number){
        new DBMenuPictureReply(null, number);
    }
}
