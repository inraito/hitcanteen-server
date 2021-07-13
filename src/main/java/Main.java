import handler.HTTPHandler;
import io.vertx.core.Vertx;
import verticle.DBVerticle;
import verticle.HttpVerticle;

public class Main {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        HTTPHandler.eb = vertx.eventBus();
        HTTPHandler.vertx = vertx;
        vertx.deployVerticle(new HttpVerticle());
        vertx.deployVerticle(new DBVerticle());
    }
}
