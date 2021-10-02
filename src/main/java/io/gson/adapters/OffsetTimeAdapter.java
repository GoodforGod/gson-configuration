package io.gson.adapters;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetTimeAdapter
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
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
        try {
            return formatter.parse(json.getAsString()).query(OffsetTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(OffsetTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(OffsetTime::from).format(src));
    }
}
