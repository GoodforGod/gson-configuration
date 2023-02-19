package io.goodforgod.gson.configuration;

/**
 * ISO8601 formats and patterns
 *
 * @author Anton Kurako (GoodforGod)
 * @since 18.02.2023
 */
final class DateFormatters {

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp}
     */
    static final String ISO_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * ISO8601 for {@link java.util.Date} and {@link java.sql.Timestamp} as default Java ISO
     */
    static final String ISO_DATE_JAVA = "yyyy-MM-dd'T'HH:mm:ssXXX";

    private DateFormatters() {}
}
