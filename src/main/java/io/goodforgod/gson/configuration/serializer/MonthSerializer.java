package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.Month;

/**
 * @see Month
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class MonthSerializer implements JsonSerializer<Month> {

  public static final MonthSerializer INSTANCE = new MonthSerializer();

  @Override
  public JsonElement serialize(Month src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.name());
  }
}
