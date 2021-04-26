package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetTimeAdapter
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
@Singleton
public class OffsetTimeAdapter implements JsonSerializer<OffsetTime>, JsonDeserializer<OffsetTime> {

    private final DateTimeFormatter formatter;

    public OffsetTimeAdapter() {
        this(DateTimeFormatter.ISO_OFFSET_TIME);
    }

    public OffsetTimeAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public OffsetTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), OffsetTime::from);
    }

    @Override
    public JsonElement serialize(OffsetTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
