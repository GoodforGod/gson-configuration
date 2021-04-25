package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * @see Month
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class MonthAdapter implements JsonSerializer<Month>, JsonDeserializer<Month> {

    private final DateTimeFormatter formatter;

    public MonthAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public MonthAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Month deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), Month::from);
    }

    @Override
    public JsonElement serialize(Month src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
