package io.goodforgod.gson.configuration;

import com.google.gson.Gson;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 19.01.2022
 */
class GsonFactoryTests extends Assertions {

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

    private static final LocalDateTime VALUE_TIME = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
    private static final String VALUE = "1970-01-01T00-00-00";

    private final GsonFactory factory = new GsonFactory();

    @Test
    void serializationIsValid() {
        final Gson gson = factory.build();

        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = gson.toJson(user);
        assertNotNull(json);
    }

    @Test
    void deserializationIsValid() {
        final Gson gson = factory.build();

        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = gson.fromJson(json, User.class);
        assertNotNull(user);
    }

    @Test
    void serializationForJavaISOIsValid() {
        final Gson gson = factory.buildJavaISO();

        final User user = new User();
        user.setName("Bob");
        user.setValue(VALUE_TIME);

        final String json = gson.toJson(user);
        assertNotNull(json);
    }

    @Test
    void deserializationForJavaISOIsValid() {
        final Gson gson = factory.buildJavaISO();

        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = gson.fromJson(json, User.class);
        assertNotNull(user);
    }
}
