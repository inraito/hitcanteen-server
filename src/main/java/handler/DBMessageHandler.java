package handler;

import database.reply.DBLoginReply;
import database.reply.DBMenuJsonReply;
import database.reply.DBMenuPictureReply;
import database.reply.DBRecommendationReply;
import database.request.DBLoginRequest;
import database.request.DBMenuJsonRequest;
import database.request.DBMenuPictureRequest;
import database.request.DBRecommendationRequest;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import database.DBReply;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import verticle.DBVerticle;

public class DBMessageHandler<DBRequest> implements Handler<Message<DBRequest>> {
    private final DBVerticle dbVerticle;
    public DBMessageHandler(DBVerticle dbVerticle){
        this.dbVerticle = dbVerticle;
    }
    @Override
    public void handle(Message<DBRequest> msg) {
        DBRequest req = msg.body();
        if(req instanceof DBLoginRequest){
            DBLoginRequest request = (DBLoginRequest) req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("SELECT * FROM User WHERE account = " + request.account +" and password = " + request.password)
                            .execute(result -> {
                                if(result.succeeded()){
                                    if(result.result().size()==1){
                                        msg.reply(new DBLoginReply("success"));
                                    }
                                    else{
                                        msg.reply(new DBLoginReply("failure"));
                                    }
                                }
                                else{
                                    msg.fail(0, "Connection Failure");
                                }
                            });
                }
                else{
                    msg.fail(0, "Connection Failure");
                }
            });
        }

        if(req instanceof DBMenuPictureRequest){
            DBMenuPictureRequest request = (DBMenuPictureRequest) req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    if(request.uuid==-1) {
                        ar.result().query("SELECT * FROM Path")
                                .execute(result -> {
                                    if (result.succeeded()) {
                                        msg.reply(new DBMenuPictureReply(result.result().size()));
                                    } else {
                                        msg.fail(0, "Connection Failure");
                                    }
                                });
                    }
                    else{
                        ar.result().query("SELECT * FROM Path WHERE uuid = " + Integer.toString(request.uuid))
                                .execute(result -> {
                                    if (result.succeeded()) {
                                        if(result.result().size()==1){
                                            for(Row row:result.result()){
                                                msg.reply(new DBMenuPictureReply(row.getString("uuid")));
                                                break;
                                            }
                                        }
                                        else{
                                            msg.fail(0, "Invalid Data in Database");
                                        }
                                    } else {
                                        msg.fail(0, "Connection Failure");
                                    }
                                });
                    }
                }
                else{
                    msg.fail(0, "Connection Failure");
                }
            });
        }

        if(req instanceof DBMenuJsonRequest){
            DBMenuJsonRequest request = (DBMenuJsonRequest)req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("SELECT * FROM Data")
                            .execute(result -> {
                                if(result.succeeded()){
                                    if(result.result().size()!=0){
                                        DBMenuJsonReply reply = new DBMenuJsonReply();
                                        for(Row row:result.result()){
                                            reply.put(row.toJson());
                                        }
                                        msg.reply(reply);
                                    }
                                    else{
                                        msg.fail(0, "Invalid Data in Database");
                                    }
                                }
                                else{
                                    msg.fail(0, "Connection Failure");
                                }
                            });
                }
                else{
                    msg.fail(0, "Connection Failure");
                }
            });
        }
        if(req instanceof DBRecommendationRequest){
            DBRecommendationRequest request = (DBRecommendationRequest)req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("SELECT * FROM Data")
                            .execute(result -> {
                                if(result.succeeded()){
                                    if(result.result().size()!=0){
                                        DBRecommendationReply reply = new DBRecommendationReply();
                                        //TODO
                                        msg.reply(reply);
                                    }
                                    else{
                                        msg.fail(0, "Invalid Data in Database");
                                    }
                                }
                                else{
                                    msg.fail(0, "Connection Failure");
                                }
                            });
                }
                else{
                    msg.fail(0, "Connection Failure");
                }
            });
        }
    }
}
