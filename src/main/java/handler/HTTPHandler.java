package handler;

import database.request.*;
import handler.dbReply.*;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;


public class HTTPHandler {
    public static EventBus eb;
    public static Vertx vertx;
    public static void handle(RoutingContext ctx){
        if(ctx.request().method() == HttpMethod.GET) {
            String tag = ctx.request().headers().get("get");
            switch (tag) {
                case "login" -> HandleLogin(ctx);
                case "menu_picture" -> HandleMenuPicture(ctx);
                case "menu_json" -> HandleMenuJson(ctx);
                case "recommendation" -> HandleRecommendation(ctx);
                default -> HandleFailure(ctx);
            }
        }
        else if(ctx.request().method() == HttpMethod.POST){
            String tag = ctx.request().headers().get("post");
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
        if(account==null||password==null||account.isEmpty()||password.isEmpty()){
            HandleFailure(ctx);
            return;
        }
        eb.request("db.receiver", new DBLoginRequest(account, password), new DBLoginReplyHandler(ctx));
    }
    private static void HandleMenuPicture(RoutingContext ctx){
        eb.request("db.receiver", new DBMenuPictureRequest(Integer.parseInt(ctx.request().headers().get("uuid"))), new DBMenuPictureReplyHandler(ctx,vertx));
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
