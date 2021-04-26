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
    public static final String ISO_8601_FORMATTER = "uuuu-MM-dd'T'HH:mm:ss.SSSXXX";

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

    public void setDateFormat(String dateFormat) {
        // validation check
        this.dateFormat = new SimpleDateFormat(dateFormat).toPattern();
    }

    public FieldNamingPolicy getFieldNamingPolicy() {
        return fieldNamingPolicy;
    }

    public void setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        if (fieldNamingPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.fieldNamingPolicy = fieldNamingPolicy;
    }

    public LongSerializationPolicy getLongSerializationPolicy() {
        return longSerializationPolicy;
    }

    public void setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.longSerializationPolicy = longSerializationPolicy;
    }

    public boolean isSerializeNulls() {
        return serializeNulls;
    }

    public void setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public boolean isComplexMapKeySerialization() {
        return complexMapKeySerialization;
    }

    public void setComplexMapKeySerialization(boolean complexMapKeySerialization) {
        this.complexMapKeySerialization = complexMapKeySerialization;
    }

    public boolean isGenerateNonExecutableJson() {
        return generateNonExecutableJson;
    }

    public void setGenerateNonExecutableJson(boolean generateNonExecutableJson) {
        this.generateNonExecutableJson = generateNonExecutableJson;
    }

    public boolean isEscapeHtmlChars() {
        return escapeHtmlChars;
    }

    public void setEscapeHtmlChars(boolean escapeHtmlChars) {
        this.escapeHtmlChars = escapeHtmlChars;
    }

    public boolean isPrettyPrinting() {
        return prettyPrinting;
    }

    public void setPrettyPrinting(boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
    }

    public boolean isLenient() {
        return lenient;
    }

    public void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    public boolean isSerializeSpecialFloatingPointValues() {
        return serializeSpecialFloatingPointValues;
    }

    public void setSerializeSpecialFloatingPointValues(boolean serializeSpecialFloatingPointValues) {
        this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
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
                .registerTypeAdapter(ZoneId.class, new ZoneIdAdapter())
                .registerTypeAdapter(Year.class, new YearAdapter(getYearFormat()))
                .registerTypeAdapter(Instant.class, new InstantAdapter(getInstantFormat()))
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter(getLocalDateFormat()))
                .registerTypeAdapter(LocalTime.class, new LocalDateAdapter(getLocalTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter(getLocalDateTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeAdapter(getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter(getOffsetTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter(getZonedDateTimeFormat()));
    }
}
