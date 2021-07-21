package codec.Reply;

import database.reply.DBMenuJsonReply;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBMenuJsonReplyCodec implements MessageCodec<DBMenuJsonReply, DBMenuJsonReply> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBMenuJsonReply dbRequest) {

    }

    @Override//empty implementation
    public DBMenuJsonReply decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBMenuJsonReply transform(DBMenuJsonReply dbRequest) {
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
