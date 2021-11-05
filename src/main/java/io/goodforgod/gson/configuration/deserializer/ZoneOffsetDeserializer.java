package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.ZoneOffset;

/**
 * @see ZoneOffset
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class ZoneOffsetDeserializer implements JsonDeserializer<ZoneOffset> {

    public static final ZoneOffsetDeserializer INSTANCE = new ZoneOffsetDeserializer();

    @Override
    public ZoneOffset deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return ZoneOffset.of(json.getAsString());
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
