package codec.Reply;

import database.reply.DBRegisterReply;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBRegisterReplyCodec implements MessageCodec<DBRegisterReply, DBRegisterReply> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBRegisterReply dbRequest) {

    }

    @Override//empty implementation
    public DBRegisterReply decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBRegisterReply transform(DBRegisterReply dbRequest) {
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
