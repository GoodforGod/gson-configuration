package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.ZoneId;

/**
 * @see ZoneId
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZoneIdSerializer implements JsonSerializer<ZoneId> {

    public static final ZoneIdSerializer INSTANCE = new ZoneIdSerializer();

    @Override
    public JsonElement serialize(ZoneId src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
}
