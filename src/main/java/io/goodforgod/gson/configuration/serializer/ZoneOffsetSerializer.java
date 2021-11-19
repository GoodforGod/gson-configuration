package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.ZoneOffset;

/**
 * @see ZoneOffset
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class ZoneOffsetSerializer implements JsonSerializer<ZoneOffset> {

    public static final ZoneOffsetSerializer INSTANCE = new ZoneOffsetSerializer();

    @Override
    public JsonElement serialize(ZoneOffset src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
}
