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
implementation "io.goodforgod:gson-configuration:1.4.2"
```

**Maven**
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>gson-configuration</artifactId>
    <version>1.4.2</version>
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

## Formatters

Gson Configuration by default comes with [ISO8601 with millis precision](https://goodforgod.dev/posts/2/)
(basically default Java ISO8601 formatters but with millis precision)

Here is list of such formatters:
- LocalDateTime - *uuuu-MM-dd'T'HH:mm:ss[.SSS]*
- LocalDate - *uuuu-MM-dd*
- LocalTime - *HH:mm:ss[.SSS]*
- OffsetDateTime - *uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX*
- OffsetTime - *HH:mm:ss[.SSS]XXX*
- ZonedDateTime - *uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX['['VV']']*

If you want to know more about why use such Java Date & Time formats, you can [read more here](https://goodforgod.dev/posts/2/)

```java
GsonConfiguration configuration = GsonConfiguration.of();
```

You can also use default Java ISO8601 formatters by:
```java
GsonConfiguration configuration = GsonConfiguration.ofJavaISO();
```

## Gson Configuration

Library provides configuration for configuring *GsonBuilder* for most properties:

```java
GsonBuilder builder = new GsonConfiguration()
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
        .setExcludeFieldsWithoutExposeAnnotation(true)
        .setExcludeFieldsWithModifiers(GsonConfiguration.FieldModifiers.TRANSIENT)
        .builder();
```

You can configure DateTimeFormatters for provided adapters:
```java
GsonBuilder builder = new GsonConfiguration()
        .setInstantFormat("uuuu-MM-dd HH:mm:ss")
        .builder();
```

### Properties file

**By default** Gson or GsonBuilder that is build via GsonConfiguration or GsonFactory **doesn't serialize/deserialize** transient, static, volatile, synchronized fields.
You need to use configuration setters to configure it otherwise.

GsonConfiguration also can be filled from *properties* file.

How to build GsonConfiguration from Properties:
```java
InputStream resource = getClass().getClassLoader().getResourceAsStream("gson.properties");
Properties properties = new Properties();
properties.load(resource);

GsonConfiguration configuration = GsonConfiguration.ofProperties(properties);
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

gson.forceIsoChronology=true
gson.forceResolverStrict=true

gson.lenient=true
gson.serializeNulls=true
gson.prettyPrinting=true
gson.escapeHtmlChars=false
gson.generateNonExecutableJson=true
gson.serializeComplexMapKey=true
gson.serializeSpecialFloatingPointValues=true
gson.excludeFieldsWithoutExposeAnnotation=false
gson.excludeFieldsWithModifiers=TRANSIENT,STATIC,SYNCHRONIZED,VOLATILE

gson.policy.fieldNaming=UPPER_CAMEL_CASE
gson.policy.longSerialization=STRING
```

#### Factory

Gson can also be instantiated via properties using *GsonFactory*.

*GsonFactory* is looking for property file in root *resource*: **gson.properties**
```java
Gson gson = new GsonFactory().build();
```

There is respected method to build Gson with Java ISO8601 formatters as defaults:
```java
Gson gson = new GsonFactory().buildJavaISO();
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
        .registerTypeAdapter(LocalDate.class, LocalDateSerializer.INSTANCE)
```

You can register with custom formatter also:
```java
GsonBuilder builder = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(DateTimeFormatters.ISO_LOCAL_DATE))
```

## License

This project licensed under the MIT - see the [LICENSE](LICENSE) file for details.
