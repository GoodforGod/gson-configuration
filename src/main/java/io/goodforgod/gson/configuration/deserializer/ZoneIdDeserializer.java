package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.ZoneId;

/**
 * @see ZoneId
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZoneIdDeserializer implements JsonDeserializer<ZoneId> {

    public static final ZoneIdDeserializer INSTANCE = new ZoneIdDeserializer();

    @Override
    public ZoneId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return ZoneId.of(json.getAsString());
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
