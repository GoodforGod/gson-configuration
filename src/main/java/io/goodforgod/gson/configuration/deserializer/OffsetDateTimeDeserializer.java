package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {

    public static final OffsetDateTimeDeserializer INSTANCE = new OffsetDateTimeDeserializer();

    private final DateTimeFormatter formatter;

    public OffsetDateTimeDeserializer() {
        this(DateTimeFormatters.ISO_OFFSET_DATE_TIME);
    }

    public OffsetDateTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(OffsetDateTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
