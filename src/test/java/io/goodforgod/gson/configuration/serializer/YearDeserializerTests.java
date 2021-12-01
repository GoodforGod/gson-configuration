package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.deserializer.YearDeserializer;
import java.time.Year;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class YearDeserializerTests extends Assertions {

  static class User {

    private String name;
    private Year value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Year getValue() {
      return value;
    }

    public void setValue(Year value) {
      this.value = value;
    }
  }

  private static final Year VALUE_TIME = Year.of(2000);
  private static final String VALUE = "2000";

  private final Gson adapter =
      new GsonBuilder()
          .registerTypeAdapter(Year.class, YearSerializer.INSTANCE)
          .registerTypeAdapter(Year.class, YearDeserializer.INSTANCE)
          .create();

  @Test
  void serializationIsValid() {
    final User user = new User();
    user.setName("Bob");
    user.setValue(VALUE_TIME);

    final String json = adapter.toJson(user);
    assertNotNull(json);
    assertTrue(json.contains("\"value\":\"" + VALUE + "\""), json);
  }

  @Test
  void deserializationFromIntIsValid() {
    final String json = "{\"name\":\"Bob\",\"value\":" + VALUE + "}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_TIME, user.getValue());
  }

  @Test
  void deserializationFromStringIsValid() {
    final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

    final User user = adapter.fromJson(json, User.class);
    assertNotNull(user);
    assertEquals("Bob", user.getName());
    assertEquals(VALUE_TIME, user.getValue());
  }

  @Test
  void deserializationFails() {
    final String json = "{\"name\":\"Bob\",\"value\":\"NOT_TIME\"}";

    try {
      adapter.fromJson(json, User.class);
      fail("Should not happen");
    } catch (JsonParseException e) {
      assertFalse(e.getMessage().isEmpty());
    }
  }
}
