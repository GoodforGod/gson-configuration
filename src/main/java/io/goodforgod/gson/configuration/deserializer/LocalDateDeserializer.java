package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDate
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateDeserializer() {
        this(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(LocalDate::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
