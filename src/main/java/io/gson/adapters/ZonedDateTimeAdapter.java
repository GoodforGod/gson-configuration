package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see ZonedDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZonedDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    private final DateTimeFormatter formatter;

    public ZonedDateTimeAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public ZonedDateTimeAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), ZonedDateTime::from);
    }

    @Override
    public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
