package io.goodforgod.gson.configuration.serializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @see Year
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class YearSerializer implements JsonSerializer<Year>, JsonDeserializer<Year> {

    private final DateTimeFormatter formatter;

    public YearSerializer() {
        this(DateTimeFormatter.ofPattern("yyyy"));
    }

    public YearSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public Year deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return Year.parse(json.getAsString(), formatter);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Year src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}
