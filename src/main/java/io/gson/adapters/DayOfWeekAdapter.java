package io.gson.adapters;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.gson.adapters.config.GsonConfiguration;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @see DayOfWeek
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class DayOfWeekAdapter implements JsonSerializer<DayOfWeek>, JsonDeserializer<DayOfWeek> {

    private static final Type TYPE = new TypeToken<DayOfWeek>(){}.getType();

    public static Type getType() {
        return TYPE;
    }

    private final DateTimeFormatter formatter;

    public DayOfWeekAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public DayOfWeekAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }


    @Override
    public DayOfWeek deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return formatter.parse(json.getAsString(), DayOfWeek::from);
    }

    @Override
    public JsonElement serialize(DayOfWeek src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }
}
