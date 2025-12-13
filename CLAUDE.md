# Agent Instructions

This file contains important guidelines for AI agents working on this project.

## Architecture and Design Principles

This project follows **Clean Architecture** principles with a **multi-module structure**. Before
making any changes, please review the following documentation:

### Required Reading

1. **[@docs/architecture.md](docs/architecture.md)** - Clean Architecture rules and guidelines
    - Layer structure and dependency rules
    - Domain, Data, and Presentation layer responsibilities
    - Data flow patterns
    - Package structure conventions

2. **[@docs/multi-module.md](docs/multi-module.md)** - Multi-module architecture
    - Module organization and structure
    - Module dependency rules and restrictions
    - Forbidden dependencies

3. **[@docs/version-catalog.md](docs/version-catalog.md)** - Dependency management
    - Version catalog usage with `gradle/libs.versions.toml`
    - Maintaining version consistency

## Core Rules (Always Follow)

### 1. Clean Architecture Dependency Rule

- **Dependency direction**: `presentation → domain ← data`
- Domain layer must be **pure Kotlin** (no Android Framework dependencies)
- Domain layer must **not** import data or presentation layers
- Presentation layer must **not** directly reference data layer

### 2. Multi-Module Structure

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

### 3. Domain Layer Restrictions (Most Important)

- ✅ UseCase, Entity, Repository Interface only
- ✅ Pure Kotlin code only
- ❌ NO Android Framework (Context, Activity, Fragment, etc.)
- ❌ NO external libraries (Retrofit, Room, etc.)
- ❌ NO imports from data or presentation layers

### 4. Data Layer Responsibilities

- Implement Domain's Repository Interface
- Convert DTO ↔ Domain Model using Mappers
- Handle API services and Database access
- Do NOT import presentation layer

### 5. Presentation Layer Guidelines

- Depend only on Domain's UseCase
- Do NOT directly reference data layer
- Do NOT include business logic (even in ViewModel)
- Use UI State and UI Event patterns

## Version Management

- Use Version Catalog (`gradle/libs.versions.toml`)
- Maintain version consistency across modules
- Do NOT hardcode dependency versions in module build files

## When Adding New Features

1. **Start with Domain layer**
    - Define Entity (domain model)
    - Define Repository Interface
    - Create UseCase with business logic

2. **Implement Data layer**
    - Create DTO (Data Transfer Object)
    - Implement Repository Interface
    - Create Mapper for DTO ↔ Domain Model conversion
    - Set up DataSource (API/Database)

3. **Build Presentation layer**
    - Create ViewModel
    - Define UI State and Events
    - Build UI (Activity/Fragment/Composable)
    - Call UseCase from ViewModel

## Reference Documentation

For detailed information, always refer to:

- [@docs/architecture.md](docs/architecture.md) - Complete architecture guidelines
- [@docs/multi-module.md](docs/multi-module.md) - Module structure and dependencies
- [@docs/version-catalog.md](docs/version-catalog.md) - Dependency management

## Questions to Ask Before Making Changes

1. Does this change violate dependency rules?
2. Is the domain layer still pure Kotlin without external dependencies?
3. Are all layers properly separated?
4. Does the data flow follow the correct pattern?
5. Are we using Version Catalog for dependencies?

---

**Remember**: When in doubt, refer to the documentation in the `@docs/` folder. These guidelines
ensure maintainability, scalability, and clean separation of concerns.