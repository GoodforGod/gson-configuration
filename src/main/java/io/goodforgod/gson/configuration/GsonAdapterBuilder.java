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
        .setDateFormat(DateTimeFormatters.ISO_DATE)
        .registerTypeAdapter(DayOfWeek.class, DayOfWeekDeserializer.INSTANCE)
        .registerTypeAdapter(DayOfWeek.class, DayOfWeekSerializer.INSTANCE)
        .registerTypeAdapter(Month.class, MonthDeserializer.INSTANCE)
        .registerTypeAdapter(Month.class, MonthSerializer.INSTANCE)
        .registerTypeAdapter(ZoneId.class, ZoneIdDeserializer.INSTANCE)
        .registerTypeAdapter(ZoneId.class, ZoneIdSerializer.INSTANCE)
        .registerTypeAdapter(ZoneOffset.class, ZoneOffsetDeserializer.INSTANCE)
        .registerTypeAdapter(ZoneOffset.class, ZoneOffsetSerializer.INSTANCE);
  }

  public static GsonBuilder builder() {
    return getCommonBuilder()
        .registerTypeAdapter(Year.class, YearDeserializer.INSTANCE)
        .registerTypeAdapter(Year.class, YearSerializer.INSTANCE)
        .registerTypeAdapter(YearMonth.class, YearMonthDeserializer.INSTANCE)
        .registerTypeAdapter(YearMonth.class, YearMonthSerializer.INSTANCE)
        .registerTypeAdapter(MonthDay.class, MonthDayDeserializer.INSTANCE)
        .registerTypeAdapter(MonthDay.class, MonthDaySerializer.INSTANCE)
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
        .registerTypeAdapter(Year.class, new YearDeserializer(configuration.getYearFormat()))
        .registerTypeAdapter(Year.class, new YearSerializer(configuration.getYearFormat()))
        .registerTypeAdapter(
            YearMonth.class, new YearMonthDeserializer(configuration.getYearMonthFormat()))
        .registerTypeAdapter(
            YearMonth.class, new YearMonthSerializer(configuration.getYearMonthFormat()))
        .registerTypeAdapter(
            MonthDay.class, new MonthDayDeserializer(configuration.getMonthDayFormat()))
        .registerTypeAdapter(
            MonthDay.class, new MonthDaySerializer(configuration.getMonthDayFormat()))
        .registerTypeAdapter(
            Instant.class, new InstantDeserializer(configuration.getInstantFormat()))
        .registerTypeAdapter(Instant.class, new InstantSerializer(configuration.getInstantFormat()))
        .registerTypeAdapter(
            LocalDate.class, new LocalDateDeserializer(configuration.getLocalDateFormat()))
        .registerTypeAdapter(
            LocalDate.class, new LocalDateSerializer(configuration.getLocalDateFormat()))
        .registerTypeAdapter(
            LocalTime.class, new LocalTimeDeserializer(configuration.getLocalTimeFormat()))
        .registerTypeAdapter(
            LocalTime.class, new LocalTimeSerializer(configuration.getLocalTimeFormat()))
        .registerTypeAdapter(
            LocalDateTime.class,
            new LocalDateTimeDeserializer(configuration.getLocalDateTimeFormat()))
        .registerTypeAdapter(
            LocalDateTime.class,
            new LocalDateTimeSerializer(configuration.getLocalDateTimeFormat()))
        .registerTypeAdapter(
            OffsetTime.class, new OffsetTimeDeserializer(configuration.getOffsetTimeFormat()))
        .registerTypeAdapter(
            OffsetTime.class, new OffsetTimeSerializer(configuration.getOffsetTimeFormat()))
        .registerTypeAdapter(
            OffsetDateTime.class,
            new OffsetDateTimeDeserializer(configuration.getOffsetDateTimeFormat()))
        .registerTypeAdapter(
            OffsetDateTime.class,
            new OffsetDateTimeSerializer(configuration.getOffsetDateTimeFormat()))
        .registerTypeAdapter(
            ZonedDateTime.class,
            new ZonedDateTimeDeserializer(configuration.getZonedDateTimeFormat()))
        .registerTypeAdapter(
            ZonedDateTime.class,
            new ZonedDateTimeSerializer(configuration.getZonedDateTimeFormat()));
  }
}
