package io.goodforgod.gson.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class TimestampPatternTests extends Assertions {

    static class User {

        private String name;
        private Timestamp value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Timestamp getValue() {
            return value;
        }

        public void setValue(Timestamp value) {
            this.value = value;
        }
    }

    private static final Timestamp VALUE_TIME = Timestamp
            .from(LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC).toInstant(ZoneOffset.UTC));
    private static final String VALUE = "1970-01-01T00:00:00.000Z";

    private final Gson adapter = new GsonBuilder()
            .setDateFormat(DateTimeFormatters.ISO_DATE)
            .create();

    @Test
    void serializationIsValid() {
        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = adapter.toJson(user);
        assertNotNull(json);

        final Pattern pattern = Pattern.compile("\"value\":\"1970-01-01T\\d\\d:00:00\\.000(Z|\\+\\d\\d:00)\"");
        final Matcher matcher = pattern.matcher(json);
        assertTrue(matcher.find(), json);
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
