package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * @see MonthDay
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class MonthDayAdapter implements JsonSerializer<MonthDay>, JsonDeserializer<MonthDay> {

    private final DateTimeFormatter formatter;

    public MonthDayAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public MonthDayAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public MonthDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), MonthDay::from);
    }

    @Override
    public JsonElement serialize(MonthDay src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
