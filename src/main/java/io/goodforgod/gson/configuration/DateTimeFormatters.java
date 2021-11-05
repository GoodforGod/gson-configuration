package io.goodforgod.gson.configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * ISO8601 formats and patterns
 *
 * @author Anton Kurako (GoodforGod)
 * @since 05.11.2021
 */
public final class DateTimeFormatters {

    private DateTimeFormatters() {}

    /**
     * uuuu-MM-dd'T'HH:mm:ssX
     */
    public static final DateTimeFormatter INSTANT_ISO = DateTimeFormatter.ISO_INSTANT;

    /**
     * uuuu-MM-dd
     */
    public static final DateTimeFormatter LOCAL_DATE_ISO = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    /**
     * HH:mm:ss.SSS
     */
    public static final DateTimeFormatter LOCAL_TIME_ISO = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSS
     */
    public static final DateTimeFormatter LOCAL_DATE_TIME_ISO = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");

    /**
     * HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter OFFSET_TIME_ISO = DateTimeFormatter.ofPattern("HH:mm:ss.SSSXXX");

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter OFFSET_DATE_TIME_ISO = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSXXX");

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX[VV]
     */
    public static final DateTimeFormatter ZONED_DATE_TIME_ISO = new DateTimeFormatterBuilder().append(OFFSET_DATE_TIME_ISO)
            .optionalStart()
            .appendLiteral('[')
            .parseCaseSensitive()
            .appendZoneRegionId()
            .appendLiteral(']')
            .toFormatter();

    /**
     * uuuu
     */
    public static final DateTimeFormatter YEAR_ISO = DateTimeFormatter.ofPattern("uuuu");

    /**
     * ISO 8601 for {@link java.util.Date}
     */
    public static final String DATE_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
}
