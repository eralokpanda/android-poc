# AndroidPoc

A simple Android app with adaptive tablet screen support and an optimized GitHub Actions CI pipeline.

## Features

- **Adaptive layouts** — list view on phones, 3-column grid on tablets
- **Resource qualifiers** — `sw600dp` (7"+ tablets) and `sw720dp` (10"+ tablets) for dimensions, layouts, and boolean flags
- **Material Design 3** — uses Material3 theme and components
- **View Binding** — type-safe view access
- **GitHub Actions CI** with aggressive caching (5 cache layers)

## Project Setup

### Prerequisites

- Android Studio Hedgehog or later
- JDK 17
- Android SDK 34

### First-time setup

If the Gradle wrapper JAR is missing (not committed as binary), generate it:

```bash
# On a machine with Gradle installed:
gradle wrapper --gradle-version 8.7
```

Then build:

```bash
./gradlew assembleDebug
```

## Tablet Support

| Qualifier | Screen Size | Layout | Behavior |
|-----------|------------|--------|----------|
| (default) | Phone | `layout/activity_main.xml` | Single-column list |
| `sw600dp` | 7"+ tablet | `layout-sw600dp/activity_main.xml` | 3-column grid, centered content |
| `sw720dp` | 10"+ tablet | Larger dimens only | Bigger padding, text, cards |

## CI/CD — GitHub Actions Caching Strategy

The workflow (`.github/workflows/android-build.yml`) uses **5 cache layers** to minimize build time:

| Cache | Key Strategy | What's Cached |
|-------|-------------|---------------|
| Gradle Wrapper | `gradle-wrapper.properties` hash | `~/.gradle/wrapper` |
| Gradle Dependencies | All `*.gradle.kts` + `gradle.properties` hash | `~/.gradle/caches/modules-2`, `transforms-3`, `jars-9` |
| Gradle Build Cache | `github.sha` with fallback | `~/.gradle/caches/build-cache-1` |
| Android SDK | `app/build.gradle.kts` hash | SDK build-tools, platforms |
| Kotlin Compiler | `*.gradle.kts` hash | `~/.kotlin`, `~/.konan` |

Additional optimizations:
- `--build-cache` and `--configuration-cache` flags on all Gradle tasks
- `org.gradle.parallel=true` and `org.gradle.configureondemand=true` in `gradle.properties`
- Concurrency groups cancel redundant runs on the same branch
- Gradle daemons stopped after build to free cache space
