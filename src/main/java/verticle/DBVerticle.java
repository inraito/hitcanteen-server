package verticle;

import handler.DBMessageHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import database.DBRequest;


public class DBVerticle extends AbstractVerticle {
    public MySQLPool Client;
    public final Vertx vertx;
    public final EventBus eb;

    public DBVerticle(Vertx vertx) {
        this.vertx = vertx;
        eb = vertx.eventBus();
    }

    @Override
    public void start(Promise<Void> startPromise){
        System.err.println("DBVerticle Starting");
        MySQLConnectOptions userConnectOptions = new MySQLConnectOptions()
                .setPort(Lib.dbPort)
                .setHost(Lib.dbHost)
                .setDatabase(Lib.dbDatabase)
                .setUser(Lib.dbUser)
                .setPassword(Lib.dbPassword);
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        System.err.println("Before pool()");
        this.Client = MySQLPool.pool(vertx, userConnectOptions, poolOptions);
        System.err.println("After pool()");
        eb.consumer("db.receiver", new DBMessageHandler<DBRequest>(this));
        System.err.println("DBVerticle Deployed");
    }
}
