package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDate
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateSerializer() {
        this(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalDate::from).format(src));
    }
}
