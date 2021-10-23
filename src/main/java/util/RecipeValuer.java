package util;

import io.vertx.sqlclient.Row;

public class RecipeValuer {
    public static int max = 10;
    public static int min = 1;
    public static boolean value(Row a, Row b){
        try {
            if (
                    ((int) (Math.random()*(max-min)+min))*a.getInteger("protein")*50 - a.getInteger("heat") - a.getDouble("price") >=
                    ((int) (Math.random()*(max-min)+min))*b.getInteger("protein")*50 - b.getInteger("heat") - b.getDouble("price")
            )
                return true;
            else
                return false;
        }
        catch(Exception e){
            ;
        }
        return false;
    }
}
