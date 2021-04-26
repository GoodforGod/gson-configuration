package io.gson.adapters;

import com.google.gson.*;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDate
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
@Singleton
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateAdapter() {
        this(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateAdapter(DateTimeFormatter formatter) {
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

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalDate::from).format(src));
    }
}
