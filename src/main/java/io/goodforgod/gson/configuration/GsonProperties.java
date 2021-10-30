package io.goodforgod.gson.configuration;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 01.10.2021
 */
public interface GsonProperties {

    String PREFIX = "gson.";

    String FORMAT_INSTANT = PREFIX + "format.instant";
    String FORMAT_LOCAL_DATE = PREFIX + "format.localDate";
    String FORMAT_LOCAL_TIME = PREFIX + "format.localTime";
    String FORMAT_LOCAL_DATE_TIME = PREFIX + "format.localDateTime";
    String FORMAT_OFFSET_TIME = PREFIX + "format.offsetTime";
    String FORMAT_OFFSET_DATE_TIME = PREFIX + "format.offsetDateTime";
    String FORMAT_ZONED_DATE_TIME = PREFIX + "format.zonedDateTime";
    String FORMAT_YEAR = PREFIX + "format.year";
    String FORMAT_DATE = PREFIX + "format.date";

    String LENIENT = PREFIX + "lenient";
    String SERIALIZE_NULLS = PREFIX + "serializeNulls";
    String PRETTY_PRINTING = PREFIX + "prettyPrinting";
    String ESCAPE_HTML_CHARS = PREFIX + "escapeHtmlChars";
    String GENERATE_NON_EXECUTABLE_JSON = PREFIX + "generateNonExecutableJson";
    String COMPLEX_MAP_KEY_SERIALIZATION = PREFIX + "serializeComplexMapKey";
    String SERIALIZE_SPECIAL_FLOATING_POINT_VALUES = PREFIX + "serializeSpecialFloatingPointValues";

    String POLICY_FIELD_NAMING = PREFIX + "policy.fieldNaming";
    String POLICY_LONG_SERIALIZATION = PREFIX + "policy.longSerialization";
}
