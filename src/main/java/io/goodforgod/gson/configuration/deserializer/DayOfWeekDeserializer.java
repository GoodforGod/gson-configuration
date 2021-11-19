package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.DayOfWeek;

/**
 * @see DayOfWeek
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class DayOfWeekDeserializer implements JsonDeserializer<DayOfWeek> {

    public static final DayOfWeekDeserializer INSTANCE = new DayOfWeekDeserializer();

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
        } catch (Exception e) {
            throw new JsonParseException(e);
        }

        throw new JsonParseException("DayOfWeek can not be parsed from: " + json.getAsString());
    }
}
