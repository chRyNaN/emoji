# emoji

Kotlin Multi-platform Emoji Support Library. <br/>
<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/chRyNaN/emoji">

## Usage

### Create an Emoji

```kotlin
Emoji(
    unicodeString = "U+1F600",
    character = "😀",
    name = "grinning face",
    alias = ":grinning_face:",
    category = "Smileys & Emotion",
    group = "face-smiling",
    iconUri = null
)
```

### Accessing Emojis

Emojis can be retrieved from a `EmojiRepository` instance:

```kotlin
val emojiByName = emojiRepository.getByName("grinning face")
val emojiByAlias = emojiRepository.getByAlias(":grinning_face:")
val allEmojis = emojiRepository.getAll()
```

#### Retrieving an EmojiRepository

The `EmojiRepository` interface is in the `emoji-core` module but there are different implementations in
the `emoji-repo-map` and `emoji-repo-sqlite` modules. Note that the `emoji-repo-sqlite` module uses
the [sqldelight](https://github.com/cashapp/sqldelight) library and requires a `SqlDriver` to obtain an instance of
the `SqliteEmojiRepository`.

`emoji-repo-map`:

```
val repo = KotlinMapEmojiRepository()
```

`emoji-repo-sqlite`:

```kotlin
val database = EmojiDatabase(sqldriver)
val repo = SqliteEmojiRepository(database)
```

#### Initializing an EmojiRepository

Before the `EmojiRepository` implementation class is used for the first time, it has to be initialized by calling
the `init()` function. This loads the emoji data set. Note that the `init()` function is only on the implementation
classes and not on the `EmojiRepository` interface.

```kotlin
repository.init()
```

### Categorizing Emojis

It may be useful to have the Emojis grouped by their `category` and/or `group` properties. There are convenience
functions to achieve this.

```kotlin
repository.getAll().categorize() // List<EmojiCategory>
repository.getAll().group() // List<EmojiGroup>
```

## Building the library

The library is provided through [Repsy.io](https://repsy.io). Checkout
the [releases page](https://github.com/chRyNaN/emoji/releases) to get the latest version. <br/>
<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/chRyNaN/emoji">

### Repository

```groovy
repositories {
    maven { url = "https://repo.repsy.io/mvn/chrynan/public" }
}
```

### Core Dependency

```groovy
implementation "com.chrynan.emoji:emoji-core:$VERSION"
```

### Map Repository Dependency

```groovy
implementation "com.chrynan.emoji:emoji-repo-map:$VERSION"
```

### SQLite Repository Dependency

```groovy
implementation "com.chrynan.emoji:emoji-repo-sqlite:$VERSION"
```

### Presentation Core Dependency

```groovy
implementation "com.chrynan.emoji:emoji-presentation-core:$VERSION"
```

### Presentation Android Dependency

```groovy
implementation "com.chrynan.emoji:emoji-presentation-android:$VERSION"
```

### Presentation Android Jetpack Compose Dependency

```groovy
implementation "com.chrynan.emoji:emoji-presentation-android-compose:$VERSION"
```

## Documentation

More detailed documentation is available in the [docs](docs) folder. The entry point to the documentation can be
found [here](docs/index.md).

## License

```
Copyright 2020 chRyNaN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
