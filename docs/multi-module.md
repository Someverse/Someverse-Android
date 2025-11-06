# Multi-Module Architecture

## Overview of Layers and Modules

The architecture of this Android application is based on a multi-module approach, with each module
serving a distinct purpose. The primary layers include the presentation layer (UI), domain layer (
business logic), and data layer (data processing). This structure is designed to promote
maintainability, scalability, and clarity within the project.

## Layer and Module Relationship

The following directory structure illustrates the organization of the project:

```
someverse/
├── app/                          # Application module
│   └── AndroidManifest.xml
│   └── Application class
│   └── MainActivity
├── presentation/                 # Presentation layer (UI)
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/someverse/presentation/
│   │   │   ├── MainActivity.kt
│   │   │   └── ui/theme/
│   │   │       ├── Color.kt
│   │   │       ├── Type.kt
│   │   │       └── Theme.kt
│   │   └── res/
│   └── build.gradle.kts
│
├── domain/                       # Domain layer (Business logic)
│   ├── src/main/java/com/someverse/domain/
│   │   └── MyClass.kt
│   └── build.gradle.kts
│
└── data/                         # Data layer (Data processing)
    ├── src/main/java/com/someverse/data/
    └── build.gradle.kts
```

## Module Dependency Rules

The dependency flow between modules is crucial for maintaining a clean architecture. The diagram
below illustrates the allowed dependencies:

```
         app
          │
    ┌─────┼─────┐
    │     │     │
    ↓     ↓     ↓
presentation data
    │     │
    └──↓──┘
    domain
```

### Dependency Flow

1. **app** → presentation, data, domain
2. **presentation** → domain (UI uses business logic)
3. **data** → domain (Data uses business logic)
4. **domain** → depends on nothing (Pure Kotlin)

### Forbidden Dependencies

- ❌ presentation → data (direct reference prohibited)
- ❌ domain → presentation (reverse dependency prohibited)
- ❌ domain → data (reverse dependency prohibited)
- ❌ domain → Android Framework (pure Kotlin only)