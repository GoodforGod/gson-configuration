package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.GsonConfiguration;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class DayOfWeekDeserializerTests extends Assertions {

    static class User {

        private String name;
        private DayOfWeek value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DayOfWeek getValue() {
            return value;
        }

        public void setValue(DayOfWeek value) {
            this.value = value;
        }
    }

    private static final DayOfWeek VALUE_TIME = DayOfWeek.MONDAY;
    private static final String VALUE = "MONDAY";
    private static final String VALUE_NUMBER = "1";

    private final Gson adapter = new GsonConfiguration().builder().create();

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
    void deserializationIsValid() {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE_TIME, user.getValue());
    }

    @Test
    void deserializationIsValidForStringNumber() {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_NUMBER + "\"}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE_TIME, user.getValue());
    }

    @Test
    void deserializationIsValidForNumber() {
        final String json = "{\"name\":\"Bob\",\"value\":" + VALUE_NUMBER + "}";

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

    @Test
    void deserializationFailsForArray() {
        final String json = "{\"name\":\"Bob\",\"value\":[\"NOT_TIME\"]}";

        try {
            adapter.fromJson(json, MonthDeserializerTests.User.class);
            fail("Should not happen");
        } catch (JsonParseException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
}
