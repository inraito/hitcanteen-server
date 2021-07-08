package handler.dbReplyHandler;

import database.reply.DBRecommendationReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

public class DBRecommendationReplyHandler implements Handler<AsyncResult<Message<DBRecommendationReply>>> {
    private RoutingContext ctx;

    public DBRecommendationReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBRecommendationReply>> var1){
        if(var1.succeeded()){

        }

    }
}
