package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalTimeSerializer implements JsonSerializer<LocalTime> {

    public static final LocalTimeSerializer INSTANCE = new LocalTimeSerializer();

    private final DateTimeFormatter formatter;

    public LocalTimeSerializer() {
        this(DateTimeFormatters.LOCAL_TIME_ISO);
    }

    public LocalTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalTime::from).format(src));
    }
}
