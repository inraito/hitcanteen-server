package handler;

import database.DBRequest;
import database.request.*;
import handler.dbReply.*;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;


public class HTTPHandler {
    public static EventBus eb;
    public static Vertx vertx;

    public static void handle(RoutingContext ctx){
        System.err.println("enter HTTPHandler");
        if(ctx.request().method() == HttpMethod.GET) {
            System.err.println("enter HTTPHandler's get");
            String tag = ctx.request().headers().get("get");
            if(tag==null){
                System.err.println("tag == null");
                ctx.response().end("failure");
                return;
            }
            System.err.println("tag not null");
            System.err.println(tag);
            switch (tag) {

                case "login" -> HandleLogin(ctx);
                case "menu_picture" -> HandleMenuPicture(ctx);
                case "menu_json" -> HandleMenuJson(ctx);
                case "recommendation" -> HandleRecommendation(ctx);
                default -> HandleFailure(ctx);
            }
        }
        else if(ctx.request().method() == HttpMethod.POST){
            System.err.println("enter post");
            String tag = ctx.request().headers().get("post");
            System.err.println("tag = " + tag);
            if(tag==null){
                ctx.response().end("failure");
                return;
            }
            switch(tag){
                case "register" -> HandleRegister(ctx);
                default -> HandleFailure(ctx);
            }
        }
        else{
            HandleFailure(ctx);
        }
    }
    private static void HandleLogin(RoutingContext ctx){
        String account = ctx.request().headers().get("account");
        String password = ctx.request().headers().get("password");
        System.err.println(account + " " + password);
        if(account==null||password==null||account.isEmpty()||password.isEmpty()){
            System.err.println("fail for account or password 's emptiness");
            HandleFailure(ctx);
            return;
        }
        eb.request("db.receiver", new DBLoginRequest(account, password), new DBLoginReplyHandler(ctx));
    }
    private static void HandleMenuPicture(RoutingContext ctx){
        //eb.request("db.receiver", new DBMenuPictureRequest(Integer.parseInt(ctx.request().headers().get("uuid"))), new DBMenuPictureReplyHandler(ctx,vertx));
        FileSystem fs = vertx.fileSystem();
        int uuid = Integer.parseInt(ctx.request().headers().get("uuid"));
        fs.open("/picture/" + ctx.request().headers().get("uuid"), new OpenOptions(), ar->{
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
    private static void HandleMenuJson(RoutingContext ctx){
        eb.request("db.receiver", new DBMenuJsonRequest(), new DBMenuJsonReplyHandler(ctx));
    }
    private static void HandleRecommendation(RoutingContext ctx){
        eb.request("db.receiver", new DBRecommendationRequest(), new DBRecommendationReplyHandler(ctx));
    }
    private static void HandleRegister(RoutingContext ctx){
        String account = ctx.request().headers().get("account");
        String password = ctx.request().headers().get("password");
        System.err.println("enter HandleRegister");
        System.err.println(account + " " + password);
        if(account==null||password==null||account.isEmpty()||password.isEmpty()){
            HandleFailure(ctx);
            return;
        }
        eb.request("db.receiver", new DBRegisterRequest(account, password), new DBRegisterReplyHandler(ctx));
    }
    private static void HandleFailure(RoutingContext ctx){
        ctx.response().end("failure");
    }
}
