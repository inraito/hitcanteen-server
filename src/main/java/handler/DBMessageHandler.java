package handler;

import database.reply.DBLoginReply;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import database.DBReply;
import verticle.DBVerticle;

public class DBMessageHandler<DBRequest> implements Handler<Message<DBRequest>> {
    private DBVerticle dbVerticle;
    public DBMessageHandler(DBVerticle dbVerticle){
        this.dbVerticle = dbVerticle;
    }
    @Override
    public void handle(Message<DBRequest> msg) {
        DBRequest req = msg.body();
        DBReply reply = null;
        if(req instanceof DBLoginReply){
            //TODO
        }



        msg.reply(reply);
    }
}
