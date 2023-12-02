# Ableron Spring Boot
[![Build Status](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml/badge.svg)](https://github.com/ableron/ableron-spring-boot/actions/workflows/main.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ableron/ableron-spring-boot/badge.svg)](https://mvnrepository.com/artifact/io.github.ableron/ableron-spring-boot)

**Spring Boot Support for Ableron Server Side UI Composition**
1. Spring Boot 3.x project: Please use ableron-spring-boot-starter 3.x (Java 17+, spring-webmvc only)
2. Spring Boot 2.x project: Please use ableron-spring-boot-starter 2.x (Java 11+, spring-webmvc only)

## Installation
Gradle:
```groovy
implementation 'io.github.ableron:ableron-spring-boot-starter:3.6.0'
```

Maven:
```xml
<dependency>
  <groupId>io.github.ableron</groupId>
  <artifactId>ableron-spring-boot-starter</artifactId>
  <version>3.6.0</version>
</dependency>
```

## Usage
Use includes in response body
```html
<ableron-include src="https://your-fragment-url" />
```

### Configuration

#### `ableron.enabled`

Default: `true`

Whether UI composition is enabled.

#### `ableron.fragment-request-timeout-millis`

Default: `3000`

Timeout in milliseconds for requesting fragments.

#### `ableron.fragment-request-headers-to-pass`

Default:

```java
List.of(
    "Accept-Language",
    "Correlation-ID",
    "Forwarded",
    "Referer",
    "User-Agent",
    "X-Correlation-ID",
    "X-Forwarded-For",
    "X-Forwarded-Proto",
    "X-Forwarded-Host",
    "X-Real-IP",
    "X-Request-ID"
);
```

Request headers that are passed to fragment requests, if present.

#### `ableron.primary-fragment-response-headers-to-pass`

```java
List.of(
    "Content-Language",
    "Location",
    "Refresh"
);
```

Response headers of primary fragments to pass to the page response, if present.

#### `ableron.cache-max-size-in-bytes`

Default: `10485760` (`10 MB`)

Maximum size in bytes the fragment cache may have.

#### `ableron.cache-vary-by-request-headers`

Default: `empty list`

Fragment request headers which influence the requested fragment aside from its URL. Used to create fragment cache keys.
Must be a subset of `ableron.fragment-request-headers-to-pass`. Common example are headers used for steering A/B-tests.

#### `ableron.stats-append-to-content`

Default: `false`

Whether to append UI composition stats as HTML comment to the content.
