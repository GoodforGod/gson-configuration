package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * @see YearMonth
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class YearMonthAdapter implements JsonSerializer<YearMonth>, JsonDeserializer<YearMonth> {

    private final DateTimeFormatter formatter;

    public YearMonthAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public YearMonthAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public YearMonth deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), YearMonth::from);
    }

    @Override
    public JsonElement serialize(YearMonth src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
