package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime> {

    public static final OffsetDateTimeSerializer INSTANCE = new OffsetDateTimeSerializer();

    private final DateTimeFormatter formatter;

    public OffsetDateTimeSerializer() {
        this(DateTimeFormatters.OFFSET_DATE_TIME_ISO);
    }

    public OffsetDateTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(OffsetDateTime::from).format(src));
    }
}
