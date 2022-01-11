package io.goodforgod.gson.configuration;

import static java.time.temporal.ChronoField.*;

import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;

/**
 * ISO8601 formats and patterns
 *
 * @author Anton Kurako (GoodforGod)
 * @since 05.11.2021
 */
public final class DateTimeFormatters {

    private DateTimeFormatters() {}

    /**
     * uuuu
     */
    public static final DateTimeFormatter ISO_YEAR = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 4, SignStyle.EXCEEDS_PAD)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM
     */
    public static final DateTimeFormatter ISO_YEAR_MONTH = new DateTimeFormatterBuilder()
            .append(ISO_YEAR)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * MM-dd
     */
    public static final DateTimeFormatter ISO_MONTH_DAY = new DateTimeFormatterBuilder()
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * @see DateTimeFormatter#ISO_INSTANT
     */
    public static final DateTimeFormatter ISO_INSTANT = DateTimeFormatter.ISO_INSTANT;

    /**
     * uuuu-MM-dd
     */
    public static final DateTimeFormatter ISO_LOCAL_DATE = new DateTimeFormatterBuilder()
            .append(ISO_YEAR_MONTH)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * HH:mm:ss.SSS
     */
    public static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(NANO_OF_SECOND, 3, 3, true)
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSS
     */
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE)
            .appendLiteral('T')
            .append(ISO_LOCAL_TIME)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter ISO_OFFSET_TIME = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_TIME)
            .appendOffsetId()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .appendOffsetId()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX[VV]
     */
    public static final DateTimeFormatter ISO_ZONED_DATE_TIME = new DateTimeFormatterBuilder()
            .append(ISO_OFFSET_DATE_TIME)
            .optionalStart()
            .appendLiteral('[')
            .parseCaseSensitive()
            .appendZoneRegionId()
            .appendLiteral(']')
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp}
     */
    public static final String ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp} analog to default Java ISO
     * {@link DateTimeFormatter}
     */
    public static final String ISO_DATE_JAVA = "yyyy-MM-dd'T'HH:mm:ssXXX";
}
