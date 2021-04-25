package io.gson.adapters;

import io.gson.adapters.config.GsonConfiguration;

import java.time.format.DateTimeFormatter;

/**
 * Description.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZonedDateTimeAdapter {

    private final DateTimeFormatter formatter;

    public ZonedDateTimeAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public ZonedDateTimeAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
