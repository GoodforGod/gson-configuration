package io.gson.adapters.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class GsonConfiguration {

    /**
     * ISO 8601
     */
    public static final String ISO_8601_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

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
        this.dateFormat = DateTimeFormatter.ofPattern(dateFormat).toString();
    }

    public FieldNamingPolicy getFieldNamingPolicy() {
        return fieldNamingPolicy;
    }

    public void setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        if(fieldNamingPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.fieldNamingPolicy = fieldNamingPolicy;
    }

    public LongSerializationPolicy getLongSerializationPolicy() {
        return longSerializationPolicy;
    }

    public void setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        if(longSerializationPolicy == null)
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

    public GsonBuilder builder() {
        final GsonBuilder builder = new GsonBuilder()
                .setDateFormat(getDateFormat())
                .setLongSerializationPolicy(getLongSerializationPolicy())
                .setFieldNamingPolicy(getFieldNamingPolicy());

        if(isComplexMapKeySerialization())
            builder.enableComplexMapKeySerialization();
        if(!isEscapeHtmlChars())
            builder.disableHtmlEscaping();
        if(isGenerateNonExecutableJson())
            builder.generateNonExecutableJson();
        if(isLenient())
            builder.setLenient();
        if(isPrettyPrinting())
            builder.setPrettyPrinting();
        if(isSerializeSpecialFloatingPointValues())
            builder.serializeSpecialFloatingPointValues();
        if(isSerializeNulls())
            builder.serializeNulls();

        return builder;
    }
}
