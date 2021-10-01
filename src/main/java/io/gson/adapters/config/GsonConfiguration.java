package io.gson.adapters.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import io.gson.adapters.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class GsonConfiguration {

    /**
     * ISO 8601 for {@link java.util.Date}
     */
    static final String ISO_8601_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * yyyy-MM-dd'T'HH:mm:ssX
     */
    private DateTimeFormatter instantFormat = DateTimeFormatter.ISO_INSTANT;

    /**
     * yyyy-MM-dd
     */
    private DateTimeFormatter localDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * HH:mm:ss
     */
    private DateTimeFormatter localTimeFormat = DateTimeFormatter.ISO_LOCAL_TIME;

    /**
     * yyyy-MM-dd'T'HH:mm:ss
     */
    private DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * HH:mm:ss.SSSXXX
     */
    private DateTimeFormatter offsetTimeFormat = DateTimeFormatter.ISO_OFFSET_TIME;

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    private DateTimeFormatter offsetDateTimeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSXXX[VV]
     */
    private DateTimeFormatter zonedDateTimeFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    /**
     * yyyy
     */
    private DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");

    private String dateFormat = ISO_8601_FORMATTER;

    /**
     * @see GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)
     */
    private FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;

    /**
     * @see GsonBuilder#setLongSerializationPolicy(LongSerializationPolicy)
     */
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;

    /**
     * @see GsonBuilder#serializeNulls()
     */
    private boolean serializeNulls = false;

    /**
     * @see GsonBuilder#enableComplexMapKeySerialization()
     */
    private boolean complexMapKeySerialization = false;

    /**
     * @see GsonBuilder#generateNonExecutableJson()
     */
    private boolean generateNonExecutableJson = false;

    /**
     * @see GsonBuilder#disableHtmlEscaping()
     */
    private boolean escapeHtmlChars = true;

    /**
     * @see GsonBuilder#setPrettyPrinting()
     */
    private boolean prettyPrinting = false;

    /**
     * @see GsonBuilder#setLenient()
     */
    private boolean lenient = false;

    /**
     * @see GsonBuilder#serializeSpecialFloatingPointValues()
     */
    private boolean serializeSpecialFloatingPointValues = false;

    public String getDateFormat() {
        return dateFormat;
    }

    public GsonConfiguration setDateFormat(String dateFormat) {
        // validation check
        this.dateFormat = new SimpleDateFormat(dateFormat).toPattern();
        return this;
    }

    public FieldNamingPolicy getFieldNamingPolicy() {
        return fieldNamingPolicy;
    }

    public GsonConfiguration setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        if (fieldNamingPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.fieldNamingPolicy = fieldNamingPolicy;
        return this;
    }

    public LongSerializationPolicy getLongSerializationPolicy() {
        return longSerializationPolicy;
    }

    public GsonConfiguration setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.longSerializationPolicy = longSerializationPolicy;
        return this;
    }

    public boolean isSerializeNulls() {
        return serializeNulls;
    }

    public GsonConfiguration setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
        return this;
    }

    public boolean isComplexMapKeySerialization() {
        return complexMapKeySerialization;
    }

    public GsonConfiguration setComplexMapKeySerialization(boolean complexMapKeySerialization) {
        this.complexMapKeySerialization = complexMapKeySerialization;
        return this;
    }

    public boolean isGenerateNonExecutableJson() {
        return generateNonExecutableJson;
    }

    public GsonConfiguration setGenerateNonExecutableJson(boolean generateNonExecutableJson) {
        this.generateNonExecutableJson = generateNonExecutableJson;
        return this;
    }

    public boolean isEscapeHtmlChars() {
        return escapeHtmlChars;
    }

    public GsonConfiguration setEscapeHtmlChars(boolean escapeHtmlChars) {
        this.escapeHtmlChars = escapeHtmlChars;
        return this;
    }

    public boolean isPrettyPrinting() {
        return prettyPrinting;
    }

    public GsonConfiguration setPrettyPrinting(boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
        return this;
    }

    public boolean isLenient() {
        return lenient;
    }

    public GsonConfiguration setLenient(boolean lenient) {
        this.lenient = lenient;
        return this;
    }

    public boolean isSerializeSpecialFloatingPointValues() {
        return serializeSpecialFloatingPointValues;
    }

    public GsonConfiguration setSerializeSpecialFloatingPointValues(boolean serializeSpecialFloatingPointValues) {
        this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
        return this;
    }

    public DateTimeFormatter getInstantFormat() {
        return instantFormat;
    }

    public GsonConfiguration setInstantFormat(String instantFormat) {
        this.instantFormat = DateTimeFormatter.ofPattern(instantFormat)
                .withLocale(Locale.ENGLISH)
                .withZone(ZoneOffset.UTC);
        return this;
    }

    public DateTimeFormatter getLocalDateFormat() {
        return localDateFormat;
    }

    public GsonConfiguration setLocalDateFormat(String localDateFormat) {
        this.localDateFormat = DateTimeFormatter.ofPattern(localDateFormat);
        return this;
    }

    public DateTimeFormatter getLocalTimeFormat() {
        return localTimeFormat;
    }

    public GsonConfiguration setLocalTimeFormat(String localTimeFormat) {
        this.localTimeFormat = DateTimeFormatter.ofPattern(localTimeFormat);
        return this;
    }

    public DateTimeFormatter getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public GsonConfiguration setLocalDateTimeFormat(String localDateTimeFormat) {
        this.localDateTimeFormat = DateTimeFormatter.ofPattern(localDateTimeFormat);
        return this;
    }

    public DateTimeFormatter getOffsetTimeFormat() {
        return offsetTimeFormat;
    }

    public GsonConfiguration setOffsetTimeFormat(String offsetTimeFormat) {
        this.offsetTimeFormat = DateTimeFormatter.ofPattern(offsetTimeFormat);
        return this;
    }

    public DateTimeFormatter getOffsetDateTimeFormat() {
        return offsetDateTimeFormat;
    }

    public GsonConfiguration setOffsetDateTimeFormat(String offsetDateTimeFormat) {
        this.offsetDateTimeFormat = DateTimeFormatter.ofPattern(offsetDateTimeFormat);
        return this;
    }

    public DateTimeFormatter getZonedDateTimeFormat() {
        return zonedDateTimeFormat;
    }

    public GsonConfiguration setZonedDateTimeFormat(String zonedDateTimeFormat) {
        this.zonedDateTimeFormat = DateTimeFormatter.ofPattern(zonedDateTimeFormat);
        return this;
    }

    public DateTimeFormatter getYearFormat() {
        return yearFormat;
    }

    public GsonConfiguration setYearFormat(String yearFormat) {
        this.yearFormat = DateTimeFormatter.ofPattern(yearFormat);
        return this;
    }

    public static GsonConfiguration ofProperties(Properties properties) {
        final String formatInstant = properties.getProperty(GsonProperties.FORMAT_INSTANT);
        final String formatLocalDate = properties.getProperty(GsonProperties.FORMAT_LOCAL_DATE);
        final String formatLocalTime = properties.getProperty(GsonProperties.FORMAT_LOCAL_TIME);
        final String formatLocalDateTime = properties.getProperty(GsonProperties.FORMAT_LOCAL_DATE_TIME);
        final String formatOffsetTime = properties.getProperty(GsonProperties.FORMAT_OFFSET_TIME);
        final String formatOffsetDateTime = properties.getProperty(GsonProperties.FORMAT_OFFSET_DATE_TIME);
        final String formatZonedDateTime = properties.getProperty(GsonProperties.FORMAT_ZONED_DATE_TIME);
        final String formatYear = properties.getProperty(GsonProperties.FORMAT_YEAR);
        final String formatDate = properties.getProperty(GsonProperties.FORMAT_DATE);

        final String fieldNamingPolicy = properties.getProperty(GsonProperties.POLICY_FIELD_NAMING);
        final String longSerializationPolicy = properties.getProperty(GsonProperties.POLICY_LONG_SERIALIZATION);

        final String lenient = properties.getProperty(GsonProperties.LENIENT);
        final String serializeNulls = properties.getProperty(GsonProperties.SERIALIZE_NULLS);
        final String prettyPrinting = properties.getProperty(GsonProperties.PRETTY_PRINTING);
        final String escapeHtmlChars = properties.getProperty(GsonProperties.ESCAPE_HTML_CHARS);
        final String generateNonExecutableJson = properties.getProperty(GsonProperties.GENERATE_NON_EXECUTABLE_JSON);
        final String complexMapKeySerialization = properties.getProperty(GsonProperties.COMPLEX_MAP_KEY_SERIALIZATION);
        final String serializeSpecialFloatingPointValues = properties
                .getProperty(GsonProperties.SERIALIZE_SPECIAL_FLOATING_POINT_VALUES);

        final GsonConfiguration configuration = new GsonConfiguration();

        if (formatInstant != null)
            configuration.setInstantFormat(formatInstant);
        if (formatLocalDate != null)
            configuration.setLocalDateFormat(formatLocalDate);
        if (formatLocalTime != null)
            configuration.setLocalTimeFormat(formatLocalTime);
        if (formatLocalDateTime != null)
            configuration.setLocalDateTimeFormat(formatLocalDateTime);
        if (formatOffsetTime != null)
            configuration.setOffsetTimeFormat(formatOffsetTime);
        if (formatOffsetDateTime != null)
            configuration.setOffsetDateTimeFormat(formatOffsetDateTime);
        if (formatZonedDateTime != null)
            configuration.setZonedDateTimeFormat(formatZonedDateTime);
        if (formatYear != null)
            configuration.setYearFormat(formatYear);
        if (formatDate != null)
            configuration.setDateFormat(formatDate);

        if (fieldNamingPolicy != null)
            configuration.setFieldNamingPolicy(FieldNamingPolicy.valueOf(fieldNamingPolicy));
        if (longSerializationPolicy != null)
            configuration.setLongSerializationPolicy(LongSerializationPolicy.valueOf(longSerializationPolicy));

        if (lenient != null)
            configuration.setLenient(Boolean.parseBoolean(lenient));
        if (serializeNulls != null)
            configuration.setSerializeNulls(Boolean.parseBoolean(serializeNulls));
        if (prettyPrinting != null)
            configuration.setPrettyPrinting(Boolean.parseBoolean(prettyPrinting));
        if (escapeHtmlChars != null)
            configuration.setEscapeHtmlChars(Boolean.parseBoolean(escapeHtmlChars));
        if (generateNonExecutableJson != null)
            configuration.setGenerateNonExecutableJson(Boolean.parseBoolean(generateNonExecutableJson));
        if (complexMapKeySerialization != null)
            configuration.setComplexMapKeySerialization(Boolean.parseBoolean(complexMapKeySerialization));
        if (serializeSpecialFloatingPointValues != null)
            configuration.setSerializeSpecialFloatingPointValues(Boolean.parseBoolean(serializeSpecialFloatingPointValues));

        return configuration;
    }

    public GsonBuilder builder() {
        final GsonBuilder builder = GsonAdapters.builder()
                .setDateFormat(getDateFormat())
                .setLongSerializationPolicy(getLongSerializationPolicy())
                .setFieldNamingPolicy(getFieldNamingPolicy());

        if (isComplexMapKeySerialization())
            builder.enableComplexMapKeySerialization();
        if (isGenerateNonExecutableJson())
            builder.generateNonExecutableJson();
        if (isLenient())
            builder.setLenient();
        if (isPrettyPrinting())
            builder.setPrettyPrinting();
        if (isSerializeSpecialFloatingPointValues())
            builder.serializeSpecialFloatingPointValues();
        if (isSerializeNulls())
            builder.serializeNulls();
        if (!isEscapeHtmlChars())
            builder.disableHtmlEscaping();

        return builder;
    }
}
