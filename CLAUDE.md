# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run all unit tests
./gradlew test

# Run unit tests for a specific module
./gradlew :domain:test
./gradlew :data:test
./gradlew :usecases:test
./gradlew :app:test

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Run a single test class
./gradlew :app:testDebugUnitTest --tests "com.henryjhavierdev.rickandmorty.ExampleUnitTest"

# Clean build
./gradlew clean
```

## Architecture

This is a **Clean Architecture** Android app with strict module separation enforced via Gradle subprojects:

```
domain       → Pure Kotlin entities (Character, Episode, Location, Origin). No Android deps.
data         → Repository classes + datasource interfaces. Depends on domain only.
usecases     → One class per use case, each wrapping a repository method. Depends on data + domain.
framework/   → Concrete implementations of datasource interfaces:
  requestmanager   → Retrofit + RxJava2 HTTP client (rickandmortyapi.com/api/)
  databasemanager  → Room database for favorite characters
  imagemanager     → Glide image loading extension
app          → UI layer: Fragments, ViewModels, Dagger DI wiring, adapters
```

**Dependency rule:** outer modules depend on inner ones, never the reverse. `domain` has zero Android dependencies.

## Dependency Injection (Dagger 2)

The DI graph uses a **Component + Subcomponent** pattern:

- `RickAndMortyComponent` (singleton, created in `MainApplication`) — root component with `ServiceModule`, `DatabaseModule`, `RepositoryModule`, `UseCaseModule`
- Each screen has a `*Module` + `*Component` Subcomponent pair in `app/di/` that provides its ViewModel

ViewModels are **not** created via `ViewModelProvider`; they are manually instantiated by Dagger subcomponents and retrieved via `by lazy { component.viewModel }` in Fragments.

## Reactive Stack

- **RxJava2** is the primary async mechanism across all layers (`Single`, `Maybe`, `Flowable`)
- `CompositeDisposable` is used in ViewModels and cleared in `onCleared()`
- Room queries return `Flowable` for live favorite updates; network calls return `Single`
- LiveData is used only at the ViewModel→Fragment boundary, bridged from Rx via `reactivestreams-ktx`

## Navigation & UI Patterns

- Single-Activity (`MainActivity`) with Navigation Component; Fragments use SafeArgs for type-safe argument passing
- Character detail is a `DialogFragment` (`CharacterDetailDialogFragment`), not a full-screen fragment
- ViewModels expose a **single `events: LiveData<Event<SealedClass>>`** stream (one-shot event wrapper pattern via `Event<T>`) instead of multiple LiveData fields
- Data Binding is enabled project-wide; custom binding adapters live in `CustomBindingAdapter.kt`

## Data Flow Example

```
Fragment → ViewModel.onGetAllCharacters()
  → GetAllCharactersUseCase.invoke(page)
    → CharacterRepository.getAllCharacters(page)
      → CharacterRemoteDataSource (interface, data layer)
        → CharacterRemoteDataSourceImpl (requestmanager, Retrofit)
          → rickandmortyapi.com/api/character?page=N
```

Favorites persist locally via Room (`CharacterLocalDataSourceImpl` in `databasemanager`), with `Mapper.kt` files at each layer boundary converting between domain entities and framework-specific models.
