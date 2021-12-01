package io.goodforgod.gson.configuration;

import com.google.gson.Gson;
import java.time.*;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 28.04.2021
 */
class GsonAdapterBuilderTests extends Assertions {

  static class User {

    private final Date value = new Date(0);
    private final Instant instant = Instant.now();
    private final LocalTime localTime = LocalTime.now();
    private final LocalDate localDate = LocalDate.now();
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final OffsetTime offsetTime = OffsetTime.now();
    private final OffsetDateTime offsetDateTime = OffsetDateTime.now();
    private final ZonedDateTime zonedDateTime = ZonedDateTime.now();
    private final Month month = Month.APRIL;
    private final Year year = Year.of(2000);
    private final YearMonth yearMonth = YearMonth.of(2000, 1);
    private final MonthDay monthDay = MonthDay.of(1, 1);
    private final DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
    private final ZoneId zoneId = ZoneId.of("UTC");
    private final ZoneOffset zoneOffset = ZoneOffset.of("+02:00");

    public Date getValue() {
      return value;
    }

    public Instant getInstant() {
      return instant;
    }

    public LocalTime getLocalTime() {
      return localTime;
    }

    public LocalDate getLocalDate() {
      return localDate;
    }

    public LocalDateTime getLocalDateTime() {
      return localDateTime;
    }

    public OffsetTime getOffsetTime() {
      return offsetTime;
    }

    public OffsetDateTime getOffsetDateTime() {
      return offsetDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
      return zonedDateTime;
    }

    public Month getMonth() {
      return month;
    }

    public Year getYear() {
      return year;
    }

    public YearMonth getYearMonth() {
      return yearMonth;
    }

    public MonthDay getMonthDay() {
      return monthDay;
    }

    public DayOfWeek getDayOfWeek() {
      return dayOfWeek;
    }

    public ZoneId getZoneId() {
      return zoneId;
    }

    public ZoneOffset getZoneOffset() {
      return zoneOffset;
    }
  }

  @Test
  void complexSerializationForAllTimeValid() {
    final Gson gson = GsonAdapterBuilder.builder().create();
    assertNotNull(gson);

    final User user = new User();
    final String json = gson.toJson(user);
    assertTrue(json.contains("value"));
    assertTrue(json.contains("instant"));
    assertTrue(json.contains("localTime"));
    assertTrue(json.contains("localDate"));
    assertTrue(json.contains("localDateTime"));
    assertTrue(json.contains("offsetTime"));
    assertTrue(json.contains("offsetDateTime"));
    assertTrue(json.contains("zonedDateTime"));
    assertTrue(json.contains("dayOfWeek"));
    assertTrue(json.contains("month"));
    assertTrue(json.contains("year"));
    assertTrue(json.contains("yearMonth"));
    assertTrue(json.contains("monthDay"));
    assertTrue(json.contains("zoneId"));
    assertTrue(json.contains("zoneOffset"));
  }
}
