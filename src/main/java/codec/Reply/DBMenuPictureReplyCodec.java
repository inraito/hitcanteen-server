package codec.Reply;

import database.reply.DBMenuPictureReply;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBMenuPictureReplyCodec implements MessageCodec<DBMenuPictureReply, DBMenuPictureReply> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBMenuPictureReply dbRequest) {

    }

    @Override//empty implementation
    public DBMenuPictureReply decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBMenuPictureReply transform(DBMenuPictureReply dbRequest) {
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
