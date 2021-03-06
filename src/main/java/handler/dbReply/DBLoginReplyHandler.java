package handler.dbReply;

import database.reply.DBLoginReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

public class DBLoginReplyHandler implements Handler<AsyncResult<Message<DBLoginReply>>> {
    private RoutingContext ctx;

    public DBLoginReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBLoginReply>> var1){
        if(var1.succeeded()){
            ctx.response().end(var1.result().body().result);
        }
        else{
            System.err.println("databaseFailure");
            ctx.response().end("failure");
        }
    }

}
