package handler.dbReplyHandler;

import database.reply.DBMenuPictureReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

public class DBMenuPictureReplyHandler implements Handler<AsyncResult<Message<DBMenuPictureReply>>> {
    private RoutingContext ctx;

    public DBMenuPictureReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBMenuPictureReply>> var1){
        if(var1.succeeded()){

        }

    }
}
