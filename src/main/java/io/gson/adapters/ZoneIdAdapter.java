package io.gson.adapters;

import io.gson.adapters.config.GsonConfiguration;

import java.time.format.DateTimeFormatter;

/**
 * Description.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class ZoneIdAdapter {

    private final DateTimeFormatter formatter;

    public ZoneIdAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public ZoneIdAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
