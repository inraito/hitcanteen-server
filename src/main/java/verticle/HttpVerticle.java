package verticle;


import handler.HTTPHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;


public class HttpVerticle extends AbstractVerticle {
    HttpServer server;
    @Override
    public void start(Promise<Void> startPromise){
        Router router = Router.router(vertx);
        router.route().handler(HTTPHandler::handle);
        server = vertx.createHttpServer();
        server.requestHandler(router);
        server.listen(8080);

    }
}
