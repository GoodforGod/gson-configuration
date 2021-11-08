package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * @see MonthDay
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class MonthDayDeserializer implements JsonDeserializer<MonthDay> {

    public static final MonthDayDeserializer INSTANCE = new MonthDayDeserializer();

    private final DateTimeFormatter formatter;

    public MonthDayDeserializer() {
        this(DateTimeFormatters.ISO_MONTH_DAY);
    }

    public MonthDayDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public MonthDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json instanceof JsonPrimitive) {
                final String monthAsJson = json.getAsString();
                return MonthDay.parse(monthAsJson, formatter);
            }
        } catch (Exception e) {
            throw new JsonParseException(e);
        }

        throw new JsonParseException("MonthDay can not be parsed from: " + json.getAsString());
    }
}
