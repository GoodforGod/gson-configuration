package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @see Year
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class YearDeserializer implements JsonDeserializer<Year> {

    public static final YearDeserializer INSTANCE = new YearDeserializer();

    private final DateTimeFormatter formatter;

    public YearDeserializer() {
        this(DateTimeFormatters.ISO_YEAR);
    }

    public YearDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Year deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(Year::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
