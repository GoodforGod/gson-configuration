package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see ZonedDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime> {

    public static final ZonedDateTimeSerializer INSTANCE = new ZonedDateTimeSerializer();

    private final DateTimeFormatter formatter;

    public ZonedDateTimeSerializer() {
        this(DateTimeFormatters.ZONED_DATE_TIME_ISO);
    }

    public ZonedDateTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(ZonedDateTime::from).format(src));
    }
}
