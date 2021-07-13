package database.reply;

import database.DBReply;

public class DBLoginReply extends DBReply {

    public final String result;//"success" and "failure"
    public DBLoginReply(String result){
        this.result = result;
    }
}
