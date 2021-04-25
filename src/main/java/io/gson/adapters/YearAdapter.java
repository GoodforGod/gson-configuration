package io.gson.adapters;

import io.gson.adapters.config.GsonConfiguration;

import java.time.format.DateTimeFormatter;

/**
 * Description.
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.04.2021
 */
public class YearAdapter {

    private final DateTimeFormatter formatter;

    public YearAdapter() {
        this(DateTimeFormatter.ofPattern(GsonConfiguration.ISO_8601_FORMATTER));
    }

    public YearAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
