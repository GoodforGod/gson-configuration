package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see ZonedDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {

    public static final ZonedDateTimeDeserializer INSTANCE = new ZonedDateTimeDeserializer();

    private final DateTimeFormatter formatter;

    public ZonedDateTimeDeserializer() {
        this(DateTimeDeserializerFormatters.ISO_ZONED_DATE_TIME);
    }

    public ZonedDateTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            return formatter.parse(json.getAsString()).query(ZonedDateTime::from);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
