package codec.Request;

import database.request.DBLoginRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBLoginRequestCodec implements MessageCodec<DBLoginRequest, DBLoginRequest> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBLoginRequest dbRequest) {

    }

    @Override//empty implementation
    public DBLoginRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBLoginRequest transform(DBLoginRequest dbRequest) {
        return dbRequest;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
