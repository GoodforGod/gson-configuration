package io.goodforgod.gson.configuration;

import com.google.gson.GsonBuilder;
import io.goodforgod.gson.configuration.deserializer.*;
import io.goodforgod.gson.configuration.serializer.*;
import java.time.*;

/**
 * @see com.google.gson.GsonBuilder
 * @author Anton Kurako (GoodforGod)
 * @since 28.04.2021
 */
public final class GsonAdapterBuilder {

    private GsonAdapterBuilder() {}

    public static GsonBuilder builder() {
        return new GsonBuilder()
                .setDateFormat(GsonConfiguration.DATE_ISO_8601_PATTERN)
                .registerTypeAdapter(DayOfWeek.class, new DayOfWeekDeserializer())
                .registerTypeAdapter(DayOfWeek.class, new DayOfWeekSerializer())
                .registerTypeAdapter(Month.class, new MonthDeserializer())
                .registerTypeAdapter(Month.class, new MonthSerializer())
                .registerTypeAdapter(Year.class, new YearDeserializer())
                .registerTypeAdapter(Year.class, new YearSerializer())
                .registerTypeAdapter(ZoneId.class, new ZoneIdDeserializer())
                .registerTypeAdapter(ZoneId.class, new ZoneIdSerializer())
                .registerTypeAdapter(Instant.class, new InstantDeserializer())
                .registerTypeAdapter(Instant.class, new InstantSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeDeserializer())
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeSerializer())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeDeserializer())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
    }
}
