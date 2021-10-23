package handler;

import database.reply.*;
import database.request.*;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.sqlclient.Row;
import verticle.DBVerticle;
import static util.RecipeValuer.*;

public class DBMessageHandler<DBRequest> implements Handler<Message<DBRequest>> {
    private final DBVerticle dbVerticle;
    public DBMessageHandler(DBVerticle dbVerticle){
        this.dbVerticle = dbVerticle;
    }
    @Override
    public void handle(Message<DBRequest> msg) {
        DBRequest req = msg.body();
        System.err.println("enter DBMessageHandler");
        if(req instanceof DBLoginRequest){
            DBLoginRequest request = (DBLoginRequest) req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("SELECT * FROM User WHERE account = '" + request.account +"' and password = '" + request.password + "'")
                            .execute(result -> {
                                if(result.succeeded()){
                                    if(result.result().size()==1){
                                        System.err.println("login successfully");
                                        msg.reply(new DBLoginReply("success"));
                                    }
                                    else{
                                        System.err.println("true fail");
                                        msg.reply(new DBLoginReply("failure"));
                                    }
                                }
                                else{
                                    System.err.println("fail at execute");
                                    msg.fail(0, "Connection Failure");
                                }
                            });
                }
                else{
                    System.err.println("fail at getConnection");
                    msg.fail(0, "Connection Failure");
                }
            });
        }

        if(req instanceof DBMenuPictureRequest){
            DBMenuPictureRequest request = (DBMenuPictureRequest) req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("SELECT * FROM Path WHERE uuid = " + Integer.toString(request.uuid))
                            .execute(result -> {
                                if (result.succeeded()) {
                                    if(result.result().size()==1){
                                        for(Row row:result.result()){
                                            msg.reply(new DBMenuPictureReply(row.getString("path")));
                                            break;
                                        }
                                    }
                                    else{
                                        msg.fail(0, "Invalid Data in Database");
                                    }
                                } else {
                                    msg.fail(0, "Failure");
                                }
                            });
                }
                else{

                    msg.fail(0, "Failure");
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
                                    System.err.println("execute success");
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
                                    System.err.println("execute fail");
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
                                        Row result_stapleFood = null;
                                        Row result_drink = null;
                                        Row result_refreshments = null;
                                        for(Row r:result.result()){
                                            System.out.println(r.toJson());
                                            switch(r.getString("category")){
                                                case "refreshments":
                                                    if(result_refreshments==null){
                                                        result_refreshments = r;
                                                        break;
                                                    }
                                                    else if(!value(result_refreshments, r))
                                                        result_refreshments = r;
                                                    break;
                                                case "staple_food":
                                                    if(result_stapleFood==null){
                                                        result_stapleFood = r;
                                                        break;
                                                    }
                                                    else if(!value(result_stapleFood, r))
                                                        result_stapleFood = r;
                                                    break;
                                                case "drink":
                                                    System.out.println(r.toJson());
                                                    if(result_drink==null){
                                                        System.out.println("result drink changed");
                                                        result_drink = r;
                                                        break;
                                                    }
                                                    else if(!value(result_drink, r))
                                                        result_drink = r;
                                            }

                                        }
                                        System.out.println(result_drink.toJson());
                                        System.out.println(result_refreshments.toJson());
                                        System.out.println(result_stapleFood.toJson());
                                        if(!(request.getTag().equals("noStaple")))
                                            reply.put(result_stapleFood.toJson());
                                        if(!(request.getTag().equals("noDrink")))
                                            reply.put(result_drink.toJson());
                                        if(!(request.getTag().equals("noRefreshments")))
                                            reply.put(result_refreshments.toJson());


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
        if(req instanceof DBRegisterRequest){
            System.err.println("enter DBMessageHandler");
            DBRegisterRequest request = (DBRegisterRequest)req;
            dbVerticle.Client.getConnection( ar -> {
                if(ar.succeeded()){
                    ar.result().query("INSERT INTO user (`account`, `password` ) VALUES('" + request.account + " ', '" + request.password + "') ")
                            .execute(result -> {
                                if(result.succeeded()){
                                    System.err.println("success in execute");

                                    msg.reply(new DBRegisterReply("success"));
                                }
                                else{
                                    System.err.println("fail in execute");
                                    msg.reply(new DBRegisterReply("failure"));
                                }
                            });
                }
                else{
                    System.err.println("fail at getConnection");
                    msg.fail(0, "Failure");
                }
            });
        }
    }
}
