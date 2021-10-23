package handler.dbReply;

import database.reply.DBRecommendationReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class DBRecommendationReplyHandler implements Handler<AsyncResult<Message<DBRecommendationReply>>> {
    private final RoutingContext ctx;

    public DBRecommendationReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBRecommendationReply>> var1){
        if(var1.succeeded()){
            ctx.response().setChunked(true);
            int i = 1;
            int size = var1.result().body().getArrayList().size();
            ctx.response().write("[");
            for(JsonObject jsonObject:var1.result().body().getArrayList()){
                ctx.response().write(jsonObject.encode());
                if(i!=size)
                    ctx.response().write(",");
                i++;
            }
            ctx.response().end("]");

        }
        else{
            ctx.response().end("failure");
        }

    }
}
