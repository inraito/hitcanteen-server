package codec.Request;

import database.request.DBRecommendationRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DBRecommendationRequestCodec implements MessageCodec<DBRecommendationRequest, DBRecommendationRequest> {
    @Override//empty implementation
    public void encodeToWire(Buffer buffer, DBRecommendationRequest dbRequest) {

    }

    @Override//empty implementation
    public DBRecommendationRequest decodeFromWire(int i, Buffer buffer) {
        return null;
    }

    @Override
    public DBRecommendationRequest transform(DBRecommendationRequest dbRequest) {
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