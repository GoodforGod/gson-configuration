package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * @see YearMonth
 * @author Anton Kurako (GoodforGod)
 * @since 06.11.2021
 */
public class YearMonthSerializer implements JsonSerializer<YearMonth> {

  public static final YearMonthSerializer INSTANCE = new YearMonthSerializer();

  private final DateTimeFormatter formatter;

  public YearMonthSerializer() {
    this(DateTimeFormatters.ISO_YEAR_MONTH);
  }

  public YearMonthSerializer(DateTimeFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public JsonElement serialize(YearMonth src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(formatter.format(src));
  }
}
