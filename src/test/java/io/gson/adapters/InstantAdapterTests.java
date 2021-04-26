package io.gson.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class InstantAdapterTests extends Assertions {

    static class User {

        private String name;
        private Instant value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Instant getValue() {
            return value;
        }

        public void setValue(Instant value) {
            this.value = value;
        }
    }

    private static final String CUSTOM_ISO = "yyyy-MM-dd HH:mm:ss";
    private static final String CUSTOM_VALUE = "1970-01-01 00:00:00";

    private static final Instant VALUE_TIME = OffsetDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC")).toInstant();
    private static final String VALUE = "1970-01-01T00:00:00Z";

    private final Gson adapter = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .create();

    private final Gson adapterCustom = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantAdapter(DateTimeFormatter.ofPattern(CUSTOM_ISO)
                    .withLocale(Locale.ENGLISH)
                    .withZone(ZoneOffset.UTC)))
            .create();

    @Test
    void serializationIsValidForIso() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapter.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE + "\""));
    }

    @Test
    void serializationIsValidForCustomFormatter() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapterCustom.toJson(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + CUSTOM_VALUE + "\""));
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
    void deserializationIsValidForIsoFails() {
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
        final String json = "{\"name\":\"Bob\",\"value\":\"1970-01-01 00:00:00\"}";

        final User user = adapterCustom.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(VALUE_TIME, user.getValue());
    }
}
