package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalTimeSerializer implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    private final DateTimeFormatter formatter;

    public LocalTimeSerializer() {
        this(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public LocalTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(LocalTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalTime::from).format(src));
    }
}
