import codec.Reply.*;
import codec.Request.*;
import handler.HTTPHandler;
import io.vertx.core.Vertx;
import verticle.DBVerticle;
import verticle.HttpVerticle;

public class Main {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        HTTPHandler.eb = vertx.eventBus();
        HTTPHandler.vertx = vertx;

        vertx.eventBus().registerDefaultCodec(database.request.DBLoginRequest.class, new DBLoginRequestCodec());
        vertx.eventBus().registerDefaultCodec(database.request.DBMenuJsonRequest.class, new DBMenuJsonRequestCodec());
        vertx.eventBus().registerDefaultCodec(database.request.DBMenuPictureRequest.class, new DBMenuPictureRequestCodec());
        vertx.eventBus().registerDefaultCodec(database.request.DBRecommendationRequest.class, new DBRecommendationRequestCodec());
        vertx.eventBus().registerDefaultCodec(database.request.DBRegisterRequest.class, new DBRegisterRequestCodec());
        vertx.eventBus().registerDefaultCodec(database.reply.DBLoginReply.class, new DBLoginReplyCodec());
        vertx.eventBus().registerDefaultCodec(database.reply.DBMenuJsonReply.class, new DBMenuJsonReplyCodec());
        vertx.eventBus().registerDefaultCodec(database.reply.DBMenuPictureReply.class, new DBMenuPictureReplyCodec());
        vertx.eventBus().registerDefaultCodec(database.reply.DBRecommendationReply.class, new DBRecommendationReplyCodec());
        vertx.eventBus().registerDefaultCodec(database.reply.DBRegisterReply.class, new DBRegisterReplyCodec());

        vertx.deployVerticle(new HttpVerticle());
        vertx.deployVerticle(new DBVerticle(vertx));
    }
}
