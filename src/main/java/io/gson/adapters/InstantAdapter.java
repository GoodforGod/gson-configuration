package io.gson.adapters;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.gson.adapters.config.GsonConfiguration;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * @see Instant
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class InstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

    private static final Type TYPE = new TypeToken<Instant>(){}.getType();

    public static Type getType() {
        return TYPE;
    }

    private final DateTimeFormatter formatter;

    public InstantAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public InstantAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), Instant::from);
    }

    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
