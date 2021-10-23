package handler.dbReply;

import database.reply.DBMenuJsonReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;

public class DBMenuJsonReplyHandler implements Handler<AsyncResult<Message<DBMenuJsonReply>>> {
    private RoutingContext ctx;

    public DBMenuJsonReplyHandler(RoutingContext ctx){
        this.ctx = ctx;
    }

    @Override
    public void handle(AsyncResult<Message<DBMenuJsonReply>> var1){
        if(var1.succeeded()){
            ctx.response().setChunked(true);
            ArrayList<JsonObject> arrayList = var1.result().body().getJsonArray();
            ctx.response().write("[");
            for(int i=0;i<arrayList.size();i++){

                if(i<arrayList.size()-1){
                    ctx.response().write(arrayList.get(i).encode() + ",");
                    System.out.println(arrayList.get(i).encode());
                }
                else{
                    ctx.response().end(arrayList.get(i).encode() + "]");
                }
            }
        }
        else{
            ctx.response().end("failure");
        }

    }
}
