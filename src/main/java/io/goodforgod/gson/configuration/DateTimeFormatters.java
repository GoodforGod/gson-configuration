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
    public static final DateTimeFormatter YEAR_ISO = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM
     */
    public static final DateTimeFormatter YEAR_MONTH_ISO = new DateTimeFormatterBuilder()
            .append(YEAR_ISO)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * MM-dd
     */
    public static final DateTimeFormatter MONTH_DAY_ISO = new DateTimeFormatterBuilder()
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * @see DateTimeFormatter#ISO_INSTANT
     */
    public static final DateTimeFormatter INSTANT_ISO = DateTimeFormatter.ISO_INSTANT;

    /**
     * uuuu-MM-dd
     *
     * @see DateTimeFormatter#ISO_LOCAL_DATE
     */
    public static final DateTimeFormatter LOCAL_DATE_ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * HH:mm:ss.SSS
     */
    public static final DateTimeFormatter LOCAL_TIME_ISO = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSS
     */
    public static final DateTimeFormatter LOCAL_DATE_TIME_ISO = new DateTimeFormatterBuilder()
            .append(LOCAL_DATE_ISO)
            .appendLiteral('T')
            .append(LOCAL_TIME_ISO)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter OFFSET_TIME_ISO = new DateTimeFormatterBuilder()
            .append(LOCAL_TIME_ISO)
            .appendPattern("XXX")
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter OFFSET_DATE_TIME_ISO = new DateTimeFormatterBuilder()
            .append(LOCAL_DATE_TIME_ISO)
            .appendPattern("XXX")
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX[VV]
     */
    public static final DateTimeFormatter ZONED_DATE_TIME_ISO = new DateTimeFormatterBuilder()
            .append(OFFSET_DATE_TIME_ISO)
            .optionalStart()
            .appendLiteral('[')
            .parseCaseSensitive()
            .appendZoneRegionId()
            .appendLiteral(']')
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp}
     */
    public static final String DATE_ISO = "uuuu-MM-dd'T'HH:mm:ss.SSSXXX";
}
