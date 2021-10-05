package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetTimeDeserializer
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetTimeDeserializer implements JsonDeserializer<OffsetTime> {

    private final DateTimeFormatter formatter;

    public OffsetTimeDeserializer() {
        this(DateTimeFormatter.ISO_OFFSET_TIME);
    }

    public OffsetTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public OffsetTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(OffsetTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

}
