package io.goodforgod.gson.configuration;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 01.10.2021
 */
public final class GsonProperties {

    private GsonProperties() {}

    public static final String PREFIX = "gson.";

    public static final String FORMAT_INSTANT = PREFIX + "format.instant";
    public static final String FORMAT_LOCAL_DATE = PREFIX + "format.localDate";
    public static final String FORMAT_LOCAL_TIME = PREFIX + "format.localTime";
    public static final String FORMAT_LOCAL_DATE_TIME = PREFIX + "format.localDateTime";
    public static final String FORMAT_OFFSET_TIME = PREFIX + "format.offsetTime";
    public static final String FORMAT_OFFSET_DATE_TIME = PREFIX + "format.offsetDateTime";
    public static final String FORMAT_ZONED_DATE_TIME = PREFIX + "format.zonedDateTime";
    public static final String FORMAT_YEAR = PREFIX + "format.year";
    public static final String FORMAT_YEAR_MONTH = PREFIX + "format.yearMonth";
    public static final String FORMAT_MONTH_DAY = PREFIX + "format.monthDay";
    public static final String FORMAT_DATE = PREFIX + "format.date";

    public static final String FORCE_ISO_CHRONOLOGY = PREFIX + "forceIsoChronology";
    public static final String FORCE_RESOLVER_STRICT = PREFIX + "forceResolverStrict";

    public static final String LENIENT = PREFIX + "lenient";
    public static final String SERIALIZE_NULLS = PREFIX + "serializeNulls";
    public static final String PRETTY_PRINTING = PREFIX + "prettyPrinting";
    public static final String ESCAPE_HTML_CHARS = PREFIX + "escapeHtmlChars";
    public static final String GENERATE_NON_EXECUTABLE_JSON = PREFIX + "generateNonExecutableJson";
    public static final String COMPLEX_MAP_KEY_SERIALIZATION = PREFIX + "serializeComplexMapKey";
    public static final String SERIALIZE_SPECIAL_FLOATING_POINT_VALUES = PREFIX + "serializeSpecialFloatingPointValues";
    public static final String EXCLUDE_FIELDS_WITHOUT_EXPOSE_ANNOTATION = PREFIX + "excludeFieldsWithoutExposeAnnotation";
    public static final String EXCLUDE_FIELDS_WITH_MODIFIERS = PREFIX + "excludeFieldsWithModifiers";

    public static final String POLICY_FIELD_NAMING = PREFIX + "policy.fieldNaming";
    public static final String POLICY_LONG_SERIALIZATION = PREFIX + "policy.longSerialization";
}
