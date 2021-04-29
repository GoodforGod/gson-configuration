# Gson DateTime Adapters

Gson serializers\deserializers for Date\Time in package java.time.*

## Dependency :rocket:
**Gradle**
```groovy
dependencies {
    compile 'com.github.goodforgod:gson-datetime-adapters:1.0.0'
}
```

**Maven**
```xml
<dependency>
    <groupId>com.github.goodforgod</groupId>
    <artifactId>gson-datetime-adapters</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Supported DateTimes

Library include adapters for most java.time.* datetime objects, supported list:
- LocalDate
- LocalTime
- LocalDateTime
- Instant
- OffsetTime
- OffsetDateTime
- ZonedDateTime
- DayOfWeek
- Month
- Year
- ZoneId

All adapters register with **ISO8601** formatters by defaults, but you can register them manually with your formatter.

## Gson Builder

Registering all adapters via builder (register all possible adapters):
```java
GsonBuilder builder = GsonAdapters.builder();
```

You can register them manually:
```java
GsonBuilder builder = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
```

## Configuration

Library provides configuration for configuring *GsonBuilder* for most properties:

```java
final GsonBuilder builder = new GsonConfiguration()
        .setDateFormat(GsonConfiguration.ISO_8601_FORMATTER)
        .setInstantFormat("yyyy-MM-dd HH:mm:ss")
        .setComplexMapKeySerialization(true)
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .setLenient(true)
        .setEscapeHtmlChars(false)
        .setPrettyPrinting(true)
        .setGenerateNonExecutableJson(true)
        .setSerializeNulls(true)
        .setSerializeSpecialFloatingPointValues(true)
        .builder();
```

You can configure DateTimeFormatters for provided adapters:
```java
final GsonBuilder builder = new GsonConfiguration()
        .setInstantFormat("yyyy-MM-dd HH:mm:ss")
        .builder();
```

## License

This project licensed under the MIT - see the [LICENSE](LICENSE) file for details.
