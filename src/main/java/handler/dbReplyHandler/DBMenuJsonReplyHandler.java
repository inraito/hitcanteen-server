package handler.dbReplyHandler;

import database.reply.DBMenuJsonReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

public class DBMenuJsonReplyHandler implements Handler<AsyncResult<Message<DBMenuJsonReply>>> {
    private RoutingContext ctx;

    public DBMenuJsonReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBMenuJsonReply>> var1){
        if(var1.succeeded()){

        }

    }
}
