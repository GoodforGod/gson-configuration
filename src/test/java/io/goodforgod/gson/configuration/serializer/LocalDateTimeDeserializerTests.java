package io.goodforgod.gson.configuration.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.goodforgod.gson.configuration.deserializer.LocalDateTimeDeserializer;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class LocalDateTimeDeserializerTests extends Assertions {

    static class User {

        private String name;
        private LocalDateTime value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getValue() {
            return value;
        }

        public void setValue(LocalDateTime value) {
            this.value = value;
        }
    }

    private static final String CUSTOM_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    private static final String CUSTOM_VALUE = "1970-01-01T00:00:00.000";

    private static final LocalDateTime VALUE_TIME = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    private static final String VALUE = "1970-01-01T00:00:00";

    private final Gson adapter = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();

    private final Gson adapterCustom = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(CUSTOM_ISO)))
            .create();

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
    void serializationIsValidForCustomFormatter() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapterCustom.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + CUSTOM_VALUE + "\""), json);
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
