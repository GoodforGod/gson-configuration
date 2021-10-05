# Gson Configuration

[![GitHub Action](https://github.com/goodforgod/gson-datetime-adapters/workflows/Java%20CI/badge.svg)](https://github.com/GoodforGod/gson-datetime-adapters/actions?query=workflow%3A%22Java+CI%22)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-datetime-adapters&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-datetime-adapters)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-datetime-adapters&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-datetime-adapters)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-datetime-adapters&metric=ncloc)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-datetime-adapters)

Gson configuration and serializers/deserializers for Date/Time in [java.time.*](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html) package.

## Dependency :rocket:
**Gradle**
```groovy
dependencies {
    implementation "io.goodforgod:gson-configuration:1.0.0"
}
```

**Maven**
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>gson-configuration</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Supported Adapters

Library include adapters for most [java.time.*](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html)
datetime objects, supported list:
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

## Gson Configuration

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

### Properties file

GsonConfiguration also can be filled from *properties* file.

How to build GsonConfiguration from Properties:
```java
final InputStream resource = getClass().getClassLoader().getResourceAsStream("gson.properties");
final Properties properties = new Properties();
properties.load(resource);

final GsonConfiguration configuration = GsonConfiguration.ofProperties(properties);
```

Full list of properties ([check GsonProperties](https://github.com/GoodforGod/gson-datetime-adapters/blob/master/src/main/java/io/gson/adapters/config/GsonProperties.java)):
```properties
gson.format.instant=yyyy-MM-dd'T'HH:mm:ssX
gson.format.localDate=yyyy-MM-dd
gson.format.localTime=HH:mm:ss
gson.format.localDateTime=yyyy-MM-dd'T'HH:mm:ss
gson.format.offsetTime=HH:mm:ss.SSSXXX
gson.format.offsetDateTime=yyyy-MM-dd'T'HH:mm:ss.SSSXXX
gson.format.zonedDateTime=yyyy-MM-dd'T'HH:mm:ss.SSSXXX[VV]
gson.format.year=yyyy
gson.format.date=yyyy-MM-dd'T'HH:mm:ss.SSSXXX

gson.lenient=true
gson.serializeNulls=true
gson.prettyPrinting=true
gson.escapeHtmlChars=false
gson.generateNonExecutableJson=true
gson.serializeComplexMapKey=true
gson.serializeSpecialFloatingPointValues=true

gson.policy.fieldNaming=UPPER_CAMEL_CASE
gson.policy.longSerialization=STRING
```

## Gson Builder

All adapters already registered via when using *GsonConfiguration#builder*.

If you want to register only adapters without configuration:

```java
GsonBuilder builder = GsonAdapters.builder();
```

You can register them manually:
```java
GsonBuilder builder = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
```

You can register with custom formatter also:
```java
GsonBuilder builder = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter(DateTimeFormatter.ISO_LOCAL_DATE))
```

## License

This project licensed under the MIT - see the [LICENSE](LICENSE) file for details.
