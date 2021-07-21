package codec.Request;

import database.request.DBMenuPictureRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBMenuPictureRequestCodec implements MessageCodec<DBMenuPictureRequest, DBMenuPictureRequest> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBMenuPictureRequest dbRequest) {

    }

    @Override//empty implementation
    public DBMenuPictureRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBMenuPictureRequest transform(DBMenuPictureRequest dbRequest) {
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