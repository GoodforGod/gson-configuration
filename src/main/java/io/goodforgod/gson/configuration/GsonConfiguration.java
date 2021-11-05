package io.goodforgod.gson.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
    static final String DATE_ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * yyyy-MM-dd'T'HH:mm:ssX
     */
    private DateTimeFormatter instantFormat = DateTimeFormatter.ISO_INSTANT;

    /**
     * yyyy-MM-dd
     */
    private DateTimeFormatter localDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * HH:mm:ss.SSS
     */
    private DateTimeFormatter localTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    private DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    /**
     * HH:mm:ss.SSSXXX
     */
    private DateTimeFormatter offsetTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSSXXX");

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    private DateTimeFormatter offsetDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSXXX[VV]
     */
    private DateTimeFormatter zonedDateTimeFormat = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            .optionalStart()
            .appendLiteral('[')
            .parseCaseSensitive()
            .appendZoneRegionId()
            .appendLiteral(']')
            .toFormatter();

    /**
     * yyyy
     */
    private DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");

    private String dateFormat = DATE_ISO_8601_PATTERN;

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
            configuration.setInstantFormat(DateTimeFormatter.ofPattern(formatInstant).withLocale(Locale.ENGLISH));
        if (formatLocalDate != null)
            configuration.setLocalDateFormat(DateTimeFormatter.ofPattern(formatLocalDate));
        if (formatLocalTime != null)
            configuration.setLocalTimeFormat(DateTimeFormatter.ofPattern(formatLocalTime));
        if (formatLocalDateTime != null)
            configuration.setLocalDateTimeFormat(DateTimeFormatter.ofPattern(formatLocalDateTime));
        if (formatOffsetTime != null)
            configuration.setOffsetTimeFormat(DateTimeFormatter.ofPattern(formatOffsetTime));
        if (formatOffsetDateTime != null)
            configuration.setOffsetDateTimeFormat(DateTimeFormatter.ofPattern(formatOffsetDateTime));
        if (formatZonedDateTime != null)
            configuration.setZonedDateTimeFormat(DateTimeFormatter.ofPattern(formatZonedDateTime));
        if (formatYear != null)
            configuration.setYearFormat(DateTimeFormatter.ofPattern(formatYear));
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
        final GsonBuilder builder = GsonAdapterBuilder.builder(this)
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

    public GsonConfiguration setInstantFormat(DateTimeFormatter instantFormat) {
        this.instantFormat = instantFormat;
        return this;
    }

    public DateTimeFormatter getLocalDateFormat() {
        return localDateFormat;
    }

    public GsonConfiguration setLocalDateFormat(DateTimeFormatter localDateFormat) {
        this.localDateFormat = localDateFormat;
        return this;
    }

    public DateTimeFormatter getLocalTimeFormat() {
        return localTimeFormat;
    }

    public GsonConfiguration setLocalTimeFormat(DateTimeFormatter localTimeFormat) {
        this.localTimeFormat = localTimeFormat;
        return this;
    }

    public DateTimeFormatter getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public GsonConfiguration setLocalDateTimeFormat(DateTimeFormatter localDateTimeFormat) {
        this.localDateTimeFormat = localDateTimeFormat;
        return this;
    }

    public DateTimeFormatter getOffsetTimeFormat() {
        return offsetTimeFormat;
    }

    public GsonConfiguration setOffsetTimeFormat(DateTimeFormatter offsetTimeFormat) {
        this.offsetTimeFormat = offsetTimeFormat;
        return this;
    }

    public DateTimeFormatter getOffsetDateTimeFormat() {
        return offsetDateTimeFormat;
    }

    public GsonConfiguration setOffsetDateTimeFormat(DateTimeFormatter offsetDateTimeFormat) {
        this.offsetDateTimeFormat = offsetDateTimeFormat;
        return this;
    }

    public DateTimeFormatter getZonedDateTimeFormat() {
        return zonedDateTimeFormat;
    }

    public GsonConfiguration setZonedDateTimeFormat(DateTimeFormatter zonedDateTimeFormat) {
        this.zonedDateTimeFormat = zonedDateTimeFormat;
        return this;
    }

    public DateTimeFormatter getYearFormat() {
        return yearFormat;
    }

    public GsonConfiguration setYearFormat(DateTimeFormatter yearFormat) {
        this.yearFormat = yearFormat;
        return this;
    }
}
