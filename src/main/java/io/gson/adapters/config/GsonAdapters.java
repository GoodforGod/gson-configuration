package io.gson.adapters.config;

import com.google.gson.GsonBuilder;
import io.gson.adapters.*;

import java.time.*;

/**
 * @see com.google.gson.GsonBuilder
 * @author Anton Kurako (GoodforGod)
 * @since 28.04.2021
 */
public class GsonAdapters {

    private GsonAdapters() {}

    public static GsonBuilder builder() {
        return new GsonBuilder()
                .setDateFormat(GsonConfiguration.ISO_8601_FORMATTER)
                .registerTypeAdapter(DayOfWeek.class, new DayOfWeekAdapter())
                .registerTypeAdapter(Month.class, new MonthAdapter())
                .registerTypeAdapter(Year.class, new YearAdapter())
                .registerTypeAdapter(ZoneId.class, new ZoneIdAdapter())
                .registerTypeAdapter(Instant.class, new InstantAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeAdapter())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter());
    }
}
