# Gson Configuration

![GraalVM Enabled](https://img.shields.io/badge/GraalVM-Ready-orange?style=plastic)
[![GitHub Action](https://github.com/goodforgod/gson-configuration/workflows/Java%20CI/badge.svg)](https://github.com/GoodforGod/gson-configuration/actions?query=workflow%3A%22Java+CI%22)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-configuration&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-configuration)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-configuration&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-configuration)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_gson-configuration&metric=ncloc)](https://sonarcloud.io/dashboard?id=GoodforGod_gson-configuration)

Gson configuration and serializers/deserializers for Date/Time in [java.time.*](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html) package.

## Dependency :rocket:
**Gradle**
```groovy
dependencies {
    implementation "io.goodforgod:gson-configuration:1.1.0"
}
```

**Maven**
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>gson-configuration</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Serializers\Deserializers

Library include serializers & deserializers for most [java.time.*](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html)
datetime objects, supported list:
- Instant
- LocalDate
- LocalTime
- LocalDateTime
- OffsetTime
- OffsetDateTime
- ZonedDateTime
- Year
- YearMonth  
- Month
- MonthDay
- DayOfWeek
- ZoneId
- ZoneOffset

All adapters register with **ISO8601** formatters by defaults, but you can register them manually with your formatter.

## Patters and Formats



## Gson Configuration

Library provides configuration for configuring *GsonBuilder* for most properties:

```java
final GsonBuilder builder = new GsonConfiguration()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        .setInstantFormat("uuuu-MM-dd HH:mm:ss")
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
        .setInstantFormat("uuuu-MM-dd HH:mm:ss")
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

Full list of properties ([check GsonProperties](https://github.com/GoodforGod/gson-configuration/blob/master/src/main/java/io/gson/adapters/config/GsonProperties.java)):
```properties
gson.format.instant=uuuu-MM-dd'T'HH:mm:ssX
gson.format.localDate=uuuu-MM-dd
gson.format.localTime=HH:mm:ss.SSS
gson.format.localDateTime=uuuu-MM-dd'T'HH:mm:ss.SSS
gson.format.offsetTime=HH:mm:ss.SSSXXX
gson.format.offsetDateTime=uuuu-MM-dd'T'HH:mm:ss.SSSXXX
gson.format.zonedDateTime=uuuu-MM-dd'T'HH:mm:ss.SSSXXX
gson.format.year=uuuu
gson.format.yearMonth=uuuu-MM
gson.format.monthDay=MM-dd
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

#### Factory

Gson can also be instantiated via properties using *GsonFactory*.

*GsonFactory* is looking for property file in root *resource*: **gson.properties**

```java
Gson gson = new GsonFactory().build();
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
        .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
```

You can register with custom formatter also:
```java
GsonBuilder builder = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(DateTimeFormatters.LOCAL_DATE_ISO))
```

## License

This project licensed under the MIT - see the [LICENSE](LICENSE) file for details.
