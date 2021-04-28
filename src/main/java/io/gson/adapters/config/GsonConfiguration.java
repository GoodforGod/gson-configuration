package io.gson.adapters.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import io.gson.adapters.*;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class GsonConfiguration {

    /**
     * ISO 8601 for {@link java.util.Date}
     */
    public static final String ISO_8601_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private DateTimeFormatter instantFormat = DateTimeFormatter.ISO_INSTANT;
    private DateTimeFormatter localDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    private DateTimeFormatter localTimeFormat = DateTimeFormatter.ISO_LOCAL_TIME;
    private DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private DateTimeFormatter offsetTimeFormat = DateTimeFormatter.ISO_OFFSET_TIME;
    private DateTimeFormatter offsetDateTimeFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private DateTimeFormatter zonedDateTimeFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");

    private String dateFormat = ISO_8601_FORMATTER;

    private FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private boolean serializeNulls = false;
    private boolean complexMapKeySerialization = false;
    private boolean generateNonExecutableJson = false;
    private boolean escapeHtmlChars = true;
    private boolean prettyPrinting = false;
    private boolean lenient = false;
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

    public GsonBuilder builder() {
        final GsonBuilder builder = new GsonBuilder()
                .setDateFormat(getDateFormat())
                .setLongSerializationPolicy(getLongSerializationPolicy())
                .setFieldNamingPolicy(getFieldNamingPolicy());

        if (isComplexMapKeySerialization())
            builder.enableComplexMapKeySerialization();
        if (!isEscapeHtmlChars())
            builder.disableHtmlEscaping();
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

        return builder.registerTypeAdapter(DayOfWeek.class, new DayOfWeekAdapter())
                .registerTypeAdapter(Month.class, new MonthAdapter())
                .registerTypeAdapter(Year.class, new YearAdapter(getYearFormat()))
                .registerTypeAdapter(ZoneId.class, new ZoneIdAdapter())
                .registerTypeAdapter(Instant.class, new InstantAdapter(getInstantFormat()))
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter(getLocalDateFormat()))
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter(getLocalTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter(getLocalDateTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeAdapter(getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter(getOffsetDateTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter(getZonedDateTimeFormat()));
    }
}
