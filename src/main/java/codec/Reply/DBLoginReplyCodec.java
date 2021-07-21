package codec.Reply;

import database.reply.DBLoginReply;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBLoginReplyCodec implements MessageCodec<DBLoginReply, DBLoginReply> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBLoginReply dbRequest) {

    }

    @Override//empty implementation
    public DBLoginReply decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBLoginReply transform(DBLoginReply dbRequest) {
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
