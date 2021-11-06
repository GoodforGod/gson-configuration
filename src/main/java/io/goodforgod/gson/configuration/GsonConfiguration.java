package io.goodforgod.gson.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class GsonConfiguration {

    private DateTimeFormatter instantFormat = DateTimeFormatters.INSTANT_ISO;
    private DateTimeFormatter localDateFormat = DateTimeFormatters.LOCAL_DATE_ISO;
    private DateTimeFormatter localTimeFormat = DateTimeFormatters.LOCAL_TIME_ISO;
    private DateTimeFormatter localDateTimeFormat = DateTimeFormatters.LOCAL_DATE_TIME_ISO;
    private DateTimeFormatter offsetTimeFormat = DateTimeFormatters.OFFSET_TIME_ISO;
    private DateTimeFormatter offsetDateTimeFormat = DateTimeFormatters.OFFSET_DATE_TIME_ISO;
    private DateTimeFormatter zonedDateTimeFormat = DateTimeFormatters.ZONED_DATE_TIME_ISO;
    private DateTimeFormatter yearFormat = DateTimeFormatters.YEAR_ISO;
    private DateTimeFormatter yearMonthFormat = DateTimeFormatters.YEAR_MONTH_ISO;
    private DateTimeFormatter monthDayFormat = DateTimeFormatters.MONTH_DAY_ISO;
    private String dateFormat = DateTimeFormatters.DATE_ISO;

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
        final String formatYearMonth = properties.getProperty(GsonProperties.FORMAT_YEAR_MONTH);
        final String formatMonthDay = properties.getProperty(GsonProperties.FORMAT_MONTH_DAY);
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
        if (formatYearMonth != null)
            configuration.setYearMonthFormat(formatYearMonth);
        if (formatMonthDay != null)
            configuration.setMonthDayFormat(formatMonthDay);
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

    public GsonConfiguration setInstantFormat(String instantPattern) {
        return setInstantFormat(DateTimeFormatter.ofPattern(instantPattern));
    }

    public DateTimeFormatter getLocalDateFormat() {
        return localDateFormat;
    }

    public GsonConfiguration setLocalDateFormat(DateTimeFormatter localDateFormat) {
        this.localDateFormat = localDateFormat;
        return this;
    }

    public GsonConfiguration setLocalDateFormat(String localDatePattern) {
        return setLocalDateFormat(DateTimeFormatter.ofPattern(localDatePattern));
    }

    public DateTimeFormatter getLocalTimeFormat() {
        return localTimeFormat;
    }

    public GsonConfiguration setLocalTimeFormat(DateTimeFormatter localTimeFormat) {
        this.localTimeFormat = localTimeFormat;
        return this;
    }

    public GsonConfiguration setLocalTimeFormat(String localTimePattern) {
        return setLocalTimeFormat(DateTimeFormatter.ofPattern(localTimePattern));
    }

    public DateTimeFormatter getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public GsonConfiguration setLocalDateTimeFormat(DateTimeFormatter localDateTimeFormat) {
        this.localDateTimeFormat = localDateTimeFormat;
        return this;
    }

    public GsonConfiguration setLocalDateTimeFormat(String localDateTimePattern) {
        return setLocalDateTimeFormat(DateTimeFormatter.ofPattern(localDateTimePattern));
    }

    public DateTimeFormatter getOffsetTimeFormat() {
        return offsetTimeFormat;
    }

    public GsonConfiguration setOffsetTimeFormat(DateTimeFormatter offsetTimeFormat) {
        this.offsetTimeFormat = offsetTimeFormat;
        return this;
    }

    public GsonConfiguration setOffsetTimeFormat(String offsetTimePattern) {
        return setOffsetTimeFormat(DateTimeFormatter.ofPattern(offsetTimePattern));
    }

    public DateTimeFormatter getOffsetDateTimeFormat() {
        return offsetDateTimeFormat;
    }

    public GsonConfiguration setOffsetDateTimeFormat(DateTimeFormatter offsetDateTimeFormat) {
        this.offsetDateTimeFormat = offsetDateTimeFormat;
        return this;
    }

    public GsonConfiguration setOffsetDateTimeFormat(String offsetDateTimePattern) {
        return setOffsetDateTimeFormat(DateTimeFormatter.ofPattern(offsetDateTimePattern));
    }

    public DateTimeFormatter getZonedDateTimeFormat() {
        return zonedDateTimeFormat;
    }

    public GsonConfiguration setZonedDateTimeFormat(DateTimeFormatter zonedDateTimeFormat) {
        this.zonedDateTimeFormat = zonedDateTimeFormat;
        return this;
    }

    public GsonConfiguration setZonedDateTimeFormat(String zonedDateTimePattern) {
        return setZonedDateTimeFormat(DateTimeFormatter.ofPattern(zonedDateTimePattern));
    }

    public DateTimeFormatter getYearFormat() {
        return yearFormat;
    }

    public GsonConfiguration setYearFormat(DateTimeFormatter yearFormat) {
        this.yearFormat = yearFormat;
        return this;
    }

    public GsonConfiguration setYearFormat(String yearPattern) {
        return setYearFormat(DateTimeFormatter.ofPattern(yearPattern));
    }

    public DateTimeFormatter getYearMonthFormat() {
        return yearMonthFormat;
    }

    public GsonConfiguration setYearMonthFormat(DateTimeFormatter yearMonthFormat) {
        this.yearMonthFormat = yearMonthFormat;
        return this;
    }

    public GsonConfiguration setYearMonthFormat(String yearMonthPattern) {
        return setYearMonthFormat(DateTimeFormatter.ofPattern(yearMonthPattern));
    }

    public DateTimeFormatter getMonthDayFormat() {
        return monthDayFormat;
    }

    public GsonConfiguration setMonthDayFormat(DateTimeFormatter monthDayFormat) {
        this.monthDayFormat = monthDayFormat;
        return this;
    }

    public GsonConfiguration setMonthDayFormat(String monthDayPattern) {
        return setMonthDayFormat(DateTimeFormatter.ofPattern(monthDayPattern));
    }
}
