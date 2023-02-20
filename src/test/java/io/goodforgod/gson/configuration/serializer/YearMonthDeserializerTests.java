package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.GsonConfiguration;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class YearMonthDeserializerTests extends Assertions {

    static class User {

        private String name;
        private YearMonth value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public YearMonth getValue() {
            return value;
        }

        public void setValue(YearMonth value) {
            this.value = value;
        }
    }

    private static final YearMonth VALUE = YearMonth.of(2000, 1);
    private static final String VALUE_AS_STRING = "2000-01";

    private final Gson adapter = new GsonConfiguration().builder().create();

    @Test
    void serializationIsValid() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE);

        final String json = adapter.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE_AS_STRING + "\""), json);
    }

    @Test
    void deserializationFromIntIsValid() {
        final String json = "{\"name\":\"Bob\",\"value\":" + VALUE_AS_STRING + "}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE, user.getValue());
    }

    @Test
    void deserializationFromStringIsValid() {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_AS_STRING + "\"}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE, user.getValue());
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
