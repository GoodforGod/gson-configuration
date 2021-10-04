package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    private final DateTimeFormatter formatter;

    public OffsetDateTimeSerializer() {
        this(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public OffsetDateTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(OffsetDateTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(OffsetDateTime::from).format(src));
    }
}
