package io.goodforgod.gson.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.LongSerializationPolicy;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 01.10.2021
 */
class GsonConfigurationFromPropertiesTests extends Assertions {

    @Test
    void configurationPropertiesApplied() throws Exception {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream("gson.properties")) {
            final Properties properties = new Properties();
            properties.load(resource);

            final GsonConfiguration configuration = GsonConfiguration.ofProperties(properties);
            assertNotNull(configuration.getYearFormat());
            assertNotNull(configuration.getYearMonthFormat());
            assertNotNull(configuration.getMonthDayFormat());
            assertNotNull(configuration.getDateFormat());
            assertNotNull(configuration.getInstantFormat());
            assertNotNull(configuration.getLocalDateFormat());
            assertNotNull(configuration.getLocalTimeFormat());
            assertNotNull(configuration.getLocalDateTimeFormat());
            assertNotNull(configuration.getOffsetTimeFormat());
            assertNotNull(configuration.getOffsetDateTimeFormat());

            assertTrue(configuration.isForceIsoChronology());
            assertTrue(configuration.isForceResolverStrict());

            assertTrue(configuration.isLenient());
            assertTrue(configuration.isComplexMapKeySerialization());
            assertTrue(configuration.isPrettyPrinting());
            assertTrue(configuration.isSerializeNulls());
            assertTrue(configuration.isGenerateNonExecutableJson());
            assertTrue(configuration.isSerializeSpecialFloatingPointValues());
            assertFalse(configuration.isEscapeHtmlChars());
            assertTrue(configuration.isExcludeFieldsWithoutExposeAnnotation());
            assertEquals(1, configuration.getExcludeFieldsWithModifiers().size());
            assertTrue(configuration.getExcludeFieldsWithModifiers().contains(GsonConfiguration.FieldModifiers.TRANSIENT));

            assertEquals(FieldNamingPolicy.UPPER_CAMEL_CASE, configuration.getFieldNamingPolicy());
            assertEquals(LongSerializationPolicy.STRING, configuration.getLongSerializationPolicy());

            assertNotNull(configuration.builder());
        }
    }

    @Test
    void configurationPropertiesAsDefault() {
        final Properties properties = new Properties();

        final GsonConfiguration configuration = GsonConfiguration.ofProperties(properties);
        assertNotNull(configuration.getDateFormat());

        assertNull(configuration.getYearFormat());
        assertNull(configuration.getInstantFormat());
        assertNull(configuration.getLocalDateFormat());
        assertNull(configuration.getLocalTimeFormat());
        assertNull(configuration.getLocalDateTimeFormat());
        assertNull(configuration.getOffsetTimeFormat());
        assertNull(configuration.getOffsetDateTimeFormat());

        assertFalse(configuration.isLenient());
        assertFalse(configuration.isComplexMapKeySerialization());
        assertFalse(configuration.isPrettyPrinting());
        assertFalse(configuration.isSerializeNulls());
        assertFalse(configuration.isGenerateNonExecutableJson());
        assertFalse(configuration.isSerializeSpecialFloatingPointValues());
        assertTrue(configuration.isEscapeHtmlChars());
        assertFalse(configuration.isExcludeFieldsWithoutExposeAnnotation());
        assertEquals(4, configuration.getExcludeFieldsWithModifiers().size());
        assertTrue(configuration.getExcludeFieldsWithModifiers().contains(GsonConfiguration.FieldModifiers.TRANSIENT));
        assertTrue(configuration.getExcludeFieldsWithModifiers().contains(GsonConfiguration.FieldModifiers.STATIC));
        assertTrue(configuration.getExcludeFieldsWithModifiers().contains(GsonConfiguration.FieldModifiers.VOLATILE));
        assertTrue(configuration.getExcludeFieldsWithModifiers().contains(GsonConfiguration.FieldModifiers.SYNCHRONIZED));

        assertEquals(FieldNamingPolicy.IDENTITY, configuration.getFieldNamingPolicy());
        assertEquals(LongSerializationPolicy.DEFAULT, configuration.getLongSerializationPolicy());
    }
}
