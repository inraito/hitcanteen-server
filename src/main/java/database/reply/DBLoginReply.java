package database.reply;

import database.DBReply;

public class DBLoginReply extends DBReply {
    public final String result;
    public DBLoginReply(String result){
        this.result = result;
    }
}
