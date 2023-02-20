package io.goodforgod.gson.configuration;

import com.google.gson.GsonBuilder;
import io.goodforgod.gson.configuration.deserializer.*;
import io.goodforgod.gson.configuration.serializer.*;
import java.time.*;

/**
 * @author Anton Kurako (GoodforGod)
 * @see com.google.gson.GsonBuilder
 * @since 28.04.2021
 */
final class GsonAdapterBuilder {

    private GsonAdapterBuilder() {}

    private static GsonBuilder getCommonBuilder() {
        return new GsonBuilder()
                .setDateFormat(DateFormatters.ISO_DATE)
                .registerTypeAdapter(DayOfWeek.class, DayOfWeekDeserializer.INSTANCE)
                .registerTypeAdapter(DayOfWeek.class, DayOfWeekSerializer.INSTANCE)
                .registerTypeAdapter(Month.class, MonthDeserializer.INSTANCE)
                .registerTypeAdapter(Month.class, MonthSerializer.INSTANCE)
                .registerTypeHierarchyAdapter(ZoneId.class, ZoneIdDeserializer.INSTANCE)
                .registerTypeHierarchyAdapter(ZoneId.class, ZoneIdSerializer.INSTANCE)
                .registerTypeAdapter(ZoneOffset.class, ZoneOffsetDeserializer.INSTANCE)
                .registerTypeAdapter(ZoneOffset.class, ZoneOffsetSerializer.INSTANCE);
    }

    static GsonBuilder builder(GsonConfiguration configuration) {
        return getCommonBuilder()
                .setDateFormat(configuration.getDateFormat())
                .registerTypeAdapter(Year.class, (configuration.getYearFormat() == null)
                        ? YearDeserializer.INSTANCE
                        : new YearDeserializer(configuration.getYearFormat()))
                .registerTypeAdapter(Year.class, (configuration.getYearFormat() == null)
                        ? YearSerializer.INSTANCE
                        : new YearSerializer(configuration.getYearFormat()))
                .registerTypeAdapter(YearMonth.class, (configuration.getYearMonthFormat() == null)
                        ? YearMonthDeserializer.INSTANCE
                        : new YearMonthDeserializer(configuration.getYearMonthFormat()))
                .registerTypeAdapter(YearMonth.class, (configuration.getYearMonthFormat() == null)
                        ? YearMonthSerializer.INSTANCE
                        : new YearMonthSerializer(configuration.getYearMonthFormat()))
                .registerTypeAdapter(MonthDay.class, (configuration.getMonthDayFormat() == null)
                        ? MonthDayDeserializer.INSTANCE
                        : new MonthDayDeserializer(configuration.getMonthDayFormat()))
                .registerTypeAdapter(MonthDay.class, (configuration.getMonthDayFormat() == null)
                        ? MonthDaySerializer.INSTANCE
                        : new MonthDaySerializer(configuration.getMonthDayFormat()))
                .registerTypeAdapter(Instant.class, (configuration.getInstantFormat() == null)
                        ? InstantDeserializer.INSTANCE
                        : new InstantDeserializer(configuration.getInstantFormat()))
                .registerTypeAdapter(Instant.class, (configuration.getInstantFormat() == null)
                        ? InstantSerializer.INSTANCE
                        : new InstantSerializer(configuration.getInstantFormat()))
                .registerTypeAdapter(LocalDate.class, (configuration.getLocalDateFormat() == null)
                        ? LocalDateDeserializer.INSTANCE
                        : new LocalDateDeserializer(configuration.getLocalDateFormat()))
                .registerTypeAdapter(LocalDate.class, (configuration.getLocalDateFormat() == null)
                        ? LocalDateSerializer.INSTANCE
                        : new LocalDateSerializer(configuration.getLocalDateFormat()))
                .registerTypeAdapter(LocalTime.class, (configuration.getLocalTimeFormat() == null)
                        ? LocalTimeDeserializer.INSTANCE
                        : new LocalTimeDeserializer(configuration.getLocalTimeFormat()))
                .registerTypeAdapter(LocalTime.class, (configuration.getLocalTimeFormat() == null)
                        ? LocalTimeSerializer.INSTANCE
                        : new LocalTimeSerializer(configuration.getLocalTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, (configuration.getLocalDateTimeFormat() == null)
                        ? LocalDateTimeDeserializer.INSTANCE
                        : new LocalDateTimeDeserializer(configuration.getLocalDateTimeFormat()))
                .registerTypeAdapter(LocalDateTime.class, (configuration.getLocalDateTimeFormat() == null)
                        ? LocalDateTimeSerializer.INSTANCE
                        : new LocalDateTimeSerializer(configuration.getLocalDateTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, (configuration.getOffsetTimeFormat() == null)
                        ? OffsetTimeDeserializer.INSTANCE
                        : new OffsetTimeDeserializer(configuration.getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetTime.class, (configuration.getOffsetTimeFormat() == null)
                        ? OffsetTimeSerializer.INSTANCE
                        : new OffsetTimeSerializer(configuration.getOffsetTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, (configuration.getOffsetDateTimeFormat() == null)
                        ? OffsetDateTimeDeserializer.INSTANCE
                        : new OffsetDateTimeDeserializer(configuration.getOffsetDateTimeFormat()))
                .registerTypeAdapter(OffsetDateTime.class, (configuration.getOffsetDateTimeFormat() == null)
                        ? OffsetDateTimeSerializer.INSTANCE
                        : new OffsetDateTimeSerializer(configuration.getOffsetDateTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, (configuration.getZonedDateTimeFormat() == null)
                        ? ZonedDateTimeDeserializer.INSTANCE
                        : new ZonedDateTimeDeserializer(configuration.getZonedDateTimeFormat()))
                .registerTypeAdapter(ZonedDateTime.class, (configuration.getZonedDateTimeFormat() == null)
                        ? ZonedDateTimeSerializer.INSTANCE
                        : new ZonedDateTimeSerializer(configuration.getZonedDateTimeFormat()));
    }
}
