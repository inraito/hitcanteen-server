package codec.Request;

import database.request.DBRegisterRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBRegisterRequestCodec implements MessageCodec<DBRegisterRequest, DBRegisterRequest> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBRegisterRequest dbRequest) {

    }

    @Override//empty implementation
    public DBRegisterRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBRegisterRequest transform(DBRegisterRequest dbRequest) {
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
