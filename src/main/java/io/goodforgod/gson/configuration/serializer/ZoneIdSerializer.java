package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.ZoneId;

/**
 * @see ZoneId
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZoneIdSerializer implements JsonSerializer<ZoneId>, JsonDeserializer<ZoneId> {

    @Override
    public ZoneId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return ZoneId.of(json.getAsString());
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(ZoneId src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
}
