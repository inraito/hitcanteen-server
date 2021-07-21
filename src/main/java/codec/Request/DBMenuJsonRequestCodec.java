package codec.Request;

import database.request.DBMenuJsonRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBMenuJsonRequestCodec implements MessageCodec<DBMenuJsonRequest, DBMenuJsonRequest> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBMenuJsonRequest dbRequest) {

    }

    @Override//empty implementation
    public DBMenuJsonRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBMenuJsonRequest transform(DBMenuJsonRequest dbRequest) {
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