package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.deserializer.LocalTimeDeserializer;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class LocalTimeDeserializerTests extends Assertions {

    static class User {

        private String name;
        private LocalTime value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalTime getValue() {
            return value;
        }

        public void setValue(LocalTime value) {
            this.value = value;
        }
    }

    private static final String SHORT_VALUE = "00:00:00";

    private static final String CUSTOM_ISO = "HH:mm:ss.SSS";
    private static final String CUSTOM_VALUE = "00:00:00.000";

    private static final LocalTime VALUE_TIME = LocalTime.MIN;
    private static final String VALUE = "00:00:00.000";

    private final Gson adapter = new GsonBuilder()
            .registerTypeAdapter(LocalTime.class, LocalTimeSerializer.INSTANCE)
            .registerTypeAdapter(LocalTime.class, LocalTimeDeserializer.INSTANCE)
            .create();

    private final Gson adapterCustom = new GsonBuilder()
            .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
            .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
            .create();

    @Test
    void serializationIsValidForCustomFormatter() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapterCustom.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + CUSTOM_VALUE + "\""), json);
    }

    @Test
    void deserializationIsValidForIsoShort() {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + SHORT_VALUE + "\"}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE_TIME, user.getValue());
    }

    @Test
    void serializationIsValidForIso() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapter.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE + "\""), json);
    }

    @Test
    void deserializationIsValidForIso() {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = adapter.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE_TIME, user.getValue());
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
        assertEquals(VALUE_TIME, user.getValue());
    }
}
