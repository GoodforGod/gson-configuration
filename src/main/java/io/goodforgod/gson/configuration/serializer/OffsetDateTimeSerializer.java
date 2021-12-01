package io.goodforgod.gson.configuration.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.goodforgod.gson.configuration.DateTimeFormatters;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @see OffsetDateTime
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime> {

  public static final OffsetDateTimeSerializer INSTANCE = new OffsetDateTimeSerializer();

  private final DateTimeFormatter formatter;

  public OffsetDateTimeSerializer() {
    this(DateTimeFormatters.ISO_OFFSET_DATE_TIME);
  }

  public OffsetDateTimeSerializer(DateTimeFormatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public JsonElement serialize(
      OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(formatter.format(src));
  }
}
