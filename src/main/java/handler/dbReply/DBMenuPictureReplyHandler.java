package handler.dbReply;

import database.reply.DBMenuPictureReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.ext.web.RoutingContext;


public class DBMenuPictureReplyHandler implements Handler<AsyncResult<Message<DBMenuPictureReply>>> {
    private final RoutingContext ctx;
    private final Vertx vertx;
    public DBMenuPictureReplyHandler(RoutingContext ctx, Vertx vertx){
        this.ctx = ctx;
        this.vertx = vertx;
    }

    @Override
    public void handle(AsyncResult<Message<DBMenuPictureReply>> var1){
        if(var1.succeeded()){
            FileSystem fs = vertx.fileSystem();
            int uuid = Integer.parseInt(ctx.request().headers().get("uuid"));
            fs.open(var1.result().body().path, new OpenOptions(), ar->{
                if(ar.succeeded()){
                    ar.result().pipeTo(ctx.response(), result->{
                        if(result.succeeded()){
                            ctx.response().end();
                        }
                        else{
                            ctx.response().end("failure");
                        }
                    });
                }
                else{
                    ctx.response().end("failure");
                }
            });
        }
        else{
            ctx.response().end("failure");
        }

    }
}
