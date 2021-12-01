package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.deserializer.OffsetDateTimeDeserializer;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class OffsetDateTimeDeserializerTests extends Assertions {

  static class User {

    private String name;
    private OffsetDateTime value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public OffsetDateTime getValue() {
      return value;
    }

    public void setValue(OffsetDateTime value) {
      this.value = value;
    }
  }

  private static final String CUSTOM_ISO = "uuuu-MM-dd'T'HH-mm-ss.SSSXXX";
  private static final String CUSTOM_VALUE = "1970-01-01T00-00-00.000Z";

  private static final OffsetDateTime VALUE_TYPE =
      OffsetDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC"));
  private static final String VALUE_STR = "1970-01-01T00:00:00.000Z";

  private static final OffsetDateTime VALUE_TYPE_MOSCOW =
      OffsetDateTime.ofInstant(Instant.EPOCH, ZoneId.of("+03:00"));
  private static final String VALUE_STR_MOSCOW = "1970-01-01T03:00:00.000+03:00";

  private final Gson adapter =
      new GsonBuilder()
          .registerTypeAdapter(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE)
          .registerTypeAdapter(OffsetDateTime.class, OffsetDateTimeDeserializer.INSTANCE)
          .create();

  private final Gson adapterCustom =
      new GsonBuilder()
          .registerTypeAdapter(
              OffsetDateTime.class,
              new OffsetDateTimeSerializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
          .registerTypeAdapter(
              OffsetDateTime.class,
              new OffsetDateTimeDeserializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
          .create();

  @Test
  void serializationIsValidForIso() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_TYPE);

    final String json = adapter.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + VALUE_STR + "\""), json);
  }

  @Test
  void deserializationIsValidForIso() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_STR + "\"}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_TYPE, user.getValue());
  }

  @Test
  void serializationIsValidForIsoMoscow() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_TYPE_MOSCOW);

    final String json = adapter.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + VALUE_STR_MOSCOW + "\""), json);
  }

  @Test
  void deserializationIsValidForIsoMoscow() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_STR_MOSCOW + "\"}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_TYPE_MOSCOW, user.getValue());
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
  void serializationIsValidForCustomFormatter() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_TYPE);

    final String json = adapterCustom.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + CUSTOM_VALUE + "\""), json);
  }

  @Test
  void deserializationIsValidForCustomFormatter() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + CUSTOM_VALUE + "\"}";

    final User user = adapterCustom.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_TYPE, user.getValue());
  }
}
