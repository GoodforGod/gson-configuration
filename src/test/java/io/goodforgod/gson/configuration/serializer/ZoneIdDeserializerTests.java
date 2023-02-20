package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.GsonConfiguration;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class ZoneIdDeserializerTests extends Assertions {

    static class User {

        private String name;
        private ZoneId value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ZoneId getValue() {
            return value;
        }

        public void setValue(ZoneId value) {
            this.value = value;
        }
    }

    private static final ZoneId VALUE_TIME = ZoneId.of("UTC");
    private static final String VALUE = "UTC";

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
