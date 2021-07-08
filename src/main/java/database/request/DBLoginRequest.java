package database.request;

import database.DBRequest;

public class DBLoginRequest extends DBRequest {
    public final String account;
    public final String password;
    public DBLoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
