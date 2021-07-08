package verticle;

import handler.DBMessageHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import database.DBRequest;


public class DBVerticle extends AbstractVerticle {
    MySQLPool client = null;
    EventBus eb = vertx.eventBus();
    @Override
    public void start(Promise<Void> startPromise){
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(Lib.dbPort)
                .setHost(Lib.dbHost)
                .setDatabase(Lib.dbDatabase)
                .setUser(Lib.dbUser)
                .setPassword(Lib.dbPassword);
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        this.client = MySQLPool.pool(connectOptions, poolOptions);
        eb.consumer("db.receiver", new DBMessageHandler<DBRequest>(this));
    }
}
