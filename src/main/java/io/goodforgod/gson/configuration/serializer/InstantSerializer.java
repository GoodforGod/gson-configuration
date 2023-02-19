package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * @see Instant
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class InstantSerializer implements JsonSerializer<Instant> {

    public static final InstantSerializer INSTANCE = new InstantSerializer();

    private final DateTimeFormatter formatter;

    public InstantSerializer() {
        this(DateTimeSerializerFormatters.ISO_INSTANT);
    }

    public InstantSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
