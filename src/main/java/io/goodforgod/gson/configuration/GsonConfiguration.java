package io.goodforgod.gson.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class GsonConfiguration {

    private static final Set<FieldModifiers> DEFAULT_EXCLUDED_MODIFIERS;

    static {
        final Set<FieldModifiers> modifiers = new HashSet<>(8);
        modifiers.add(FieldModifiers.TRANSIENT);
        modifiers.add(FieldModifiers.SYNCHRONIZED);
        modifiers.add(FieldModifiers.VOLATILE);
        modifiers.add(FieldModifiers.STATIC);
        DEFAULT_EXCLUDED_MODIFIERS = Collections.unmodifiableSet(modifiers);
    }

    /**
     * @see GsonBuilder#excludeFieldsWithModifiers(int...)
     */
    public enum FieldModifiers {

        PUBLIC(Modifier.PUBLIC),
        PROTECTED(Modifier.PROTECTED),
        PRIVATE(Modifier.PRIVATE),
        STATIC(Modifier.STATIC),
        FINAL(Modifier.FINAL),
        SYNCHRONIZED(Modifier.SYNCHRONIZED),
        VOLATILE(Modifier.VOLATILE),
        TRANSIENT(Modifier.TRANSIENT);

        private final int modifier;

        FieldModifiers(int modifier) {
            this.modifier = modifier;
        }

        public int modifier() {
            return modifier;
        }
    }

    private DateTimeFormatter instantFormat = DateTimeFormatters.ISO_INSTANT;
    private DateTimeFormatter localDateFormat = DateTimeFormatters.ISO_LOCAL_DATE;
    private DateTimeFormatter localTimeFormat = DateTimeFormatters.ISO_LOCAL_TIME;
    private DateTimeFormatter localDateTimeFormat = DateTimeFormatters.ISO_LOCAL_DATE_TIME;
    private DateTimeFormatter offsetTimeFormat = DateTimeFormatters.ISO_OFFSET_TIME;
    private DateTimeFormatter offsetDateTimeFormat = DateTimeFormatters.ISO_OFFSET_DATE_TIME;
    private DateTimeFormatter zonedDateTimeFormat = DateTimeFormatters.ISO_ZONED_DATE_TIME;
    private DateTimeFormatter yearFormat = DateTimeFormatters.ISO_YEAR;
    private DateTimeFormatter yearMonthFormat = DateTimeFormatters.ISO_YEAR_MONTH;
    private DateTimeFormatter monthDayFormat = DateTimeFormatters.ISO_MONTH_DAY;
    private String dateFormat = DateTimeFormatters.ISO_DATE;

    /**
     * Forces {@link java.time.format.ResolverStyle#STRICT} for all formatters setters
     */
    private boolean forceResolverStrict = false;

    /**
     * Forces {@link IsoChronology#INSTANCE} for all formatters setters
     */
    private boolean forceIsoChronology = false;

    /**
     * Configures Gson to apply a specific naming policy to an object's field during serialization and
     * deserialization.
     *
     * @see GsonBuilder#setFieldNamingPolicy(FieldNamingPolicy)
     */
    private FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;

    /**
     * Configures Gson to apply a specific serialization policy for {@code Long} and {@code long}
     * objects.
     *
     * @see GsonBuilder#setLongSerializationPolicy(LongSerializationPolicy)
     */
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;

    /**
     * Configure Gson to serialize null fields. By default, Gson omits all fields that are null during
     * serialization.
     *
     * @see GsonBuilder#serializeNulls()
     */
    private boolean serializeNulls = false;

    /**
     * Configures Gson to exclude all fields from consideration for serialization or deserialization
     * that do not have the {@link com.google.gson.annotations.Expose} annotation.
     *
     * @see GsonBuilder#excludeFieldsWithoutExposeAnnotation()
     */
    private boolean excludeFieldsWithoutExposeAnnotation = false;

    /**
     * Configures Gson to excludes all class fields that have the specified modifiers. By default,
     * Gson will exclude all fields marked transient or static. This method will override that
     * behavior.
     *
     * @see GsonBuilder#excludeFieldsWithModifiers(int...)
     */
    private Set<FieldModifiers> excludeFieldsWithModifiers = DEFAULT_EXCLUDED_MODIFIERS;

    /**
     * Enabling this feature will only change the serialized form if the map key is a complex type (i.e.
     * non-primitive) in its <strong>serialized</strong> JSON form.
     *
     * @see GsonBuilder#enableComplexMapKeySerialization()
     */
    private boolean complexMapKeySerialization = false;

    /**
     * Makes the output JSON non-executable in Javascript by prefixing the generated JSON with some
     * special text.
     *
     * @see GsonBuilder#generateNonExecutableJson()
     */
    private boolean generateNonExecutableJson = false;

    /**
     * Use this option to configure Gson to pass-through HTML characters as is.
     *
     * @see GsonBuilder#disableHtmlEscaping()
     */
    private boolean escapeHtmlChars = true;

    /**
     * Configures Gson to output Json that fits in a page for pretty printing.
     *
     * @see GsonBuilder#setPrettyPrinting()
     */
    private boolean prettyPrinting = false;

    /**
     * This option makes the parser liberal in what it accepts.
     *
     * @see GsonBuilder#setLenient()
     */
    private boolean lenient = false;

    /**
     * This method provides a way to not throw exception when you have special floating values like
     * {@link Float#NaN}, {@link Float#POSITIVE_INFINITY}, {@link Float#NEGATIVE_INFINITY}, or a double
     * value {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}, {@link Double#NEGATIVE_INFINITY}.
     *
     * @see GsonBuilder#serializeSpecialFloatingPointValues()
     */
    private boolean serializeSpecialFloatingPointValues = false;

    /**
     * @return configuration with formatters {@link DateTimeFormatters}
     */
    public static GsonConfiguration of() {
        return new GsonConfiguration();
    }

    /**
     * @return configuration with Java default formatters {@link DateTimeFormatter}
     */
    public static GsonConfiguration ofJavaISO() {
        final GsonConfiguration configuration = new GsonConfiguration();

        configuration.setDateFormat(DateTimeFormatters.JAVA_ISO_DATE);
        configuration.setInstantFormat(DateTimeFormatter.ISO_INSTANT);
        configuration.setLocalDateFormat(DateTimeFormatter.ISO_LOCAL_DATE);
        configuration.setLocalTimeFormat(DateTimeFormatter.ISO_LOCAL_TIME);
        configuration.setLocalDateTimeFormat(DateTimeFormatter.ISO_DATE_TIME);
        configuration.setOffsetTimeFormat(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        configuration.setOffsetDateTimeFormat(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        configuration.setZonedDateTimeFormat(DateTimeFormatter.ISO_ZONED_DATE_TIME);

        return configuration;
    }

    public static GsonConfiguration ofPropertiesJavaISO(Properties properties) {
        return ofProperties(ofJavaISO(), properties);
    }

    public static GsonConfiguration ofProperties(Properties properties) {
        return ofProperties(of(), properties);
    }

    private static GsonConfiguration ofProperties(GsonConfiguration configuration, Properties properties) {
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

        final String forceIsoChronologyProp = properties.getProperty(GsonProperties.FORCE_ISO_CHRONOLOGY);
        final String forceResolverStrictProp = properties.getProperty(GsonProperties.FORCE_RESOLVER_STRICT);

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
        final String excludeFieldsWithoutExposeAnnotation = properties
                .getProperty(GsonProperties.EXCLUDE_FIELDS_WITHOUT_EXPOSE_ANNOTATION);
        final String excludeFieldsWithModifiers = properties
                .getProperty(GsonProperties.EXCLUDE_FIELDS_WITH_MODIFIERS);

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

        if (forceIsoChronologyProp != null)
            configuration.setForceIsoChronology(Boolean.parseBoolean(forceIsoChronologyProp));
        if (forceResolverStrictProp != null)
            configuration.setForceResolverStrict(Boolean.parseBoolean(forceResolverStrictProp));

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
        if (excludeFieldsWithoutExposeAnnotation != null)
            configuration.setExcludeFieldsWithoutExposeAnnotation(Boolean.parseBoolean(excludeFieldsWithoutExposeAnnotation));
        if (excludeFieldsWithModifiers != null) {
            final Set<FieldModifiers> modifiers = Arrays.stream(excludeFieldsWithModifiers.split(","))
                    .map(FieldModifiers::valueOf)
                    .collect(Collectors.toSet());

            configuration.setExcludeFieldsWithModifiers(modifiers);
        }

        return configuration;
    }

    public GsonBuilder builder() {
        final GsonBuilder builder = GsonAdapterBuilder.builder(this)
                .setDateFormat(getDateFormat())
                .setLongSerializationPolicy(getLongSerializationPolicy())
                .setFieldNamingPolicy(getFieldNamingPolicy());

        final int[] modifiers = getExcludeFieldsWithModifiers().stream()
                .mapToInt(m -> m.modifier)
                .toArray();

        builder.excludeFieldsWithModifiers(modifiers);

        if (isExcludeFieldsWithoutExposeAnnotation())
            builder.excludeFieldsWithoutExposeAnnotation();
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

    public boolean isForceResolverStrict() {
        return forceResolverStrict;
    }

    /**
     * Forces {@link java.time.format.ResolverStyle#STRICT} for all formatters setters
     */
    public GsonConfiguration setForceResolverStrict(boolean forceResolverStrict) {
        this.forceResolverStrict = forceResolverStrict;
        return this;
    }

    public boolean isForceIsoChronology() {
        return forceIsoChronology;
    }

    /**
     * Forces {@link IsoChronology#INSTANCE} for all formatters setters
     */
    public GsonConfiguration setForceIsoChronology(boolean forceIsoChronology) {
        this.forceIsoChronology = forceIsoChronology;
        return this;
    }

    public FieldNamingPolicy getFieldNamingPolicy() {
        return fieldNamingPolicy;
    }

    /**
     * Configures Gson to apply a specific naming policy to an object's field during serialization and
     * deserialization.
     * 
     * @param fieldNamingPolicy to set
     * @return self
     */
    public GsonConfiguration setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        if (fieldNamingPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.fieldNamingPolicy = fieldNamingPolicy;
        return this;
    }

    public LongSerializationPolicy getLongSerializationPolicy() {
        return longSerializationPolicy;
    }

    /**
     * Configures Gson to apply a specific serialization policy for {@code Long} and {@code long}
     * objects.
     *
     * @param longSerializationPolicy policy to set
     * @return self
     */
    public GsonConfiguration setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == null)
            throw new IllegalArgumentException("Policy can not be nullable!");
        this.longSerializationPolicy = longSerializationPolicy;
        return this;
    }

    public boolean isSerializeNulls() {
        return serializeNulls;
    }

    /**
     * Configure Gson to serialize null fields. By default, Gson omits all fields that are null during
     * serialization.
     *
     * @param serializeNulls true if serialize
     * @return self
     */
    public GsonConfiguration setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
        return this;
    }

    public boolean isComplexMapKeySerialization() {
        return complexMapKeySerialization;
    }

    /**
     * Enabling this feature will only change the serialized form if the map key is a complex type (i.e.
     * non-primitive) in its <strong>serialized</strong> JSON form.
     *
     * @param complexMapKeySerialization to set
     * @return self
     */
    public GsonConfiguration setComplexMapKeySerialization(boolean complexMapKeySerialization) {
        this.complexMapKeySerialization = complexMapKeySerialization;
        return this;
    }

    public boolean isGenerateNonExecutableJson() {
        return generateNonExecutableJson;
    }

    /**
     * Makes the output JSON non-executable in Javascript by prefixing the generated JSON with some
     * special text.
     *
     * @param generateNonExecutableJson to set
     * @return self
     */
    public GsonConfiguration setGenerateNonExecutableJson(boolean generateNonExecutableJson) {
        this.generateNonExecutableJson = generateNonExecutableJson;
        return this;
    }

    public boolean isEscapeHtmlChars() {
        return escapeHtmlChars;
    }

    /**
     * Use this option to configure Gson to pass-through HTML characters as is.
     *
     * @param escapeHtmlChars to set
     * @return self
     */
    public GsonConfiguration setEscapeHtmlChars(boolean escapeHtmlChars) {
        this.escapeHtmlChars = escapeHtmlChars;
        return this;
    }

    public boolean isPrettyPrinting() {
        return prettyPrinting;
    }

    /**
     * Configures Gson to output Json that fits in a page for pretty printing.
     *
     * @param prettyPrinting to set
     * @return self
     */
    public GsonConfiguration setPrettyPrinting(boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
        return this;
    }

    public boolean isLenient() {
        return lenient;
    }

    /**
     * This option makes the parser liberal in what it accepts.
     *
     * @param lenient to set
     * @return self
     */
    public GsonConfiguration setLenient(boolean lenient) {
        this.lenient = lenient;
        return this;
    }

    public boolean isSerializeSpecialFloatingPointValues() {
        return serializeSpecialFloatingPointValues;
    }

    /**
     * This method provides a way to not throw exception when you have special floating values like
     * {@link Float#NaN}, {@link Float#POSITIVE_INFINITY}, {@link Float#NEGATIVE_INFINITY}, or a double
     * value {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}, {@link Double#NEGATIVE_INFINITY}.
     *
     * @param serializeSpecialFloatingPointValues to set
     * @return self
     */
    public GsonConfiguration setSerializeSpecialFloatingPointValues(boolean serializeSpecialFloatingPointValues) {
        this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
        return this;
    }

    public boolean isExcludeFieldsWithoutExposeAnnotation() {
        return excludeFieldsWithoutExposeAnnotation;
    }

    /**
     * Configures Gson to exclude all fields from consideration for serialization or deserialization
     * that do not have the {@link com.google.gson.annotations.Expose} annotation.
     *
     * @see GsonBuilder#excludeFieldsWithoutExposeAnnotation()
     * @param excludeFieldsWithoutExposeAnnotation set true to exclude
     * @return self
     */
    public GsonConfiguration setExcludeFieldsWithoutExposeAnnotation(boolean excludeFieldsWithoutExposeAnnotation) {
        this.excludeFieldsWithoutExposeAnnotation = excludeFieldsWithoutExposeAnnotation;
        return this;
    }

    public Set<FieldModifiers> getExcludeFieldsWithModifiers() {
        return excludeFieldsWithModifiers;
    }

    /**
     * Configures Gson to excludes all class fields that have the specified modifiers. By default,
     * Gson will exclude all fields marked transient or static. This method will override that
     * behavior.
     *
     * @see GsonBuilder#excludeFieldsWithModifiers(int...)
     * @param excludeFieldsWithModifiers fields modifiers to exclude
     * @return self
     */
    public GsonConfiguration setExcludeFieldsWithModifiers(Set<FieldModifiers> excludeFieldsWithModifiers) {
        this.excludeFieldsWithModifiers = Collections.unmodifiableSet(excludeFieldsWithModifiers);
        return this;
    }

    /**
     * Configures Gson to excludes all class fields that have the specified modifiers. By default,
     * Gson will exclude all fields marked transient or static. This method will override that
     * behavior.
     *
     * @see GsonBuilder#excludeFieldsWithModifiers(int...)
     * @param excludeFieldsWithModifiers fields modifiers to exclude
     * @return self
     */
    public GsonConfiguration setExcludeFieldsWithModifiers(FieldModifiers... excludeFieldsWithModifiers) {
        this.excludeFieldsWithModifiers = Collections.unmodifiableSet(Arrays.stream(excludeFieldsWithModifiers)
                .collect(Collectors.toSet()));

        return this;
    }

    public DateTimeFormatter getInstantFormat() {
        return instantFormat;
    }

    public GsonConfiguration setInstantFormat(DateTimeFormatter instantFormat) {
        this.instantFormat = applyRestrictions(instantFormat);
        return this;
    }

    public GsonConfiguration setInstantFormat(String instantPattern) {
        return setInstantFormat(DateTimeFormatter.ofPattern(instantPattern));
    }

    public DateTimeFormatter getLocalDateFormat() {
        return localDateFormat;
    }

    public GsonConfiguration setLocalDateFormat(DateTimeFormatter localDateFormat) {
        this.localDateFormat = applyRestrictions(localDateFormat);
        return this;
    }

    public GsonConfiguration setLocalDateFormat(String localDatePattern) {
        return setLocalDateFormat(DateTimeFormatter.ofPattern(localDatePattern));
    }

    public DateTimeFormatter getLocalTimeFormat() {
        return localTimeFormat;
    }

    public GsonConfiguration setLocalTimeFormat(DateTimeFormatter localTimeFormat) {
        this.localTimeFormat = applyRestrictions(localTimeFormat);
        return this;
    }

    public GsonConfiguration setLocalTimeFormat(String localTimePattern) {
        return setLocalTimeFormat(DateTimeFormatter.ofPattern(localTimePattern));
    }

    public DateTimeFormatter getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public GsonConfiguration setLocalDateTimeFormat(DateTimeFormatter localDateTimeFormat) {
        this.localDateTimeFormat = applyRestrictions(localDateTimeFormat);
        return this;
    }

    public GsonConfiguration setLocalDateTimeFormat(String localDateTimePattern) {
        return setLocalDateTimeFormat(DateTimeFormatter.ofPattern(localDateTimePattern));
    }

    public DateTimeFormatter getOffsetTimeFormat() {
        return offsetTimeFormat;
    }

    public GsonConfiguration setOffsetTimeFormat(DateTimeFormatter offsetTimeFormat) {
        this.offsetTimeFormat = applyRestrictions(offsetTimeFormat);
        return this;
    }

    public GsonConfiguration setOffsetTimeFormat(String offsetTimePattern) {
        return setOffsetTimeFormat(DateTimeFormatter.ofPattern(offsetTimePattern));
    }

    public DateTimeFormatter getOffsetDateTimeFormat() {
        return offsetDateTimeFormat;
    }

    public GsonConfiguration setOffsetDateTimeFormat(DateTimeFormatter offsetDateTimeFormat) {
        this.offsetDateTimeFormat = applyRestrictions(offsetDateTimeFormat);
        return this;
    }

    public GsonConfiguration setOffsetDateTimeFormat(String offsetDateTimePattern) {
        return setOffsetDateTimeFormat(DateTimeFormatter.ofPattern(offsetDateTimePattern));
    }

    public DateTimeFormatter getZonedDateTimeFormat() {
        return zonedDateTimeFormat;
    }

    public GsonConfiguration setZonedDateTimeFormat(DateTimeFormatter zonedDateTimeFormat) {
        this.zonedDateTimeFormat = applyRestrictions(zonedDateTimeFormat);
        return this;
    }

    public GsonConfiguration setZonedDateTimeFormat(String zonedDateTimePattern) {
        return setZonedDateTimeFormat(DateTimeFormatter.ofPattern(zonedDateTimePattern));
    }

    public DateTimeFormatter getYearFormat() {
        return yearFormat;
    }

    public GsonConfiguration setYearFormat(DateTimeFormatter yearFormat) {
        this.yearFormat = applyRestrictions(yearFormat);
        return this;
    }

    public GsonConfiguration setYearFormat(String yearPattern) {
        return setYearFormat(DateTimeFormatter.ofPattern(yearPattern));
    }

    public DateTimeFormatter getYearMonthFormat() {
        return yearMonthFormat;
    }

    public GsonConfiguration setYearMonthFormat(DateTimeFormatter yearMonthFormat) {
        this.yearMonthFormat = applyRestrictions(yearMonthFormat);
        return this;
    }

    public GsonConfiguration setYearMonthFormat(String yearMonthPattern) {
        return setYearMonthFormat(DateTimeFormatter.ofPattern(yearMonthPattern));
    }

    public DateTimeFormatter getMonthDayFormat() {
        return monthDayFormat;
    }

    public GsonConfiguration setMonthDayFormat(DateTimeFormatter monthDayFormat) {
        this.monthDayFormat = applyRestrictions(monthDayFormat);
        return this;
    }

    public GsonConfiguration setMonthDayFormat(String monthDayPattern) {
        return setMonthDayFormat(DateTimeFormatter.ofPattern(monthDayPattern));
    }

    private DateTimeFormatter applyRestrictions(DateTimeFormatter formatter) {
        DateTimeFormatter processedFormatter = formatter;
        if (forceIsoChronology)
            processedFormatter = processedFormatter.withChronology(IsoChronology.INSTANCE);
        if (forceResolverStrict)
            processedFormatter = processedFormatter.withResolverStyle(ResolverStyle.STRICT);
        return processedFormatter;
    }
}
