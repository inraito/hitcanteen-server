package handler;

import database.request.DBLoginRequest;
import database.request.DBMenuJsonRequest;
import database.request.DBMenuPictureRequest;
import database.request.DBRecommendationRequest;
import handler.dbReply.DBLoginReplyHandler;
import handler.dbReply.DBMenuJsonReplyHandler;
import handler.dbReply.DBMenuPictureReplyHandler;
import handler.dbReply.DBRecommendationReplyHandler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;


public class HTTPHandler {
    public static EventBus eb;
    public static Vertx vertx;
    public static void handle(RoutingContext ctx){
        String tag = ctx.request().headers().get("get");
        switch (tag) {
            case "login" -> HandleLogin(ctx);
            case "menu_picture" -> HandleMenuPicture(ctx);
            case "menu_json" -> HandleMenuJson(ctx);
            case "recommendation" -> HandleRecommendation(ctx);
            default -> ctx.response().end("failure");
        }
    }
    private static void HandleLogin(RoutingContext ctx){
        String account = ctx.request().headers().get("account");
        String password = ctx.request().headers().get("password");
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
}
