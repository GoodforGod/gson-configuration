package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

    public static final LocalDateTimeSerializer INSTANCE = new LocalDateTimeSerializer();

    private final DateTimeFormatter formatter;

    public LocalDateTimeSerializer() {
        this(DateTimeFormatters.LOCAL_DATE_TIME_ISO);
    }

    public LocalDateTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalDateTime::from).format(src));
    }
}
