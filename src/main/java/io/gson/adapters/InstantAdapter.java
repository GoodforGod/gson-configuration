package io.gson.adapters;

import com.google.gson.*;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * @see Instant
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
@Singleton
public class InstantAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {

    private final DateTimeFormatter formatter;

    public InstantAdapter() {
        this(DateTimeFormatter.ISO_INSTANT);
    }

    public InstantAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(Instant::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(Instant::from).format(src));
    }
}
