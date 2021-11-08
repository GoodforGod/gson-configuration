package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * @see MonthDay
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class MonthDaySerializer implements JsonSerializer<MonthDay> {

    public static final MonthDaySerializer INSTANCE = new MonthDaySerializer();

    private final DateTimeFormatter formatter;

    public MonthDaySerializer() {
        this(DateTimeFormatters.ISO_MONTH_DAY);
    }

    public MonthDaySerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(MonthDay src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }
}
