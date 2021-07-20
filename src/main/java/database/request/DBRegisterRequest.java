package database.request;

import database.DBRequest;

public class DBRegisterRequest extends DBRequest {
    public final String account;
    public final String password;
    public DBRegisterRequest(String account, String password){
        this.account = account;
        this.password = password;
    }
}
