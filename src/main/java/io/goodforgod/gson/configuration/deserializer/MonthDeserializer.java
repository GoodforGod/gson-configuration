package io.goodforgod.gson.configuration.deserializer;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Month;

/**
 * @see Month
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class MonthDeserializer implements JsonDeserializer<Month> {

    private static final Month[] MONTHS = Month.values();

    @Override
    public Month deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json instanceof JsonPrimitive) {
                if (((JsonPrimitive) json).isNumber()) {
                    return Month.of(json.getAsInt());
                }

                final String monthAsJson = json.getAsString();
                for (Month month : MONTHS) {
                    if (month.name().equalsIgnoreCase(monthAsJson)) {
                        return month;
                    }
                }

                return Month.of(Integer.parseInt(json.getAsString()));
            }

            throw new JsonParseException("Month can not be parsed from: " + json.getAsString());
        } catch (JsonParseException e) {
            throw e;
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

}
