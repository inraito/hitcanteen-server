package handler;

import database.request.DBLoginRequest;
import database.request.DBMenuJsonRequest;
import database.request.DBMenuPictureRequest;
import database.request.DBRecommendationRequest;
import handler.dbReplyHandler.DBLoginReplyHandler;
import handler.dbReplyHandler.DBMenuJsonReplyHandler;
import handler.dbReplyHandler.DBMenuPictureReplyHandler;
import handler.dbReplyHandler.DBRecommendationReplyHandler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;


public class HTTPHandler {
    public static EventBus eb;
    public static void handle(RoutingContext ctx){
        String tag = ctx.get("get");
        switch(tag){
            case "login":
                HandleLogin(ctx);
                break;
            case "menu_picture":
                HandleMenuPicture(ctx);
                break;
            case "menu_json":
                HandleMenuJson(ctx);
                break;
            case "recommendation":
                HandleRecommendation(ctx);
                break;
        }
    }
    private static void HandleLogin(RoutingContext ctx){
        String account = ctx.get("account");
        String password = ctx.get("password");
        eb.request("db.receiver", new DBLoginRequest(account, password), new DBLoginReplyHandler(ctx));
    }
    private static void HandleMenuPicture(RoutingContext ctx){
        eb.request("db.receiver", new DBMenuPictureRequest(), new DBMenuPictureReplyHandler(ctx));
    }
    private static void HandleMenuJson(RoutingContext ctx){
        eb.request("db.receiver", new DBMenuJsonRequest(), new DBMenuJsonReplyHandler(ctx));
    }
    private static void HandleRecommendation(RoutingContext ctx){
        eb.request("db.receiver", new DBRecommendationRequest(), new DBRecommendationReplyHandler(ctx));
    }
}
