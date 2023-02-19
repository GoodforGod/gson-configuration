package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetTimeSerializer
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetTimeSerializer implements JsonSerializer<OffsetTime> {

    public static final OffsetTimeSerializer INSTANCE = new OffsetTimeSerializer();

    private final DateTimeFormatter formatter;

    public OffsetTimeSerializer() {
        this(DateTimeSerializerFormatters.ISO_OFFSET_TIME);
    }

    public OffsetTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(OffsetTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
