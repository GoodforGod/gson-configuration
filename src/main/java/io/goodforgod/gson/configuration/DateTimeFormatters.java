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
            .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
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
     *
     * @see DateTimeFormatter#ISO_LOCAL_DATE
     */
    public static final DateTimeFormatter ISO_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * HH:mm:ss.SSS
     */
    public static final DateTimeFormatter ISO_LOCAL_TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
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
            .appendPattern("XXX")
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * uuuu-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .appendPattern("XXX")
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
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp}
     */
    public static final String ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
}
