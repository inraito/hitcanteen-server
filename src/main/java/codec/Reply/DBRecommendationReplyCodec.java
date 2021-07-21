package codec.Reply;

import database.reply.DBRecommendationReply;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBRecommendationReplyCodec implements MessageCodec<DBRecommendationReply, DBRecommendationReply> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBRecommendationReply dbRequest) {

    }

    @Override//empty implementation
    public DBRecommendationReply decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBRecommendationReply transform(DBRecommendationReply dbRequest) {
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
