package handler.dbReply;

import database.reply.DBRecommendationReply;
import database.reply.DBRegisterReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

public class DBRegisterReplyHandler implements Handler<AsyncResult<Message<DBRegisterReply>>> {
    private final RoutingContext ctx;
    public DBRegisterReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }
    @Override
    public void handle(AsyncResult<Message<DBRegisterReply>> var1) {
        if(var1.succeeded()) {
            ctx.response().end(var1.result().body().result);
        }
        else{

            ctx.response().end("failure");
        }
    }
}
