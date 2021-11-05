package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * @see Instant
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class InstantDeserializer implements JsonDeserializer<Instant> {

    public static final InstantDeserializer INSTANCE = new InstantDeserializer();

    private final DateTimeFormatter formatter;

    public InstantDeserializer() {
        this(DateTimeFormatters.INSTANT_ISO);
    }

    public InstantDeserializer(DateTimeFormatter formatter) {
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
}
