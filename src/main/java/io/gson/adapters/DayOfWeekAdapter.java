package io.gson.adapters;

import com.google.gson.*;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.time.DayOfWeek;

/**
 * @see DayOfWeek
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
@Singleton
public class DayOfWeekAdapter implements JsonSerializer<DayOfWeek>, JsonDeserializer<DayOfWeek> {

    private static final DayOfWeek[] DAY_OF_WEEKS = DayOfWeek.values();

    @Override
    public DayOfWeek deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json instanceof JsonPrimitive) {
            return DayOfWeek.of(json.getAsInt());
        } else {
            final String valueAsJson = json.getAsString();
            for (DayOfWeek dayOfWeek : DAY_OF_WEEKS) {
                if (dayOfWeek.name().equalsIgnoreCase(valueAsJson)) {
                    return dayOfWeek;
                }
            }

            throw new JsonParseException("Month can not be parsed from: " + valueAsJson);
        }
    }

    @Override
    public JsonElement serialize(DayOfWeek src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }
}
