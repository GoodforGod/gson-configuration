package io.gson.adapters;

import com.google.gson.*;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @see Year
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class YearAdapter implements JsonSerializer<Year>, JsonDeserializer<Year> {

    private final DateTimeFormatter formatter;

    public YearAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public YearAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Year deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), Year::from);
    }

    @Override
    public JsonElement serialize(Year src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
