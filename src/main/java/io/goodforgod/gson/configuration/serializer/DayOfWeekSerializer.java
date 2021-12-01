package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.DayOfWeek;

/**
 * @see DayOfWeek
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class DayOfWeekSerializer implements JsonSerializer<DayOfWeek> {

  public static final DayOfWeekSerializer INSTANCE = new DayOfWeekSerializer();

  @Override
  public JsonElement serialize(DayOfWeek src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.name());
  }
}
