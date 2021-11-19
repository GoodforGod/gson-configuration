package io.goodforgod.gson.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 28.04.2021
 */
class GsonConfigurationTests extends Assertions {

    static class User {

        private String name;
        private Date value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getValue() {
            return value;
        }

        public void setValue(Date value) {
            this.value = value;
        }
    }

    @Test
    void configFailForNullFieldNamePolicy() {
        try {
            final GsonBuilder builder = new GsonConfiguration()
                    .setFieldNamingPolicy(null)
                    .builder();
        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void configFailForNullLongPolicy() {
        try {
            final GsonBuilder builder = new GsonConfiguration()
                    .setLongSerializationPolicy(null)
                    .builder();
        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void configBuilderFailForFormatter() {
        try {
            final GsonBuilder builder = new GsonConfiguration()
                    .setInstantFormat(DateTimeFormatter.ofPattern("uuuu-MM-dddd HHHH:mm:ss"))
                    .builder();
        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void configBuilderPropertiesValid() {
        final GsonBuilder builder = new GsonConfiguration()
                .setDateFormat(DateTimeFormatters.ISO_DATE)
                .setInstantFormat(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"))
                .setComplexMapKeySerialization(true)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setLenient(true)
                .setEscapeHtmlChars(false)
                .setPrettyPrinting(true)
                .setGenerateNonExecutableJson(true)
                .setSerializeNulls(true)
                .setSerializeSpecialFloatingPointValues(true)
                .builder();

        assertNotNull(builder);
    }

    @Test
    void configBuilderValidDateFormatISO() {
        final Gson gson = new GsonConfiguration()
                .builder()
                .create();

        final Timestamp timestamp = Timestamp.from(OffsetDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC")).toInstant());
        final User user = new User();
        user.setName("bob");
        user.setValue(timestamp);

        final String json = gson.toJson(user);
        assertNotNull(json);

        final Pattern pattern = Pattern.compile("\"value\":\"1970-01-01T0[03]:00:00.000");
        assertTrue(pattern.matcher(json).find());
    }
}
