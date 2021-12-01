package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.deserializer.ZonedDateTimeDeserializer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class ZonedDateTimeDeserializerTests extends Assertions {

  static class User {

    private String name;
    private ZonedDateTime value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public ZonedDateTime getValue() {
      return value;
    }

    public void setValue(ZonedDateTime value) {
      this.value = value;
    }
  }

  private static final String CUSTOM_ISO = "uuuu-MM-dd'T'HH-mm-ss.SSSXXX";
  private static final String CUSTOM_VALUE = "1970-01-01T00-00-00.000Z";

  private static final ZonedDateTime VALUE_AS_TIME =
      ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC"));
  private static final String VALUE_AS_STRING = "1970-01-01T00:00:00.000Z[UTC]";

  private static final ZonedDateTime VALUE_AS_TIME_NON_UTC =
      ZonedDateTime.ofInstant(
          LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.of("Europe/Paris")),
          ZoneOffset.UTC,
          ZoneId.of("Europe/Paris"));
  private static final String VALUE_AS_STRING_NON_UTC =
      "1970-01-01T02:00:00.000+01:00[Europe/Paris]";

  private final Gson adapter =
      new GsonBuilder()
          .registerTypeAdapter(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE)
          .registerTypeAdapter(ZonedDateTime.class, ZonedDateTimeDeserializer.INSTANCE)
          .create();

  private final Gson adapterCustom =
      new GsonBuilder()
          .registerTypeAdapter(
              ZonedDateTime.class,
              new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
          .registerTypeAdapter(
              ZonedDateTime.class,
              new ZonedDateTimeDeserializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
          .create();

  @Test
  void serializationIsValidForIso() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_AS_TIME);

    final String json = adapter.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + VALUE_AS_STRING + "\""), json);
  }

  @Test
  void deserializationIsValidForIso() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_AS_STRING + "\"}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_AS_TIME, user.getValue());
  }

  @Test
  void serializationIsValidForIsoNonUtc() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_AS_TIME_NON_UTC);

    final String json = adapter.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + VALUE_AS_STRING_NON_UTC + "\""), json);
  }

  @Test
  void deserializationIsValidForIsoNonUtc() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_AS_STRING_NON_UTC + "\"}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_AS_TIME_NON_UTC, user.getValue());
  }

  @Test
  void serializationIsValidForCustomFormatter() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_AS_TIME);

    final String json = adapterCustom.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + CUSTOM_VALUE + "\""), json);
  }

  @Test
  void deserializationFails() {
    final String json = "{\"name\":\"Bob\",\"value\":\"1970-01-01 03:00\"}";

    try {
      adapter.fromJson(json, User.class);
      fail("Should not happen");
    } catch (JsonParseException e) {
      assertTrue(e.getCause() instanceof DateTimeException);
    }
  }

  @Test
  void deserializationIsValidForCustomFormatter() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + CUSTOM_VALUE + "\"}";

    final User user = adapterCustom.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_AS_TIME.toEpochSecond(), user.getValue().toEpochSecond());
  }
}
