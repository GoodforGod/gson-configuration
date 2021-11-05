package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * @see YearMonth
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class YearMonthDeserializer implements JsonDeserializer<YearMonth> {

    public static final YearMonthDeserializer INSTANCE = new YearMonthDeserializer();

    private final DateTimeFormatter formatter;

    public YearMonthDeserializer() {
        this(DateTimeFormatters.YEAR_MONTH_ISO);
    }

    public YearMonthDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public YearMonth deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return YearMonth.parse(json.getAsString(), formatter);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
