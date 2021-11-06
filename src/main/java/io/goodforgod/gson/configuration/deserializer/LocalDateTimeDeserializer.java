package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    public static final LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();

    private final DateTimeFormatter formatter;

    public LocalDateTimeDeserializer() {
        this(DateTimeFormatters.LOCAL_DATE_TIME_ISO);
    }

    public LocalDateTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(LocalDateTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
