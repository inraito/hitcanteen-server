package handler.dbReply;

import database.reply.DBMenuPictureReply;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;

class FileOpenCompletedHandler implements  Handler<AsyncResult<AsyncFile>>{
    private final int count;
    private final ArrayList<String> arrayList;
    private final RoutingContext ctx;
    private final FileSystem fs;
    public FileOpenCompletedHandler(int i, ArrayList<String> arrayList, RoutingContext ctx, FileSystem fs){
        this.count = i;
        this.arrayList = arrayList;
        this.ctx = ctx;
        this.fs = fs;
    }
    @Override
    public void handle(AsyncResult<AsyncFile> result) {
        if (result.succeeded()) {
            result.result().pipeTo(ctx.response(), ar -> {
                if(ar.succeeded()){
                    if(count<arrayList.size()-1)
                        fs.open(arrayList.get(count+1), new OpenOptions(), new FileOpenCompletedHandler(count+1,arrayList, ctx, fs));
                    else
                        ctx.response().end();
                }
                else{
                        ctx.response().end();
                }
            });
        } else {
                ctx.response().end();
        }
    }
}

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
            ArrayList<String> arrayList = var1.result().body().getList();//get paths
            fs.open(arrayList.get(0), new OpenOptions(), new FileOpenCompletedHandler(0,arrayList, ctx, fs));
        }
        else{
            ctx.response().end("failure");
        }

    }
}
