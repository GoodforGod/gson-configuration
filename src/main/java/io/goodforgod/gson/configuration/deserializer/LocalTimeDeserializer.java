package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {

    public static final LocalTimeDeserializer INSTANCE = new LocalTimeDeserializer();

    private final DateTimeFormatter formatter;

    public LocalTimeDeserializer() {
        this(DateTimeFormatters.LOCAL_TIME_ISO);
    }

    public LocalTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(LocalTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
