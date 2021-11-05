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

    private static GsonBuilder getCommonBuilder() {
        return new GsonBuilder()
                .setDateFormat(DateTimeFormatters.DATE_ISO)
                .registerTypeAdapter(DayOfWeek.class, DayOfWeekDeserializer.INSTANCE)
                .registerTypeAdapter(DayOfWeek.class, DayOfWeekSerializer.INSTANCE)
                .registerTypeAdapter(Month.class, MonthDeserializer.INSTANCE)
                .registerTypeAdapter(Month.class, MonthSerializer.INSTANCE)
                .registerTypeAdapter(Year.class, YearDeserializer.INSTANCE)
                .registerTypeAdapter(Year.class, YearSerializer.INSTANCE)
                .registerTypeAdapter(ZoneId.class, ZoneIdDeserializer.INSTANCE)
                .registerTypeAdapter(ZoneId.class, ZoneIdSerializer.INSTANCE);
    }

    public static GsonBuilder builder() {
        return getCommonBuilder()
                .registerTypeAdapter(Instant.class, InstantDeserializer.INSTANCE)
                .registerTypeAdapter(Instant.class, InstantSerializer.INSTANCE)
                .registerTypeAdapter(LocalDate.class, LocalDateDeserializer.INSTANCE)
                .registerTypeAdapter(LocalDate.class, LocalDateSerializer.INSTANCE)
                .registerTypeAdapter(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                .registerTypeAdapter(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .registerTypeAdapter(OffsetTime.class, OffsetTimeDeserializer.INSTANCE)
                .registerTypeAdapter(OffsetTime.class, OffsetTimeSerializer.INSTANCE)
                .registerTypeAdapter(OffsetDateTime.class, OffsetDateTimeDeserializer.INSTANCE)
                .registerTypeAdapter(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE)
                .registerTypeAdapter(ZonedDateTime.class, ZonedDateTimeDeserializer.INSTANCE)
                .registerTypeAdapter(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE);
    }

    public static GsonBuilder builder(GsonConfiguration configuration) {
        return getCommonBuilder()
                .setDateFormat(configuration.getDateFormat())
                .registerTypeAdapter(Instant.class, new InstantDeserializer(configuration.getInstantFormat()))
                .registerTypeAdapter(Instant.class, new InstantSerializer(configuration.getInstantFormat()))
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(configuration.getLocalDateFormat()))
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(configuration.getLocalDateFormat()))
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer(configuration.getLocalTimeFormat()))
                .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer(configuration.getLocalTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateDeserializer(configuration.getLocalDateTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer(configuration.getLocalDateTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeDeserializer(configuration.getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, new OffsetTimeSerializer(configuration.getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeDeserializer(configuration.getOffsetDateTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer(configuration.getOffsetDateTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer(configuration.getZonedDateTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer(configuration.getZonedDateTimeFormat()));
    }
}
