package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @see LocalDate
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate> {

    public static final LocalDateSerializer INSTANCE = new LocalDateSerializer();

    private final DateTimeFormatter formatter;

    public LocalDateSerializer() {
        this(DateTimeFormatters.LOCAL_DATE_ISO);
    }

    public LocalDateSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.toFormat(LocalDate::from).format(src));
    }
}
