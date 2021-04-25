package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @see ZoneId
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZoneIdAdapter implements JsonSerializer<ZoneId>, JsonDeserializer<ZoneId> {

    private final DateTimeFormatter formatter;

    public ZoneIdAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public ZoneIdAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public ZoneId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ZoneId.of(json.getAsString());
    }

    @Override
    public JsonElement serialize(ZoneId src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
}
