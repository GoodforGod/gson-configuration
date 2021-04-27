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
        try {
            if (json instanceof JsonPrimitive) {
                if (((JsonPrimitive) json).isNumber()) {
                    return DayOfWeek.of(json.getAsInt());
                }

                final String valueAsJson = json.getAsString();
                for (DayOfWeek dayOfWeek : DAY_OF_WEEKS) {
                    if (dayOfWeek.name().equalsIgnoreCase(valueAsJson)) {
                        return dayOfWeek;
                    }
                }

                return DayOfWeek.of(Integer.parseInt(json.getAsString()));
            }

            throw new JsonParseException("DayOfWeek can not be parsed from: " + json.getAsString());
        } catch (JsonParseException e) {
            throw e;
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(DayOfWeek src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }
}
