package io.goodforgod.gson.configuration.deserializer;

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
 * @since 18.02.2023
 */
final class DateTimeDeserializerFormatters {

    private DateTimeDeserializerFormatters() {}

    /**
     * uuuu
     */
    static final DateTimeFormatter ISO_YEAR = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 4, SignStyle.EXCEEDS_PAD)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * uuuu-MM
     */
    static final DateTimeFormatter ISO_YEAR_MONTH = new DateTimeFormatterBuilder()
            .append(ISO_YEAR)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * MM-dd
     */
    static final DateTimeFormatter ISO_MONTH_DAY = new DateTimeFormatterBuilder()
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT)
            .withChronology(IsoChronology.INSTANCE);

    /**
     * @see DateTimeFormatter#ISO_INSTANT
     */
    static final DateTimeFormatter ISO_INSTANT = DateTimeFormatter.ISO_INSTANT;

    /**
     * uuuu-MM-dd
     */
    static final DateTimeFormatter ISO_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * HH:mm:ss[.SSS]
     */
    static final DateTimeFormatter ISO_LOCAL_TIME = DateTimeFormatter.ISO_TIME;

    /**
     * uuuu-MM-dd'T'HH:mm:ss[.SSS]
     */
    static final DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * HH:mm:ss[.SSS]XXX
     */
    static final DateTimeFormatter ISO_OFFSET_TIME = DateTimeFormatter.ISO_OFFSET_TIME;

    /**
     * uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX
     */
    static final DateTimeFormatter ISO_OFFSET_DATE_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX['['VV']']
     */
    static final DateTimeFormatter ISO_ZONED_DATE_TIME = DateTimeFormatter.ISO_ZONED_DATE_TIME;
}
